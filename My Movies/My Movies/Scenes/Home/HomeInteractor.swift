//
//  HomeInteractor.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

protocol HomeBusinessLogic {
    func fetchMovies(withPage page: Int, genre: Int)
}

protocol HomeDataStore {
    var movies: [Int: [Movie]] { get set }
}

class HomeInteractor: HomeBusinessLogic, HomeDataStore {

    var presenter: HomePresentationLogic?
    var worker: HomeWorker = HomeWorker()
    
    // MARK: - HomeDataStore
    
    var movies: [Int: [Movie]] = [:]
    
    // MARK: - HomeBusinessLogic
    
    func fetchMovies(withPage page: Int, genre: Int) {
        let request = HomeModels.FetchMovies.Request(page: page, genre: genre)
        
        worker.fetchMovies(request: request) { [weak self] (response) in
            switch response {
            case .success(let movies):
                self?.movies[genre] = movies
                self?.presenter?.presentFetchedMovies(response: HomeModels.FetchMovies.Response(genre: genre, movies: movies))
                break
            case .error(let error):
                self?.presenter?.presentError(error)
                break
            }
        }
    }
}
