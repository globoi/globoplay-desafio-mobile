//
//  MyListRouter.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation
import UIKit

@objc protocol MyListRoutingLogic {
    func navigateToMovieDetails(atIndexPath indexPath: IndexPath)
}

protocol MyListDataPassing {
    var dataStore: MyListDataStore? { get set }
}

class MyListRouter: NSObject, MyListRoutingLogic, MyListDataPassing {

    weak var viewController: MyListViewController?
    var dataStore: MyListDataStore?
    
    // MARK: - MyListRoutingLogic
    func navigateToMovieDetails(atIndexPath indexPath: IndexPath) {
        
        guard let movieId = dataStore?.favoriteMovies[indexPath.row].id, let vc = viewController?.storyboard?.instantiateViewController(identifier: MovieDetailsViewController.identifier, creator: { coder in
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
