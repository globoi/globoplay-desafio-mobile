//
//  MovieDetailsWorker.swift
//  My Movies
//
//  Created by Rafael Valer on 11/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

class MovieDetailsWorker {

    enum MovieDetailsResponse {
        case success(Movie)
        case error(Error)
    }
    
    enum MovieRecommendationsResponse {
        case success([Movie])
        case error(Error)
    }

    func fetchMovieDetails(request: MovieDetailsModels.FetchMovieDetails.Request,
                           completion: @escaping (MovieDetailsResponse) -> Void) {
        
        APIClient(manager: APIManager.shared).getMovieDetails(request.movieId) { (response) in
            
            switch response {
            case .success(movie: let movie):
                completion(.success(movie)); break
            case .failed(error: let error):
                completion(.error(error)); break
            }
        }
    }
    
    func fetchMovieRecommendations(request: MovieDetailsModels.FetchMovieRecommendations.Request,
                                   completion: @escaping (MovieRecommendationsResponse) -> Void) {
        
        APIClient(manager: APIManager.shared).gerMovieRecomendations(request.movieId) { (response) in
            
            switch response {
            case .success(movies: let movies):
                completion(.success(movies)); break
            case .failed(error: let error):
                completion(.error(error)); break
            }
        }
    }
}
