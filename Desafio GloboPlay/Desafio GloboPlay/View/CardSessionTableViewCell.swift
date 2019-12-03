//
//  CardSessionTableViewCell.swift
//  Desafio GloboPlay
//
//  Created by Filipo Negrao on 03/11/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import UIKit

class CardSessionTableViewCell: UITableViewCell {
    
    private weak var controller : HomeViewController!
    @IBOutlet weak var collectionView: UICollectionView!
    private var cards: [Card] = [Card]()
    // Guarda a informacao de qual sessao é essa celula
    private var section : Int!
    
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
    
    func configCell(cards: [Card], section: Int, controller: HomeViewController) {
        self.controller = controller
        self.cards = cards
        self.collectionView.reloadData()
        self.section = section
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
    
    func collectionView(_ collectionView: UICollectionView, willDisplay cell: UICollectionViewCell, forItemAt indexPath: IndexPath) {
        let index = indexPath.item
        if index == self.cards.count-1 {
            if section == 0 {
                if !self.controller.popularLimit {
                    self.controller.popularIndex += 1
                    self.controller.loadPopular(page: self.controller.popularIndex, append: true)
                }
            } else if section == 1 {
                if !self.controller.topLimit {
                    self.controller.topIndex += 1
                    self.controller.loadTop(page: self.controller.topIndex, append: true)
                }
                
            } else if section == 2 {
                if !self.controller.moviesLimit {
                    self.controller.moviesIndex += 1
                    self.controller.loadMovies(page: self.controller.moviesIndex, append: true)
                }
                
            }
        }
    }
}
