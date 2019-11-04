//
//  RecommendationsViewController.swift
//  Desafio GloboPlay
//
//  Created by Filipo Negrao on 04/11/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import UIKit

class RecommendationsViewController: UIViewController {

    private var collectionView: UICollectionView!
    private var recomendations: [Card] = [Card]()
    private weak var controller : UIViewController!
    
    let margin : CGFloat = 20
    
    init(controller: UIViewController, recommendations: [Card]) {
        super.init(nibName: nil, bundle: nil)
        self.recomendations = recommendations
        self.controller = controller
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.view.backgroundColor = UIColor.clear
            
        
         self.configCollection()
    }
    
        func configCollection() {
            // Define a largura dos cards
            let itemWidth = StaticFunctions.getItemWidth()
            let itemHeight = StaticFunctions.getItemHeight()
            
            let collectionLayout = UICollectionViewFlowLayout()
            collectionLayout.scrollDirection = .horizontal
            collectionLayout.itemSize = CGSize.init(width: itemWidth, height: itemHeight)
            
            self.collectionView = UICollectionView(frame: CGRect(x: 0, y: 0, width: screenWidth, height: screenWidth), collectionViewLayout: collectionLayout)
            
            self.collectionView.register(CardCollectionViewCell.self, forCellWithReuseIdentifier: "Cell")
            self.collectionView.delegate = self
            self.collectionView.dataSource = self
            self.collectionView.collectionViewLayout = collectionLayout
            self.collectionView.showsVerticalScrollIndicator = false
            self.collectionView.showsHorizontalScrollIndicator = false
            self.collectionView.backgroundColor = UIColor.clear
            self.collectionView.autoresizesSubviews = false
            self.collectionView.automaticallyAdjustsScrollIndicatorInsets = false
            self.view.addSubview(self.collectionView)
        }

}


// MARK: Collection view method and delegate
extension RecommendationsViewController : UICollectionViewDelegate, UICollectionViewDataSource {
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.recomendations.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "Cell", for: indexPath) as! CardCollectionViewCell
        let card = self.recomendations[indexPath.item]
        cell.configCell(card: card)
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let card = self.recomendations[indexPath.item]
        let controller = CardViewController.init(card: card)
        self.controller.navigationController?.pushViewController(controller, animated: true)
    }
}
