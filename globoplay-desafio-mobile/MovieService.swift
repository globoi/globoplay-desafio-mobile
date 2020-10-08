//
//  MovieService.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 05/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import UIKit
import Alamofire

class MovieService: NSObject {
//    "https://api.themoviedb.org/3/movie/upcoming?api_key=2a0eb1c99630d71df118961ee0b5864e&language=en-US&page=1
    
    static func getUpcomingMovies(completion: @escaping ([Movie]?, Error?) -> Void){
        let URL = CONST.API_CONSTANTS.BASE_URL + CONST.API_CONSTANTS.MOVIE + CONST.API_CONSTANTS.UPCOMING + CONST.API_CONSTANTS.API_KEY + CONST.API_CONSTANTS.LANGUAGE_EN
        
        var movieList : [Movie]?
        
        AF.request(URL).responseData { response in
            switch response.result {
            case .failure(let error):
                print(error)
            case .success(let data):
                do {
                    print(data)
                    //let pageData = try JSONDecoder().decode(Movie.self, from: data)
                    let root = try JSONDecoder().decode(Root.self, from: data)
                    movieList = root.results
                    completion(movieList, nil)
                } catch let error {
                    completion(nil, error)
                    print(error)
                }
                
            }
        }
    }
    
    static func getPopularMovies(completion: @escaping ([Movie]?, Error?) -> Void){
        let URL = CONST.API_CONSTANTS.BASE_URL + CONST.API_CONSTANTS.MOVIE + CONST.API_CONSTANTS.POPULAR + CONST.API_CONSTANTS.API_KEY + CONST.API_CONSTANTS.LANGUAGE_EN
        
        var movieList : [Movie]?
        
        AF.request(URL).responseData { response in
            switch response.result {
            case .failure(let error):
                print(error)
            case .success(let data):
                do {
                    print(data)
                    //let pageData = try JSONDecoder().decode(Movie.self, from: data)
                    let root = try JSONDecoder().decode(Root.self, from: data)
                    movieList = root.results
                    completion(movieList, nil)
                } catch let error {
                    completion(nil, error)
                    print(error)
                }
                
            }
        }
    }
    
    static func getNowPlayingMovies(completion: @escaping ([Movie]?, Error?) -> Void){
        let URL = CONST.API_CONSTANTS.BASE_URL + CONST.API_CONSTANTS.MOVIE + CONST.API_CONSTANTS.NOW_PLAYING + CONST.API_CONSTANTS.API_KEY + CONST.API_CONSTANTS.LANGUAGE_EN
        
        var movieList : [Movie]?
        
        AF.request(URL).responseData { response in
            switch response.result {
            case .failure(let error):
                print(error)
            case .success(let data):
                do {
                    print(data)
                    //let pageData = try JSONDecoder().decode(Movie.self, from: data)
                    let root = try JSONDecoder().decode(Root.self, from: data)
                    movieList = root.results
                    completion(movieList, nil)
                } catch let error {
                    completion(nil, error)
                    print(error)
                }
                
            }
        }
    }
    
//    https://api.themoviedb.org/3/movie/297762/videos?api_key=2a0eb1c99630d71df118961ee0b5864e&language=en-US
    
    static func getTrailerKey(id: String, completion: @escaping (String?, Error?) -> Void){
        
        let URL = CONST.API_CONSTANTS.BASE_URL + CONST.API_CONSTANTS.MOVIE + id + CONST.API_CONSTANTS.VIDEO + CONST.API_CONSTANTS.API_KEY
        
        print("[DEBUG] - \(URL)")
        
        var key: String?
        
        AF.request(URL).responseData { response in
            switch response.result {
            case .failure(let error):
                print(error)
            case .success(let data):
                do {
                    //print(data)
                    let root = try JSONDecoder().decode(VideoRoot.self, from: data)
                    key = root.results.first?.key ?? ""
                    completion(key, nil)
                } catch let error {
                    completion(nil, error)
                    print(error)
                }
                
            }
        }  
    }
}
