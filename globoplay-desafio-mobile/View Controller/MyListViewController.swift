//
//  MyListViewController.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 05/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import UIKit

class MyListViewController: UIViewController, UICollectionViewDelegate, UICollectionViewDataSource{

    @IBOutlet var collectionViewMyList: UICollectionView!
    
    var images = ["teste1", "teste2", "teste3"]
    var flag :Bool?
    var index :Int?
    var myList: [Movie]?
    private let spacing:CGFloat = 8.0
    
    override func viewWillAppear(_ animated: Bool) {
        self.collectionViewMyList.reloadData()
        let defaults = UserDefaults.standard
        
        if defaults.object(forKey: "favoriteListArray") != nil {
            do {
                let playerData = defaults.object(forKey: "favoriteListArray") as? Data
                let decoder = PropertyListDecoder()
                myList = try decoder.decode(Array<Movie>.self, from: playerData!)
            } catch {
                print(error)
            }
        }
    }
    
    override func viewDidLoad() {
        let layout = UICollectionViewFlowLayout()
        layout.sectionInset = UIEdgeInsets(top: spacing, left: spacing, bottom: spacing, right: spacing)
        layout.minimumLineSpacing = spacing
        layout.minimumInteritemSpacing = spacing
        layout.itemSize = CGSize(width: 110, height: 230)
        self.collectionViewMyList?.collectionViewLayout = layout
        
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        
        if (myList == nil){
            return 0
        }
        return myList!.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "myListCell", for: indexPath) as! MyListMovieCollectionViewCell
                
        guard let path = myList?[indexPath.row].poster_path else {
            cell.myListMoviePoster.image =  UIImage(named: "placeholder")
            return cell
        }
        let imageUrl = URL(string: CONST.API_CONSTANTS.BASE_IMAGE_URL + path )
        cell.myListMoviePoster.kf.setImage(with: imageUrl)
        return cell
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.destination.isKind(of: DetailsViewController.self) {
            let secondVC = segue.destination as! DetailsViewController
            secondVC.isFromHome = flag
            secondVC.indexList = index
            secondVC.currentMovie = myList![index!]
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        flag = false
        index = indexPath.row
        self.performSegue(withIdentifier: "toDetailsView", sender: self)
    }
    
   
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        
        let numberOfItemsPerRow:CGFloat = 3
        let spacingBetweenCells:CGFloat = 8

        let totalSpacing = (2 * self.spacing) + ((numberOfItemsPerRow - 1) * spacingBetweenCells)
        
        if let collection = self.collectionViewMyList {
            _ = (collection.bounds.width - totalSpacing)/numberOfItemsPerRow
            return CGSize(width: 150, height: 270)
        } else {
            return CGSize(width: 150, height: 270)
        }
    }

}
