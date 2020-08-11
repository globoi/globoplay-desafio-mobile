//
//  EndPoint.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

enum EndPoint {
    
    case getMovieDetails(String)
    case discoverMovies(Int, Int)
    
    func getPath() -> String {
        switch self {
        case .getMovieDetails(let movieId):
            return "\(Constants.baseURL)/movie/\(movieId)?api_key=\(Constants.apiKey)"
        case .discoverMovies(let page, let genre):
            return "\(Constants.baseURL)/discover/movie/?api_key=\(Constants.apiKey)&sort_by=popularity.desc&page=\(page)&with_genres=\(genre)"
        }
    }
    
    func getHttpMethod() -> HttpMethod {
        switch self {
        case .getMovieDetails(_),
             .discoverMovies:
            return .get
        }
    }
}

enum HttpMethod: String {
    
    case delete = "DELETE"
    case get = "GET"
    case post = "POST"
    case put = "PUT"
}
