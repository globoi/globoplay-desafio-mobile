//
//  HomePresenter.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

protocol HomePresentationLogic {
    func presentFetchedMovies(response: HomeModels.FetchMovies.Response)
    func presentError(_ error: Error)
}

class HomePresenter: HomePresentationLogic {

    weak var viewController: HomeDisplayLogic?
    
    func presentFetchedMovies(response: HomeModels.FetchMovies.Response) {
        
        var displayedMovies: [HomeModels.FetchMovies.ViewModel.DisplayedMovie] = []
        
        for movie in response.movies {
            let posterPathUrl = Constants.baseImagesURL + "w185/\(movie.posterPath ?? "")"
            
            let displayedMovie = HomeModels
                .FetchMovies
                .ViewModel
                .DisplayedMovie(title: movie.title ?? "Unknown",
                                posterPath: posterPathUrl)
            
            displayedMovies.append(displayedMovie)
        }
        let viewModel = HomeModels.FetchMovies.ViewModel(displayedStatements: displayedMovies)
        viewController?.displayFetchedMovies(viewModel: viewModel, forGenre: response.genre)
    }
    
    func presentError(_ error: Error) {
        viewController?.displayError(withMessage: error.localizedDescription)
    }
}
