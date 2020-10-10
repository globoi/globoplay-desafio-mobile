//
//  DetailsWatchTableViewCell.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 06/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import UIKit

class DetailsWatchTableViewCell: UITableViewCell, UICollectionViewDelegate, UICollectionViewDataSource {
   
    @IBOutlet weak var detailsCollectionView: UICollectionView!
    
    var currentMovieId              : String = ""
    var currentMovie                : Movie?
    var relatedList                 : [Movie]?
    private let spacing             : CGFloat = 8.0
    var delegate                    : CollectionViewCellRelatedDelegate?

    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        if (relatedList?.isEmpty == true || relatedList == nil){
            return 0
        }
        return 6
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
      
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "relatedCell", for: indexPath) as! relatedMovieCollectionViewCell
        
        var imageUrl = URL(string: "placeholder")
        
        if (relatedList != nil){
            guard let path = relatedList?[indexPath.row].poster_path else {
                cell.moviePoster.image =  UIImage(named: "placeholder")
                return cell
            }
            imageUrl = URL(string: CONST.API_CONSTANTS.BASE_IMAGE_URL + path )
        }
        
        cell.moviePoster.kf.setImage(with: imageUrl, placeholder: UIImage(named:"placeholder"))

        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        self.delegate?.cellWasPressed(newCurrentMovie: relatedList![indexPath.row])
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        let nibNameCol = UINib(nibName: "relatedMovieCell", bundle: nil)
        self.detailsCollectionView.register(nibNameCol, forCellWithReuseIdentifier: "relatedCell")
        detailsCollectionView.delegate = self
        detailsCollectionView.dataSource = self
        
        let layout = UICollectionViewFlowLayout()
        layout.sectionInset = UIEdgeInsets(top: spacing, left: spacing, bottom: spacing, right: spacing)
        layout.minimumLineSpacing = spacing
        layout.minimumInteritemSpacing = spacing
        layout.itemSize = CGSize(width: 100, height: 180)
        self.detailsCollectionView?.collectionViewLayout = layout
    }
}

protocol CollectionViewCellRelatedDelegate {
    func cellWasPressed(newCurrentMovie: Movie)
}
