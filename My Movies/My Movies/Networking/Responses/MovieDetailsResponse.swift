//
//  MovieDetailsResponse.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

/// JSON parsing when dealing with Movie Details responses
enum MovieDetailsResponse {
    case success(movie: Movie)
    case failed(error: AppError)
    
    /// Parses the data from API response
    /// - Parameter jsonData: JSON Data
    /// - Returns: parsed MovieDetailsResponse
    static func parse(_ jsonData: Data) -> MovieDetailsResponse {
        guard let movie = Movie(data: jsonData) else {
            debugPrint("Decoding Error: MovieDetailsResponse")
            return .failed(error: ApplicationError.parseError)
        }
        
        return .success(movie: movie)
    }
}
