//
//  HomeRouter.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

@objc protocol HomeRoutingLogic {
    func navigateToMovieDetails(atIndexPath indexPath: IndexPath, withGenre genre: Int)
}

protocol HomeDataPassing {
    var dataStore: HomeDataStore? { get set }
}

class HomeRouter: NSObject, HomeRoutingLogic, HomeDataPassing {
    
    weak var viewController: HomeViewController?
    var dataStore: HomeDataStore?
    
    // MARK: - Navigation
    func navigateToMovieDetails(atIndexPath indexPath: IndexPath, withGenre genre: Int) {
        
        if let genreMovies = dataStore?.movies[genre], genreMovies.count > indexPath.row {
            let selectedMovie = genreMovies[indexPath.row]
            
            guard let movieId = selectedMovie.id, let vc = viewController?.storyboard?.instantiateViewController(identifier: MovieDetailsViewController.identifier, creator: { coder in
                return MovieDetailsViewController(coder: coder, movieId: movieId)
            }) else {
                fatalError("Failed to load MovieDetailsViewController from storyboard.")
            }
            
            viewController?.navigationController?.pushViewController(vc, animated: true)
        }
    }
}
