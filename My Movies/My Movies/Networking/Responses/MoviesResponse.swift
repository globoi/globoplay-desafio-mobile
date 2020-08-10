//
//  MoviesResponse.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

/// JSON parsing when dealing with Movies responses
enum MoviesResponse {
    case success(movies: [Movie])
    case failed(error: AppError)
    
    /// Parses the data from API response
    /// - Parameter jsonData: JSON Data
    /// - Returns: parsed MoviesResponse
    static func parse(_ jsonData: Data) -> MoviesResponse {
        guard let results = MoviesResults(data: jsonData) else {
            debugPrint("Decoding Error: MoviesResponse")
            return .failed(error: ApplicationError.parseError)
        }
        
        return .success(movies: results.movies)
    }
}
