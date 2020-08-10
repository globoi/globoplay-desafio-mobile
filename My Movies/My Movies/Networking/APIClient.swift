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
        manager.request(method: .get, endPoint: .getMovieDetails(movieId)) { (response) in
            switch response {
            case .sucess(let data):
                completion(MovieDetailsResponse.parse(data))
            case .error(let appError):
                completion(MovieDetailsResponse.failed(error: appError))
            }
        }
    }
    
    /// Gets movies based on query params
    /// - Parameter completion: Closure with request completion
    func discoverMovies(onPage page: Int,
                        withGenre genre: Int,
                        completion: @escaping (MoviesResponse) -> Void) {
        manager.request(method: .get, endPoint: .discoverMovies(page, genre)) { (response) in
            switch response {
            case .sucess(let data):
                completion(MoviesResponse.parse(data))
            case .error(let appError):
                completion(MoviesResponse.failed(error: appError))
            }
        }
    }
}
