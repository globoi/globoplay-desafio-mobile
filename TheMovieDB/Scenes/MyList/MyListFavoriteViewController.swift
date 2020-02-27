//
//  MyListFavoriteViewController.swift
//  TheMovieDB
//
//  Created by Edwy Lugo on 03/02/20.
//  Copyright © 2020 Edwy Lugo. All rights reserved.
//

import UIKit

class MyListFavoriteViewController: UIViewController {
    
    

     @IBOutlet weak var collectionView: UICollectionView!
        
        private var favorites = [Card]()
        
        let margin : CGFloat = 20
        
        override func viewDidLoad() {
            super.viewDidLoad()
            
            let imageView = UIImageView(frame: CGRect(x: 0, y: 0, width: 28, height: 28))
            imageView.contentMode = .scaleAspectFit
            let image = UIImage(named: "globoplay-logo")
            imageView.image = image
            self.navigationItem.titleView = imageView
            
            self.view.backgroundColor = UIColor.black
            self.collectionView.delegate = self
            self.collectionView.dataSource = self
            self.configCollection()
    //        self.loadData()
        }
        
        override func viewWillAppear(_ animated: Bool) {
            super.viewWillAppear(animated)
            self.loadData()
            
        }
        
        func loadData() {
            self.favorites =  TheMovieDBAPI.sharedInstance.getFavorites()
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
    extension MyListFavoriteViewController : UICollectionViewDelegate, UICollectionViewDataSource {
        
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
