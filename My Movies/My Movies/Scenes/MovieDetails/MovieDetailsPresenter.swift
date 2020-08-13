//
//  MovieDetailsPresenter.swift
//  My Movies
//
//  Created by Rafael Valer on 11/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation
import UIKit

protocol MovieDetailsPresentationLogic {
    func presentFetchedMovieDetails(response: MovieDetailsModels.FetchMovieDetails.Response)
    func presentFetchedRecommendations(response: MovieDetailsModels.FetchMovieRecommendations.Response)
    func presentError(_ error: Error)
}

class MovieDetailsPresenter: MovieDetailsPresentationLogic {

    weak var viewController: MovieDetailsDisplayLogic?
    
    // MARK: - MovieDetailsPresentationLogic
    
    func presentFetchedMovieDetails(response: MovieDetailsModels.FetchMovieDetails.Response) {
        
        let movie = response.movie
        let posterPathURL = Constants.baseImagesURL + "w185/\(movie.posterPath ?? "")"
        let backdropPathURL = Constants.baseImagesURL + "w500/\(movie.backdropPath ?? "")"
        let releaseDateText = "Data de Lançamento: \(movie.releaseDate?.convert(fromDateFormat: "yyyy-MM-dd", toDateFormat: "dd/MM/yyyy") ?? "")"
        let originalTitleText = "Título Original: \(movie.originalTitle ?? "")"
        let scoreText = "Avaliação: " + String(movie.voteAverage ?? 0) + "/10"
        
        var genresText = ""
        if let genres = movie.genres {
            genresText = "Gêneros: " + genres.reduce("", { ($0.isEmpty ? "" : $0 + ", ") + $1.name })
        }
        
        var productionCountriesText = ""
        if let countries = movie.productionCountries {
            productionCountriesText = "Países: " + countries.reduce("", { ($0.isEmpty ? "" : $0 + ", ") + $1.name })
        }
        
        let displayedMovie = MovieDetailsModels.FetchMovieDetails
            .ViewModel
            .DisplayedMovie(title: movie.title ?? "",
                            posterPath: posterPathURL,
                            backdropPath: backdropPathURL,
                            overview: movie.overview ?? "",
                            type: "Filme", releaseDate: releaseDateText,
                            genres: genresText, originalTitle: originalTitleText,
                            score: scoreText,
                            productionCountries: productionCountriesText)
        
        let viewModel = MovieDetailsModels.FetchMovieDetails.ViewModel(displayedMovie: displayedMovie)
        viewController?.displayFetchedMovieDetails(viewModel: viewModel)
    }
    
    func presentFetchedRecommendations(response: MovieDetailsModels.FetchMovieRecommendations.Response) {
        
        var displayedMovies: [MovieDetailsModels.FetchMovieRecommendations.ViewModel.DisplayedMovie] = []
        
        for movie in response.movies {
            let posterPathURL = Constants.baseImagesURL + "w185/\(movie.posterPath ?? "")"
            
            let displayedMovie = MovieDetailsModels
                .FetchMovieRecommendations
                .ViewModel
                .DisplayedMovie(title: movie.title ?? "Unknown",
                                posterPath: posterPathURL)
            
            displayedMovies.append(displayedMovie)
            
            // limiting the number of recommendations to only 6
            if displayedMovies.count >= 6 { break }
        }
        let viewModel = MovieDetailsModels.FetchMovieRecommendations.ViewModel(displayedMovies: displayedMovies)
        viewController?.displayFetchedMovieRecommendations(viewModel: viewModel)
    }
    
    func presentError(_ error: Error) {
        
    }
}
