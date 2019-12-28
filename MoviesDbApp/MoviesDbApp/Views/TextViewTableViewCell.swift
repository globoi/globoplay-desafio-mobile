//
//  TextViewTableViewCell.swift
//  Movs
//
//  Created by gmmoraes on 27/12/19.
//  Copyright © 2019 gmmoraes. All rights reserved.
//

import Foundation
import UIKit

class TextViewTableViewCell: UITableViewCell {
    
    @IBOutlet weak var collectionView: UICollectionView!
    @IBOutlet weak var textView: UITextView!
    
    var isLoading = false
    
    var popularMovies: [Movies] = []
    var popularSeries: [Series] = []
    var currentSection: Int = 0 {
        didSet{
            if currentSection == 0 {
                self.getMovies()
            }else if currentSection == 1 {
                self.getSeries()
            }
        }
    }
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.backgroundColor = .backgroundColor
        textView.backgroundColor = .backgroundColor
        
        collectionView.backgroundColor = .backgroundColor
        collectionView.reloadData()
        
        NotificationCenter.default.addObserver(self, selector: #selector(changeVisibility(_:)), name: Notification.Name("isTextViewVisible"), object: nil)
    }
    
    @objc func changeVisibility(_ notification: NSNotification) {
        
        if let isTextViewVisible = notification.userInfo?["isTextViewVisible"] as? Bool {
            
            if isTextViewVisible == true {
                textView.isHidden = !isTextViewVisible
                collectionView.isHidden = isTextViewVisible
                
            } else {
                textView.isHidden = !isTextViewVisible
                collectionView.isHidden = isTextViewVisible
                collectionView.reloadData()
            }
        }
        
    }
    

}

extension TextViewTableViewCell: UICollectionViewDataSource {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 5
    }
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {

        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "assistaTambemCollectionViewCell", for: indexPath) as! assistaTambemCollectionViewCell
        
        cell.tag = currentSection
        if  (popularMovies.count > 0 || popularSeries.count > 0) {
            let posterPath = currentSection == 0 ? popularMovies[indexPath.row].poster_path : popularSeries[indexPath.row].poster_path
            let id = currentSection == 0 ? popularMovies[indexPath.row].id : popularSeries[indexPath.row].id
            if let urlPath = URL(string: "https://image.tmdb.org/t/p/w185/" + posterPath) {
                cell.thumbnailRequestInfo = VideoThumbnailRequestInfo(section: currentSection, id: id, url: urlPath)
            }
        }

        

        
        return cell

    }

}

extension TextViewTableViewCell: UICollectionViewDelegateFlowLayout {

    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: 200, height: 200)
    }

}


//Mark: Api requests
extension TextViewTableViewCell {
    func getMovies(){
        let randomInt = Int.random(in: 0..<6)
        MoviesDBRequest.sharedInstance.getPopularMovies(manuallySectPage: randomInt) {[weak self] (response) -> () in
            self?.isLoading = false
            DispatchQueue.main.async {
                self?.collectionView.performBatchUpdates({
                    self?.popularMovies += response.results
                    for i in 0 ..< 5 {
                        self?.collectionView.insertItems(at: [IndexPath(item: i, section: 0)])
                    }
                }, completion: { (completed) in })
            }
        }
    }
    
    func getSeries(){
        let randomInt = Int.random(in: 0..<6)
        MoviesDBRequest.sharedInstance.getPopularTvSeries(manuallySectPage: randomInt) {[weak self] (response) -> () in
            self?.isLoading = false
            DispatchQueue.main.async {
                self?.collectionView.performBatchUpdates({
                    self?.popularSeries += response.results
                    for i in 0 ..< 5 {
                        self?.collectionView.insertItems(at: [IndexPath(item: i, section: 0)])
                    }
                }, completion: { (completed) in })
            }
        }
    }
    
}
