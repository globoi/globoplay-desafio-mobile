//
//  MyListWorker.swift
//  My Movies
//
//  Created by Rafael Valer on 15/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

class MyListWorker {
    
    enum FavoriteMoviesResponse {
        case success([Movie])
    }
    
    func fetchFavoriteMovies(completion: @escaping (FavoriteMoviesResponse) -> Void) {
        
        guard let response = UserPreferences.shared.getFavoriteMovies(),
            !response.isEmpty else {
                completion(.success([]))
                return
        }
        
        var favMovies: [Movie] = []
        response.forEach { (key, value) in
            let movie = Movie(id: Int(key), title: value[0],
                              originalTitle: nil, overview: nil, genres: nil,
                              genreIds: nil, releaseDate: nil, posterPath: value[1],
                              backdropPath: nil, voteAverage: nil,
                              productionCountries: nil)
            
            favMovies.append(movie)
        }
        
        completion(.success(favMovies))
    }
}
