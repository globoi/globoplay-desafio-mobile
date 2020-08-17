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
    case discoverMovies(Int, Int?)
    case getMovieRecommendations(String)
    case getMovieTrailer(String)
    case getSearchMovies(String)
    
    func getPath() -> String {
        switch self {
        case .getMovieDetails(let movieId):
            return "\(Constants.baseURL)/movie/\(movieId)?api_key=\(Constants.apiKey)&language=pt-BR"
        case .discoverMovies(let page, let genre):
            var path = "\(Constants.baseURL)/discover/movie/?api_key=\(Constants.apiKey)&sort_by=popularity.desc&page=\(page)"
            if let genre = genre {
                path += "&with_genres=\(genre)"
            }
            return path
        case .getMovieRecommendations(let movieId):
            return "\(Constants.baseURL)/movie/\(movieId)/recommendations?api_key=\(Constants.apiKey)"
        case .getMovieTrailer(let movieId):
            return "\(Constants.baseURL)/movie/\(movieId)/videos?api_key=\(Constants.apiKey)"
        case .getSearchMovies(let searchString):
            let queryText = searchString.replacingOccurrences(of: " ", with: "%20")
            return "\(Constants.baseURL)/search/movie?api_key=\(Constants.apiKey)&query=\(queryText)"
        }
    }
    
    func getHttpMethod() -> HttpMethod {
        switch self {
        case .getMovieDetails,
             .discoverMovies,
             .getMovieRecommendations,
             .getMovieTrailer,
             .getSearchMovies:
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
