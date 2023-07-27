//
//  APIURLs.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import Foundation

//https://api.themoviedb.org/3/movie/{movie_id}
//Detalhes do filme

//https://api.themoviedb.org/3/movie/{movie_id}/credits
//Elenco do filme

//https://api.themoviedb.org/3/tv/{series_id}
//detalhes da série

//https://api.themoviedb.org/3/tv/{series_id}/credits
//elenco da série

let baseURL = "https://api.themoviedb.org/3"
let posterURL = "https://image.tmdb.org/t/p/original"

let headers = [
  "accept": "application/json",
  "Authorization": "Bearer \(auth.TMDBAPI.bearerToken.rawValue)"
]

enum APIURLs{
    
    case getMovies
    case getTVShows
    
    case detailsMovie(Int)
    case detailsTVShow(Int)
    
    case creditsMovie(Int)
    case creditsTvShow(Int)
    
    func request() throws -> URLRequest {
        switch self {
        case .getMovies:
            return createRequest(withURL: "\(baseURL)/movie/now_playing")
        case .getTVShows:
            return createRequest(withURL: "\(baseURL)/tv/airing_today")
        case let .detailsMovie(movieId):
            return createRequest(withURL: "\(baseURL)/movie/\(movieId)")
        case let .detailsTVShow(tvShowId):
            return createRequest(withURL: "\(baseURL)/tv/\(tvShowId)")
        case let .creditsMovie(movieId):
            return createRequest(withURL: "\(baseURL)/movie/\(movieId)/credits")
        case let .creditsTvShow(tvShowId):
            return createRequest(withURL: "\(baseURL)/tv/\(tvShowId)/credits")
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
