//
//  SearchPresenter.swift
//  My Movies
//
//  Created by Rafael Valer on 17/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

protocol SearchPresentationLogic {
    func presentFetchedMovies(response: SearchModels.FetchSearchMovies.Response)
    func presentError(_ error: Error)
}

class SearchPresenter: SearchPresentationLogic {
    
    weak var viewController: SearchDisplayLogic?
    
    // MARK: - SearchPresentationLogic
    
    func presentFetchedMovies(response: SearchModels.FetchSearchMovies.Response) {
        
        var displayedMovies: [SearchModels.FetchSearchMovies.ViewModel.DisplayedMovie] = []
        
        for movie in response.movies {
            let posterPathURL = Constants.baseImagesURL + "w185/\(movie.posterPath ?? "")"
            
            let displayedMovie = SearchModels
                .FetchSearchMovies
                .ViewModel
                .DisplayedMovie(title: movie.title ?? "Unknown",
                                posterPath: posterPathURL)
            
            displayedMovies.append(displayedMovie)
        }
        
        let viewModel = SearchModels.FetchSearchMovies.ViewModel(displayedMovies: displayedMovies)
        viewController?.displayFetchedSearchMovies(viewModel: viewModel)
    }
    
    func presentError(_ error: Error) {
        let message = "Desculpe, parece que ocorreu um erro. \nTente novamente mais tarde."
        viewController?.displayErrorMessage(message)
    }
}
