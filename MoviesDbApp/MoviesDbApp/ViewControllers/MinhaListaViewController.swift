//
//  MinhaListaViewController.swift
//  Movs
//
//  Created by gmmoraes on 20/12/19.
//  Copyright © 2019 gmmoraes. All rights reserved.
//

import UIKit

class MinhaListaViewController: UIViewController {

    @IBOutlet weak var noElementLabel: UILabel!
    @IBOutlet weak var collectionView: UICollectionView!
    
    var safeAreHeight:CGFloat = 0.0
    var sectionHeight:CGFloat = 0.0
    var collectionViewHeight:CGFloat = 0.0
    var topSafeArea: CGFloat = 0.0
    var bottomSafeArea: CGFloat = 0.0
    
    var favoritedMedias: [FavoritedMedia] = []
    var fileNames: [String] = [] {
        didSet {
            for file in fileNames {
                if let favorite = Storage.retrieve(file, from: .documents, as: FavoritedMedia.self) {
                    favoritedMedias.append(favorite)
                }
            }
            checkAmountOfFiles()
            collectionView.reloadData()
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.view.layoutIfNeeded()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
                
        checkAmountOfFiles()
        fileNames = Storage.listAllFileNames()
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()

        if #available(iOS 11.0, *) {
            topSafeArea = view.safeAreaInsets.top
            bottomSafeArea = view.safeAreaInsets.bottom
        } else {
            topSafeArea = topLayoutGuide.length
            bottomSafeArea = bottomLayoutGuide.length
        }

        safeAreHeight = self.view.frame.height - topSafeArea - bottomSafeArea -  (self.navigationController?.navigationBar.frame.height ?? 0)
        sectionHeight = safeAreHeight * 0.10
        collectionViewHeight = safeAreHeight * 0.40
        
        self.collectionView.backgroundColor = .backgroundColor

        self.navigationController?.navigationBar.topItem?.title = "Minha Lista"
    }
    
    private func checkAmountOfFiles() {
        noElementLabel.alpha = Storage.amountOfFiles() == 0 ? 1.0 : 0.0
        collectionView.alpha = Storage.amountOfFiles() == 0 ? 0.0 : 1.0
    }
}
extension MinhaListaViewController: UICollectionViewDataSource {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return favoritedMedias.count
    }
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {

        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "MinhaListaCollectionCell", for: indexPath) as! MinhaListaCollectionCell
        
        if favoritedMedias.count > 0 {
            if let series = favoritedMedias[indexPath.row].series {
                
                let urlPath = "https://image.tmdb.org/t/p/w185/" + series.poster_path
                let id = series.id
                if let url = URL(string: urlPath) {
                    ImageCache.shared.loadVideoThumbnail(thumbnailRequestInfo: VideoThumbnailRequestInfo(section: 1, id: id, url: url)){ image in
                        DispatchQueue.main.async {
                            cell.imageView.image = image
                        }
                    }
                }
                
            }else if let movies = favoritedMedias[indexPath.row].movies {
                
                let urlPath = "https://image.tmdb.org/t/p/w185/" + movies.poster_path
                let id = movies.id
                if let url = URL(string: urlPath) {
                    ImageCache.shared.loadVideoThumbnail(thumbnailRequestInfo: VideoThumbnailRequestInfo(section: 1, id: id, url: url)){ image in
                        DispatchQueue.main.async {
                            cell.imageView.image = image
                        }
                    }
                }
                
            }
            
            
        }

        cell.imageView.contentMode = .scaleToFill
        return cell

    }

}

extension MinhaListaViewController: UICollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let padding: CGFloat = 25
        let collectionCellSize = collectionView.frame.size.width - padding
        return CGSize(width: collectionCellSize * 0.40, height: collectionViewHeight)
    }

}
