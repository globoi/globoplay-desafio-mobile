//
//  CardSessionTableViewCell.swift
//  Desafio GloboPlay
//
//  Created by Filipo Negrao on 03/11/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import UIKit

class CardSessionTableViewCell: UITableViewCell {
    
    private weak var controller : UIViewController!
    @IBOutlet weak var collectionView: UICollectionView!
    private var cards: [Card] = [Card]()
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.backgroundColor = UIColor.clear
        self.collectionView.delegate = self
        self.collectionView.dataSource = self
        self.configCollection()
        self.selectionStyle = .none
    }
    
    func configCollection() {
        self.collectionView.register(CardCollectionViewCell.self, forCellWithReuseIdentifier: "Cell")
        // Define a largura dos cards
        let itemWidth = StaticFunctions.getItemWidth()
        let itemHeight = StaticFunctions.getItemHeight()
        
        let collectionLayout = UICollectionViewFlowLayout()
        collectionLayout.scrollDirection = .horizontal
        collectionLayout.itemSize = CGSize.init(width: itemWidth, height: itemHeight)
        
        self.collectionView.collectionViewLayout = collectionLayout
        self.collectionView.showsVerticalScrollIndicator = false
        self.collectionView.showsHorizontalScrollIndicator = false
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        
        // Configure the view for the selected state
    }
    
    func configCell(cards: [Card], section: Int, controller: UIViewController) {
        self.controller = controller
        self.cards = cards
        self.collectionView.reloadData()
    }
    
}

// MARK: Collection view method and delegate
extension CardSessionTableViewCell : UICollectionViewDelegate, UICollectionViewDataSource {
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.cards.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "Cell", for: indexPath) as! CardCollectionViewCell
        let card = self.cards[indexPath.item]
        cell.configCell(card: card)
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let card = self.cards[indexPath.item]
        let controller = CardViewController.init(card: card)
        self.controller.navigationController?.pushViewController(controller, animated: true)
    }
}
