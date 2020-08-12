//
//  MovieDetailsInteractor.swift
//  My Movies
//
//  Created by Rafael Valer on 11/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

protocol MovieDetailsBusinessLogic {
    func fetchMovieDetails(_ movieId: Int)
    func fetchMovieRecommendations(_ movieId: Int)
}

protocol MovieDetailsDataStore {
    var movie: Movie? { get set }
}

class MovieDetailsInteractor: MovieDetailsBusinessLogic, MovieDetailsDataStore {
    
    var presenter: MovieDetailsPresentationLogic?
    var worker: MovieDetailsWorker = MovieDetailsWorker()
    
    // MARK: - MovieDetailsDataStore
    var movie: Movie?
    
    // MARK: - MovieDetailsBusinessLogic
    func fetchMovieDetails(_ movieId: Int) {
        let request = MovieDetailsModels.FetchMovieDetails.Request(movieId: String(movieId))
        
        worker.fetchMovieDetails(request: request) { [weak self] (response) in
            switch response {
            case .success(let movie):
                self?.movie = movie
                self?.presenter?.presentFetchedMovieDetails(response: MovieDetailsModels.FetchMovieDetails.Response(movie: movie))
                break
            case .error(let error):
                self?.presenter?.presentError(error)
                break
            }
        }
    }
    
    func fetchMovieRecommendations(_ movieId: Int) {
        let request = MovieDetailsModels.FetchMovieRecommendations.Request(movieId: String(movieId))
        
        worker.fetchMovieRecommendations(request: request) { [weak self] (response) in
            switch response {
            case .success(movies: let movies):
                self?.presenter?.presentFetchedRecommendations(response: MovieDetailsModels.FetchMovieRecommendations.Response(movies: movies))
                break
            case .error(error: let error):
                self?.presenter?.presentError(error); break
            }
        }
    }
}
