//
//  MovieComponentTableViewCell.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 05/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import UIKit

class MovieComponentTableViewCell: UITableViewCell, UICollectionViewDelegate, UICollectionViewDataSource {
   
    @IBOutlet weak var title: UILabel!
    @IBOutlet weak var cv: UICollectionView!
    public var name: String!
    public var tableIndex: Int!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        let nibNameCol = UINib(nibName: "movieComponent", bundle: nil)
        self.cv.register(nibNameCol, forCellWithReuseIdentifier: "mcColCell")
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 10
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "mcColCell", for: indexPath) as! MovieComponentCollectionViewCell
        cell.moviePoster.image =  UIImage(named: self.name)
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        print("Selecionou - \(indexPath.row) - \(tableIndex)\n\n\n" )
    }
}
