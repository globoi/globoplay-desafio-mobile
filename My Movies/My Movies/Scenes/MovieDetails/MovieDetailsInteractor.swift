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
    func addOrRemoveMovieFromFavorites(_ movieId: Int)
    func checkIfMovieIsFavorite(_ movieId: Int)
}

protocol MovieDetailsDataStore {
    var movie: Movie? { get set }
    var recommendedMovies: [Movie] { get set }
    var isMovieFavorited: Bool { get set }
}

class MovieDetailsInteractor: MovieDetailsBusinessLogic, MovieDetailsDataStore {
    
    var presenter: MovieDetailsPresentationLogic?
    var worker: MovieDetailsWorker = MovieDetailsWorker()
    
    private func addMovieToFavorites() {
        guard let movie = movie, let movieId = movie.id else { return }
        
        UserPreferences.shared.addFavoriteMovie(movieId: String(movieId), title: movie.title, posterPath: movie.posterPath)
        self.isMovieFavorited = true
        presenter?.presentIsMovieOnFavorites(isMovieFavorited)
    }
    
    private func removeMovieFromFavorites(_ movieId: Int) {
        UserPreferences.shared.removeFavoriteMovie(movieId: String(movieId))
        self.isMovieFavorited = false
        presenter?.presentIsMovieOnFavorites(isMovieFavorited)
    }
    
    // MARK: - MovieDetailsDataStore
    var movie: Movie?
    var recommendedMovies: [Movie] = []
    var isMovieFavorited: Bool = false
    
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
                self?.recommendedMovies = movies
                self?.presenter?.presentFetchedRecommendations(response: MovieDetailsModels.FetchMovieRecommendations.Response(movies: movies))
                break
            case .error(error: let error):
                self?.presenter?.presentError(error); break
            }
        }
    }
    
    func addOrRemoveMovieFromFavorites(_ movieId: Int) {
        if isMovieFavorited {
            removeMovieFromFavorites(movieId)
        } else {
            addMovieToFavorites()
        }
        
        NotificationCenter.default.post(name: .FavoriteMoviesUpdated, object: nil)
    }
    
    func checkIfMovieIsFavorite(_ movieId: Int) {
        let isMovieOnFavorites = UserPreferences.shared.isMovieOnFavorites(String(movieId))
        self.isMovieFavorited = isMovieOnFavorites
        presenter?.presentIsMovieOnFavorites(isMovieOnFavorites)
    }
}
