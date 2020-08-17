//
//  SearchRouter.swift
//  My Movies
//
//  Created by Rafael Valer on 17/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation
import UIKit

@objc protocol SearchRoutingLogic {
    func navigateToMovieDetails(atIndexPath indexPath: IndexPath)
}

protocol SearchDataPassing {
    var dataStore: SearchDataStore? { get set }
}

class SearchRouter: NSObject, SearchRoutingLogic, SearchDataPassing {
    
    weak var viewController: SearchViewController?
    var dataStore: SearchDataStore?
    
    // MARK: - Navigation
    
    func navigateToMovieDetails(atIndexPath indexPath: IndexPath) {
        guard let movieId = dataStore?.searchedMovies[indexPath.row].id, let vc = viewController?.storyboard?.instantiateViewController(identifier: MovieDetailsViewController.identifier, creator: { coder in
            return MovieDetailsViewController(coder: coder, movieId: movieId)
        }) else {
            fatalError("Failed to load MovieDetailsViewController from storyboard.")
        }
        
        let navVC = UINavigationController(rootViewController: vc)
        navVC.modalTransitionStyle = .crossDissolve
        navVC.modalPresentationStyle = .fullScreen
        navVC.navigationBar.isHidden = true
        viewController?.navigationController?.present(navVC, animated: true, completion: nil)
    }
}
