//
//  MoviesListInteractor.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 25/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import Foundation

protocol MoviesListInteractorProtocol: class {
    func getMostPopularTVShows()
    func getMostPopularMovies()
    func getTopRatedMovies()
}

class MoviesListInteractor: MoviesListInteractorProtocol {
    var presenter: MoviesListPresenterProtocol!
    
    private let baseURL: String = "https://api.themoviedb.org/3"
    private let apiKey: String = "c23dc7d4e85db40fa72c8c0da28e2bfb"
    
    func getMostPopularTVShows() {
        var urlStr = "\(self.baseURL)/discover/tv?"
        urlStr += "certification_country=BR&"
        urlStr += "certification=R&"
        urlStr += "sort_by=popularity.desc&"
        urlStr += "language=pt-BR&"
        urlStr += "api_key=\(self.apiKey)"
        
        self.sendRequest(withURL: urlStr, withSection: "tvShows")
    }
    
    func getTopRatedMovies() {
        var urlStr = "\(self.baseURL)/movie/top_rated?"
        urlStr += "language=pt-BR&"
        urlStr += "api_key=\(self.apiKey)"
        
        self.sendRequest(withURL: urlStr, withSection: "topRatedMovies")
    }
    
    func getMostPopularMovies() {
        var urlStr = "\(self.baseURL)/movie/popular?"
        urlStr += "language=pt-BR&"
        urlStr += "api_key=\(self.apiKey)"
        
        self.sendRequest(withURL: urlStr, withSection: "popularMovies")
    }
    
    private func sendRequest(withURL urlStr: String, withSection section: String) {
        guard let url = URL(string: urlStr) else { return }
        
        var request = URLRequest(url: url)
        request.httpMethod = "get"
        
        RequestHelper.send(request: request) { (error, dict) in
            if error == nil && dict != nil {
                if let results = dict!["results"] as? [[String : Any]] {
                    let movies = results.compactMap { (movie) in
                        Movie(dict: movie)
                    }
                    
                    self.presenter.update(withList: movies, withSectionIndex: section)
                }
            }
        }
    }
}
