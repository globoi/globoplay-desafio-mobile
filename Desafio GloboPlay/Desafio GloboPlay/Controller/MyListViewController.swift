//
//  MyListViewController.swift
//  Desafio GloboPlay
//
//  Created by Filipo Negrao on 02/11/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import UIKit

class MyListViewController: UIViewController {

    @IBOutlet weak var collectionView: UICollectionView!
    
    private var favorites = [Card]()
    
    let margin : CGFloat = 20
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.view.backgroundColor = UIColor.clear
        self.collectionView.delegate = self
        self.collectionView.dataSource = self
        self.configCollection()
//        self.loadData()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        self.loadData()
    }
    
    func loadData() {
        self.favorites =  ApplicationService.sharedInstance.getFavorites()
        self.collectionView.reloadData()
    }
    
    func configCollection() {
        self.collectionView.register(CardCollectionViewCell.self, forCellWithReuseIdentifier: "Cell")
        // Define a largura dos cards
        let itemWidth = screenWidth/2 - margin/2
        let itemHeight = itemWidth * 1.5//StaticFunctions.getItemHeight()
        
        let collectionLayout = UICollectionViewFlowLayout()
        collectionLayout.scrollDirection = .vertical
        collectionLayout.itemSize = CGSize.init(width: itemWidth, height: itemHeight)
        
        self.collectionView.collectionViewLayout = collectionLayout
        self.collectionView.showsVerticalScrollIndicator = false
        self.collectionView.showsHorizontalScrollIndicator = false
    }
}

// MARK: Collection view method and delegate
extension MyListViewController : UICollectionViewDelegate, UICollectionViewDataSource {
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.favorites.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "Cell", for: indexPath) as! CardCollectionViewCell
        let card = self.favorites[indexPath.item]
        cell.configCell(card: card)
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let card = self.favorites[indexPath.item]
        let controller = CardViewController.init(card: card)
        self.navigationController?.pushViewController(controller, animated: true)
    }
}
