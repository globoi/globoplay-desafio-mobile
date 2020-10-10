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
    
    static func getUpcomingMovies(completion: @escaping ([Movie]?, Error?) -> Void){
        let URL = CONST.API_CONSTANTS.BASE_URL +  CONST.API_CONSTANTS.UPCOMING + CONST.API_CONSTANTS.API_KEY + CONST.API_CONSTANTS.LANGUAGE_BR
        
        var movieList : [Movie]?
        
        AF.request(URL).responseData { response in
            switch response.result {
            case .failure(let error):
                print(error)
            case .success(let data):
                do {
                    print(data)
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
        let URL = CONST.API_CONSTANTS.BASE_URL +  CONST.API_CONSTANTS.POPULAR + CONST.API_CONSTANTS.API_KEY + CONST.API_CONSTANTS.LANGUAGE_BR
        
        var movieList : [Movie]?
        
        AF.request(URL).responseData { response in
            switch response.result {
            case .failure(let error):
                print(error)
            case .success(let data):
                do {
                    print(data)
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
        let URL = CONST.API_CONSTANTS.BASE_URL + CONST.API_CONSTANTS.NOW_PLAYING + CONST.API_CONSTANTS.API_KEY + CONST.API_CONSTANTS.LANGUAGE_BR
        
        var movieList : [Movie]?
        
        AF.request(URL).responseData { response in
            switch response.result {
            case .failure(let error):
                print(error)
            case .success(let data):
                do {
                    print(data)
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
    
    
    static func getTrailerKey(id: String, completion: @escaping (String?, Error?) -> Void){
        
        let URL = CONST.API_CONSTANTS.BASE_URL +  id + CONST.API_CONSTANTS.VIDEO + CONST.API_CONSTANTS.API_KEY
        
        
        var key: String?
        
        AF.request(URL).responseData { response in
            switch response.result {
            case .failure(let error):
                print(error)
            case .success(let data):
                do {
                    let root = try JSONDecoder().decode(VideoRoot.self, from: data)
                    key = root.results.first?.key ?? ""
                    completion(key, nil)
                    print(key)
                    
                } catch let error {
                    completion(nil, error)
                    print(error)
                }
                
            }
        }  
    }
    
    
    static func getMovieDetails(id: String, completion: @escaping (MovieDetails?, Error?) -> Void){
        
        let URL = CONST.API_CONSTANTS.BASE_URL + id + CONST.API_CONSTANTS.QUERY + CONST.API_CONSTANTS.API_KEY + CONST.API_CONSTANTS.LANGUAGE_BR
        
        var movieDetailsList : MovieDetails?
        
        AF.request(URL).responseData { response in
            switch response.result {
            case .failure(let error):
                print(error)
            case .success(let data):
                do {
                    let root = try JSONDecoder().decode(MovieDetails.self, from: data)
                    movieDetailsList = root
                    completion(movieDetailsList, nil)
                } catch let error {
                    completion(nil, error)
                    print(error)
                }
                
            }
        }
    }
    
    static func getRelatedMovies(id: String, completion: @escaping ([Movie]?, Error?) -> Void){
        
        let URL = CONST.API_CONSTANTS.BASE_URL + id + CONST.API_CONSTANTS.RELATED + CONST.API_CONSTANTS.API_KEY + CONST.API_CONSTANTS.LANGUAGE_BR
        
        
        var relatedMovies : [Movie]?
        
        AF.request(URL).responseData { response in
            switch response.result {
            case .failure(let error):
                print(error)
            case .success(let data):
                do {
                    let root = try JSONDecoder().decode(Root.self, from: data)
                    relatedMovies = root.results
                    completion(relatedMovies, nil)
                } catch let error {
                    completion(nil, error)
                    print(error)
                }
                
            }
        }
    }
}
