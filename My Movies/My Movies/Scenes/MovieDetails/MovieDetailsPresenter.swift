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
    func presentFetchedTrailer(response: MovieDetailsModels.FetchMovieTrailer.Response)
    func presentError(_ error: Error)
    func presentIsMovieOnFavorites(_ isFavorite: Bool)
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
        if let genres = movie.genres, !genres.isEmpty {
            genresText = "Gêneros: " + genres.reduce("", { ($0.isEmpty ? "" : $0 + ", ") + $1.name })
        }
        
        var productionCountriesText = ""
        if let countries = movie.productionCountries, !countries.isEmpty {
            productionCountriesText = "Países: " + countries.reduce("", { ($0.isEmpty ? "" : $0 + ", ") + $1.name })
        }
        
        var productionCompaniesText = ""
        if let companies = movie.productionCompanies, !companies.isEmpty {
            productionCompaniesText = "Produtoras: " + companies.reduce("", { ($0.isEmpty ? "" : $0 + ", ") + $1.name })
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
                            productionCountries: productionCountriesText,
                            productionCompanies: productionCompaniesText)
        
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
        viewController?.displayErrorAlert(withTitle: "Erro", message: "Desculpe, parece que ocorreu algo inesperado. Por favor, tente novamente mais tarde.")
    }
    
    func presentIsMovieOnFavorites(_ isFavorite: Bool) {
        let image = UIImage(named: isFavorite ? "baseline-check" : "baseline-star_rate")?.withTintColor(.white)
        let text = isFavorite ? "Adicionado" : "Minha Lista"
        viewController?.displayFavoriteButton(withImage: image, text: text)
    }

    func presentFetchedTrailer(response: MovieDetailsModels.FetchMovieTrailer.Response) {
        guard let url = URL(string: "https://www.youtube.com/watch?v=\(response.trailer.key)?autoplay=1&playsinline=1") else {
            viewController?.displayErrorAlert(withTitle: "Erro de URL", message: "Ocorreu um erro ao executar o Trailer. Tente novamente mais tarde.")
            return
        }
        
        viewController?.displayTrailer(withURL: url)
    }
}
