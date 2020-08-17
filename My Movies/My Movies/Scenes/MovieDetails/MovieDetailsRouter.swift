//
//  MovieDetailsRouter.swift
//  My Movies
//
//  Created by Rafael Valer on 11/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

@objc protocol MovieDetailsRoutingLogic {
    func dismiss()
    func navigateToPreviousView()
    func navigateToMovieDetails(atIndexPath indexPath: IndexPath)
}

protocol MovieDetailsDataPassing {
    var dataStore: MovieDetailsDataStore? { get set }
}

class MovieDetailsRouter: NSObject, MovieDetailsRoutingLogic, MovieDetailsDataPassing {
    
    weak var viewController: MovieDetailsViewController?
    var dataStore: MovieDetailsDataStore?
    
    // MARK: - MovieDetailsRoutingLogic
    func navigateToMovieDetails(atIndexPath indexPath: IndexPath) {
        
        guard let movieId = dataStore?.recommendedMovies[indexPath.row].id, let vc = viewController?.storyboard?.instantiateViewController(identifier: MovieDetailsViewController.identifier, creator: { coder in
            return MovieDetailsViewController(coder: coder, movieId: movieId)
        }) else {
            fatalError("Failed to load MovieDetailsViewController from storyboard.")
        }
        
        vc.modalTransitionStyle = .crossDissolve
        viewController?.show(vc, sender: nil)
    }
    
    func navigateToPreviousView() {
        DispatchQueue.main.async { [weak self] in
            self?.viewController?.navigationController?.popViewController(animated: true)
        }
    }
    
    func dismiss() {
        DispatchQueue.main.async { [weak self] in
            self?.viewController?.navigationController?.dismiss(animated: true, completion: nil)
        }
    }
}
