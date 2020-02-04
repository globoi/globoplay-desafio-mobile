//
//  HomeViewController.swift
//  TheMovieDB
//
//  Created by Edwy Lugo on 03/02/20.
//  Copyright © 2020 Edwy Lugo. All rights reserved.
//

import UIKit
import Kingfisher
import Alamofire
import AlamofireObjectMapper

class HomeViewController: UIViewController {

    
    var baseURL: TheMovieDBAPI = TheMovieDBAPI()
    var movies: [Movies] = []
    var movie: Movies!
    var total = 0
    
    @IBOutlet weak var collectionView: UICollectionView! {
        didSet {
            collectionView.delegate = self
            collectionView.dataSource = self
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        let imageView = UIImageView(frame: CGRect(x: 0, y: 0, width: 28, height: 28))
        imageView.contentMode = .scaleAspectFit
        let image = UIImage(named: "globoplay-logo")
        imageView.image = image
        self.navigationItem.titleView = imageView
        
        // Retorna a célula da interface
               let nib = UINib(nibName: "HomeCollectionViewCell", bundle: nil)
               collectionView.register(nib, forCellWithReuseIdentifier: HomeCollectionViewCell.reuseIdentifier)
        
        loadMovies()
    }
    
    
    func loadMovies() {
        
        TheMovieDBAPI.loadMovies() { (info) in
            if let info = info {
                
                self.movies.append(contentsOf: info.results)
                self.total = info.total_results
                
                print("Total:", self.total, "Inserted", self.movies.count)
                
                DispatchQueue.main.async {
                    self.collectionView.reloadData()
                }
                
            }
        }
    }

}

extension HomeViewController: UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return movies.count
    }
    
    func collectionView(_: UICollectionView, layout _: UICollectionViewLayout, sizeForItemAt _: IndexPath) -> CGSize { // size of your item for screen sizes
        let wsize = UIScreen.main.bounds.size.width
        switch wsize {
        case 414:
            return CGSize(width: 118, height: 196)
        case 375:
            return CGSize(width: 100, height: 178)
        case 320:
            return CGSize(width: 90, height: 168)
        default:
            return CGSize(width: 118, height: 196)
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: HomeCollectionViewCell.reuseIdentifier,
                                                              for: indexPath) as? HomeCollectionViewCell else { return UICollectionViewCell() }
        
        let movie = movies[indexPath.row]
        if let url = URL(string: "\(baseURL.BASE_MOVIE_POSTER_URL)\(movie.poster_path)") {
                   cell.posterImageView.kf.indicatorType = .activity
                   cell.posterImageView.kf.setImage(with: url)
                   print(url)
               } else {
                   cell.posterImageView.image = UIImage(named: "globoplay")
               }

        return cell
        
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
       print("viewModel to MovieDetails")
    }
    
    
}
