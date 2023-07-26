//
//  APIURLs.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import Foundation

let baseURL = "https://api.themoviedb.org/3"
let posterURL = "https://image.tmdb.org/t/p/original"

let headers = [
  "accept": "application/json",
  "Authorization": "Bearer \(auth.TMDBAPI.bearerToken.rawValue)"
]

enum APIURLs{
    
    case getMovies
    case getTVShows
    
    func request() throws -> URLRequest {
        switch self {
        case .getMovies:
            return createRequest(withURL: "\(baseURL)/movie/now_playing")
        case .getTVShows:
            return createRequest(withURL: "\(baseURL)/tv/airing_today")
        }
    }
    
    private func createRequest(withURL url: String) -> URLRequest{
        var request = URLRequest(url: URL.init(string: url)!,
                                 cachePolicy: .useProtocolCachePolicy,
                                 timeoutInterval: 10.0)
        request.httpMethod = "GET"
        request.allHTTPHeaderFields = headers
        return request
    }
    
}
