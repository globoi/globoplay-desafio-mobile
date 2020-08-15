//
//  MyListPresenter.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

protocol MyListPresentationLogic {
    func presentFavoriteMovies(response: MyListModels.FetchFavoriteMovies.Response)
    func presentEmptyMessage()
}

class MyListPresenter: MyListPresentationLogic {
    
    weak var viewController: MyListDisplayLogic?
    
    //MARK: - MyListPresentationLogic
    
    func presentEmptyMessage() {
        viewController?.displayMessage("Você ainda não possui nenhum filme\n na sua lista de favoritos.")
    }
    
    func presentFavoriteMovies(response: MyListModels.FetchFavoriteMovies.Response) {
        var displayedMovies: [MyListModels.FetchFavoriteMovies.ViewModel.DisplayedMovie] = []
        
        for movie in response.favoriteMovies {
            let posterPathURL = Constants.baseImagesURL + "w185/\(movie.posterPath ?? "")"
            
            let displayedMovie = MyListModels
                .FetchFavoriteMovies
                .ViewModel
                .DisplayedMovie(title: movie.title ?? "Unknown",
                                posterPath: posterPathURL)
            
            displayedMovies.append(displayedMovie)
        }
        let viewModel = MyListModels.FetchFavoriteMovies.ViewModel(displayedMovies: displayedMovies)
        viewController?.displayFavoriteMovies(viewModel: viewModel)
    }
}
