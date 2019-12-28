//
//  MoviesDBRequest.swift
//  Movs
//  Created by gmmoraes on 20/12/19.
//  Copyright © 2019 gmmoraes. All rights reserved.
//

import Foundation

class MoviesDBRequest: Request {

    static let sharedInstance = MoviesDBRequest()
    let popularMoviesUrl =  "https://api.themoviedb.org/3/movie/popular"
    let popularSeriesUrl =  "https://api.themoviedb.org/3/tv/popular"
    
    var popularMoviesNextPage: Int = 1
    var popularSeriesNextPage: Int = 1
    
    private lazy var baseURL: URL = {
      return URL(string: "https://api.themoviedb.org/3")!
    }()

    private override init() {
        super.init()
    }
    
    func getPopularMovies(manuallySectPage: Int? = nil, completion: @escaping (Info) -> () ){
        var stringNextPage = String(popularMoviesNextPage)
        if let manuallySectPage = manuallySectPage {
            stringNextPage = String(manuallySectPage)
        }
        let queryItems = [
            URLQueryItem(name: "api_key", value: "825389946ad345f364dea4bbb7293e10"),
            URLQueryItem(name: "language", value: "en-US"),
            URLQueryItem(name: "page", value: stringNextPage)
        ]
        requestApi(apiUrl: popularMoviesUrl, queryItems: queryItems, authorization: nil){ (resultObject) -> () in
            let jsonDecoder = JSONDecoder()
            do {
                let response = try jsonDecoder.decode(Info.self, from: resultObject)
                completion(response)
                
            } catch {
                DispatchQueue.main.async(execute: {
                    print("Unable to parse JSON response")
                    print(error)
                })
            }
        }
    }
    
    func getPopularTvSeries(manuallySectPage: Int? = nil, completion: @escaping (SeriesInfo) -> () ){
        var stringNextPage = String(popularSeriesNextPage)
        if let manuallySectPage = manuallySectPage {
            stringNextPage = String(manuallySectPage)
        }
        let queryItems = [
            URLQueryItem(name: "api_key", value: "825389946ad345f364dea4bbb7293e10"),
            URLQueryItem(name: "language", value: "en-US"),
            URLQueryItem(name: "page", value: stringNextPage)
        ]
        requestApi(apiUrl: popularSeriesUrl, queryItems: queryItems, authorization: nil){ (resultObject) -> () in
            let jsonDecoder = JSONDecoder()
            do {
                let response = try jsonDecoder.decode(SeriesInfo.self, from: resultObject)
                completion(response)
                
            } catch {
                DispatchQueue.main.async(execute: {
                    print("Unable to parse JSON response")
                    print(error)
                })
            }
        }
    }
    
    
    
    


}
