//
//  BookmarksViewController.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 27/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import UIKit

protocol BookmarksViewProtocol: class {
    func display()
}

class BookmarksViewController: UIViewController, BookmarksViewProtocol {
    @IBOutlet weak var bookmarksCollectionView: UICollectionView!
    
    var presenter: BookmarksPresenterProtocol!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setUp(collectionView: self.bookmarksCollectionView)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        self.presenter.getData()
    }
    
    func setUp(collectionView: UICollectionView) {
        collectionView.dataSource = self
        
        collectionView.backgroundColor = Colors.collectionBG
        collectionView.showsHorizontalScrollIndicator = false
        collectionView.showsVerticalScrollIndicator = false
        
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .vertical
        layout.sectionInset = UIEdgeInsets(top: 0, left: 8, bottom: 0, right: 8)
        layout.itemSize = CGSize(width: 106, height: 159)
        
        collectionView.collectionViewLayout = layout
    }
    
    func display() {
        DispatchQueue.main.async {
            self.bookmarksCollectionView.reloadData()
        }
    }
}

extension BookmarksViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.presenter.getNumberOfItems()
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "movieCell", for: indexPath) as? MovieCellCollectionViewCell else { return UICollectionViewCell() }
        
        self.presenter.configure(cell, at: indexPath.row)
        
        return cell
    }
    
    
}
