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
    
    static func getPopularSeries(completion: @escaping ([Serie]?, Error?) -> Void){
        let URL = CONST.API_CONSTANTS.BASE_URL + CONST.API_CONSTANTS.TV + CONST.API_CONSTANTS.POPULAR + CONST.API_CONSTANTS.API_KEY + CONST.API_CONSTANTS.LANGUAGE_EN
        
        var serieList : [Serie]?
        
        AF.request(URL).responseData { response in
            switch response.result {
            case .failure(let error):
                print(error)
            case .success(let data):
                do {
                    print(data)
                    //let pageData = try JSONDecoder().decode(Movie.self, from: data)
                    let root = try JSONDecoder().decode(RootS.self, from: data)
                    serieList = root.results
                    completion(serieList, nil)
                } catch let error {
                    completion(nil, error)
                    print(error)
                }
                
            }
        }
    }
}
