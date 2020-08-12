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
    case getMovieRecommendations(String)
    
    func getPath() -> String {
        switch self {
        case .getMovieDetails(let movieId):
            return "\(Constants.baseURL)/movie/\(movieId)?api_key=\(Constants.apiKey)&language=pt-BR"
        case .discoverMovies(let page, let genre):
            return "\(Constants.baseURL)/discover/movie/?api_key=\(Constants.apiKey)&sort_by=popularity.desc&page=\(page)&with_genres=\(genre)"
        case .getMovieRecommendations(let movieId):
            return "\(Constants.baseURL)/movie/\(movieId)/recommendations?api_key=\(Constants.apiKey)"
        }
    }
    
    func getHttpMethod() -> HttpMethod {
        switch self {
        case .getMovieDetails,
             .discoverMovies,
             .getMovieRecommendations:
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
