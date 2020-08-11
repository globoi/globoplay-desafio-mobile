//
//  HomeWorker.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

class HomeWorker {
    
    enum MoviesResponse {
        case success([Movie])
        case error(Error)
    }

    func fetchMovies(request: HomeModels.FetchMovies.Request,
                     completion: @escaping (MoviesResponse) -> Void) {
        
        APIClient(manager: APIManager.shared).discoverMovies(onPage: request.page, withGenre: request.genre) { (response) in
            
            switch response {
            case .success(movies: let movies):
                completion(.success(movies))
                break
            case .failed(error: let error):
                completion(.error(error))
                break
            }
        }
    }
}
