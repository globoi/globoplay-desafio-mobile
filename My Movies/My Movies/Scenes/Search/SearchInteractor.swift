//
//  SearchInteractor.swift
//  My Movies
//
//  Created by Rafael Valer on 17/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

protocol SearchBusinessLogic {
    func fetchSearchMovies(_ queryText: String)
}

protocol SearchDataStore {
    var searchedMovies: [Movie] { get set }
}

class SearchInteractor: SearchBusinessLogic, SearchDataStore {
    
    var presenter: SearchPresentationLogic?
    var worker: SearchWorker = SearchWorker()
    
    // MARK: - MyListDataStore
    var searchedMovies: [Movie] = []
    
    // MARK: - MyListBusinessLogic
    func fetchSearchMovies(_ queryText: String) {
        
        guard queryText.isEmpty == false else {
            presenter?.presentFetchedMovies(response: SearchModels.FetchSearchMovies.Response(movies: []))
            return
        }
        
        let request = SearchModels.FetchSearchMovies.Request(queryText: queryText)
        
        worker.fetchMovies(request: request) { [weak self] (response) in
            switch response {
            case .success(let movies):
                self?.searchedMovies = movies
                let response = SearchModels.FetchSearchMovies.Response(movies: movies)
                self?.presenter?.presentFetchedMovies(response: response)
                break
            case .error(let error):
                self?.presenter?.presentError(error); break
            }
        }
    }
}
