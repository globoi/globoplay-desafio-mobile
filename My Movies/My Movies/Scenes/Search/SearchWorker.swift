//
//  SearchWorker.swift
//  My Movies
//
//  Created by Rafael Valer on 17/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

class SearchWorker {

    enum MoviesResponse {
       case success([Movie])
       case error(Error)
    }

    func fetchMovies(request: SearchModels.FetchSearchMovies.Request,
                    completion: @escaping (MoviesResponse) -> Void) {
       
        APIClient(manager: APIManager.shared).getSearchMovies(request.queryText) { (response) in
            switch response {
            case .success(movies: let movies):
                completion(.success(movies)); break
            case .failed(error: let error):
                completion(.error(error)); break
            }
        }
    }
    
    func discoverPopularMovies(completion: @escaping (MoviesResponse) -> Void) {
        APIClient(manager: APIManager.shared).discoverMovies(onPage: 1) { (response) in
            switch response {
            case .success(movies: let movies):
                completion(.success(movies)); break
            case .failed(error: let error):
                completion(.error(error)); break
            }
        }
    }
}
