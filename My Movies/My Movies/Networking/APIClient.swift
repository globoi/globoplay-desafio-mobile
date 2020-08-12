//
//  APIClient.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

final class APIClient {
    
    let manager: APIManager
    
    init(manager: APIManager) {
        self.manager = manager
    }
    
    /// Gets the movie details
    /// - Parameters:
    ///   - movieId: Movie ID
    ///   - completion: Closure with request completion
    func getMovieDetails(_ movieId: String, completion: @escaping (MovieDetailsResponse) -> Void) {
        manager.request(endPoint: .getMovieDetails(movieId)) { (response) in
            switch response {
            case .sucess(let data):
                completion(MovieDetailsResponse.parse(data)); break
            case .error(let appError):
                completion(MovieDetailsResponse.failed(error: appError)); break
            }
        }
    }
    
    /// Gets movies based on query params
    /// - Parameters:
    ///   - page: Page number
    ///   - genre: Movie genre
    ///   - completion: Closure with request completion
    func discoverMovies(onPage page: Int,
                        withGenre genre: Int,
                        completion: @escaping (MoviesResponse) -> Void) {
        manager.request(endPoint: .discoverMovies(page, genre)) { (response) in
            switch response {
            case .sucess(let data):
                completion(MoviesResponse.parse(data)); break
            case .error(let appError):
                completion(MoviesResponse.failed(error: appError)); break
            }
        }
    }
    
    
    /// Gets movies recommendations based on a specific movie
    /// - Parameters:
    ///   - movieId: Movie ID
    ///   - completion: Closure with request completion
    func gerMovieRecomendations(_ movieId: String, completion: @escaping (MoviesResponse) -> Void) {
        
        manager.request(endPoint: .getMovieRecommendations(movieId)) { (response) in
            switch response {
            case .sucess(let data):
                completion(MoviesResponse.parse(data)); break
            case .error(let error):
                completion(MoviesResponse.failed(error: error)); break
            }
        }
    }
}
