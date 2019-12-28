//
//  TableViewItemCellid.swift
//  Movs
//
//  Created by gmmoraes on 20/12/19.
//  Copyright © 2019 gmmoraes. All rights reserved.
//

import Foundation
import UIKit

protocol CellDelegate {
    func colCategorySelected(_ movies: Movies?, series: Series?, image: UIImage)
}

class TableViewItemCellid: UITableViewCell {
    
    @IBOutlet weak var collectionView: UICollectionView!
    var numberOfCells: Int = 0
    var isLoading = false
    var tableViewHeight:CGFloat = 0
    var tableViewWidth:CGFloat = 0
    
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
    var delegate : CellDelegate?
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.collectionView.delegate = self
        self.collectionView.dataSource = self
        collectionView.backgroundColor = .backgroundColor
        collectionView.reloadData()
        
        let tap = UITapGestureRecognizer(target: self, action: #selector(userHasTap(_:)))
        tap.numberOfTapsRequired = 1
        tap.numberOfTouchesRequired = 1
        tap.delegate = self
        collectionView?.addGestureRecognizer(tap)
    }
    
    
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        let offsetX = scrollView.contentOffset.x

        let loadOnScrollThresholdX = CGFloat(1500)
        if (offsetX > scrollView.contentSize.width - loadOnScrollThresholdX) {
            loadMoreData()
        }
    }
    
    @objc func userHasTap(_ sender: UITapGestureRecognizer) {

        if let indexPath = self.collectionView?.indexPathForItem(at: sender.location(in: self.collectionView)) {
            if let cell = self.collectionView?.cellForItem(at: indexPath) as? VideoCollectionViewCell, let text = cell.title.text, let image =  cell.imageView.image, let overview = cell.overview {

                if cell.tag == 0 {
                    delegate?.colCategorySelected(popularMovies[indexPath.row], series: nil, image: image)
                }else if cell.tag == 1 {
                    delegate?.colCategorySelected(nil, series: popularSeries[indexPath.row], image: image)
                }
            }
            print("you can do something with the cell or index path here \(indexPath)")
        } else {
            print("collection view was tapped")
        }
    }

}

//Mark: Api requests
extension TableViewItemCellid {
    func getMovies(){
        MoviesDBRequest.sharedInstance.getPopularMovies {[weak self] (response) -> () in
            MoviesDBRequest.sharedInstance.popularMoviesNextPage = response.page + 1
            self?.isLoading = false
            DispatchQueue.main.async {
                self?.collectionView.performBatchUpdates({
                    let newItemCount = response.results.count
                    self?.popularMovies += response.results
                    let numberOfCells = self?.numberOfCells ?? 0
                    for i in numberOfCells ..< numberOfCells+newItemCount {
                        self?.collectionView.insertItems(at: [IndexPath(item: i, section: 0)])
                    }
                    self?.numberOfCells = self?.popularMovies.count ?? 0
                }, completion: { (completed) in })
            }
        }
    }
    
    func getSeries(){
        MoviesDBRequest.sharedInstance.getPopularTvSeries {[weak self] (response) -> () in
            MoviesDBRequest.sharedInstance.popularSeriesNextPage = response.page + 1
            self?.isLoading = false
            DispatchQueue.main.async {
                self?.collectionView.performBatchUpdates({
                    let newItemCount = response.results.count
                    self?.popularSeries += response.results
                    let numberOfCells = self?.numberOfCells ?? 0
                    for i in numberOfCells ..< numberOfCells+newItemCount {
                        self?.collectionView.insertItems(at: [IndexPath(item: i, section: 0)])
                    }
                    self?.numberOfCells = self?.popularSeries.count ?? 0
                }, completion: { (completed) in })
            }
        }
    }
    
    func loadMoreData() {
        if !self.isLoading {
            self.isLoading = true
            DispatchQueue.global().async {
                sleep(2)
                if self.currentSection == 0 {
                    self.getMovies()
                }else if self.currentSection == 1 {
                    self.getSeries()
                }
            }
        }
    }
}

extension TableViewItemCellid: UICollectionViewDataSource {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return numberOfCells
    }
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {

        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "VideoCollectionViewCell", for: indexPath) as! VideoCollectionViewCell
        
        cell.tag = currentSection
        cell.title.text = currentSection == 0 ? popularMovies[indexPath.row].title : popularSeries[indexPath.row].name
        cell.overview = currentSection == 0 ? popularMovies[indexPath.row].overview : popularSeries[indexPath.row].overview
        
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

extension TableViewItemCellid: UICollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: tableViewWidth * 0.30, height: tableViewHeight)
    }

}
