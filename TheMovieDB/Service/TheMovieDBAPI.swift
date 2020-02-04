//
//  TheMovieDBAPI.swift
//  TheMovieDB
//
//  Created by Edwy Lugo on 03/02/20.
//  Copyright © 2020 Edwy Lugo. All rights reserved.
//

import Foundation
import Alamofire
import AlamofireObjectMapper

class TheMovieDBAPI {
    
    static private let API_KEY = "c5850ed73901b8d268d0898a8a9d8bff"
    static private let MOVIE_LIST_URL = "https://api.themoviedb.org/3/search/movie?"
    static private let MOVIE_LIST_LAST = "https://api.themoviedb.org/3/movie/popular?"
    static private let MOVIE_DETAILS = "https://api.themoviedb.org/3/movie/"
    static private let MOVIE_GENRE = "https://api.themoviedb.org/3/genre/"
    
    let BASE_MOVIE_POSTER_URL = "https://image.tmdb.org/t/p/w500"
    static private let NOWPLAYING_FETCH_REQUEST = "NowPlayingMovies"
    static private let TOPRATED_FETCH_REQUEST = "TopRatedMovies"
    static private let NO_NETWORK_ERROR = "Please check connectivity to network."
    static private let MOVIE_FETCH_FAIL_ALERT_TITLE = "Error"
    
    
    //Carrega Filmes
    class func loadMovies(onComplete: @escaping (MoviesInfo?) -> Void) {
        
        let url: String
        url = MOVIE_LIST_LAST + "api_key=\(API_KEY)"
        Alamofire.request(url).responseObject { (response: DataResponse<MoviesInfo>) in
            onComplete(response.result.value)
            
        }

    }
    
    //Carrega Generos
    class func loadGenres(movie_id: [Int] = [], onComplete: @escaping (MoviesDetail?) -> Void) {
        
        let url: String
        url = MOVIE_DETAILS + "\(movie_id)" + "?api_key=\(API_KEY)"
        
        Alamofire.request(url).responseObject { (response: DataResponse<MoviesDetail>) in
            onComplete(response.result.value)
            
        }
        
    }
    
}

