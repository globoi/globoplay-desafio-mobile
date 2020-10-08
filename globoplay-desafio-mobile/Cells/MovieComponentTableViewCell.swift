//
//  MovieComponentTableViewCell.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 05/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import UIKit
import Kingfisher

class MovieComponentTableViewCell: UITableViewCell, UICollectionViewDelegate, UICollectionViewDataSource {
   
    @IBOutlet weak var title: UILabel!
    @IBOutlet weak var cv: UICollectionView!
    public var name: String!
    public var tableIndex: Int!
    var delegate: CollectionViewCellDelegate?
    var movieList: [Movie]?
    
    override func awakeFromNib() {
        super.awakeFromNib()
        let nibNameCol = UINib(nibName: "movieComponent", bundle: nil)
        self.cv.register(nibNameCol, forCellWithReuseIdentifier: "mcColCell")
        cv.delegate = self
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 10
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "mcColCell", for: indexPath) as! MovieComponentCollectionViewCell
        
        var imageUrl = URL(string: "")
        
        if (movieList != nil){
            guard let path = movieList?[indexPath.row].poster_path else {
                cell.moviePoster.image =  UIImage(named: self.name)
                return cell
            }
            imageUrl = URL(string: CONST.API_CONSTANTS.BASE_IMAGE_URL + path )
        }
        
        cell.moviePoster.kf.setImage(with: imageUrl)
        
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        self.delegate?.cellWasPressed(index: indexPath.row, tableId: tableIndex)
    }
}

protocol CollectionViewCellDelegate {
    func cellWasPressed(index: Int, tableId: Int)
}



