//
//  MyListInteractor.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

protocol MyListBusinessLogic {
    func fetchFavoriteMovies()
}

protocol MyListDataStore {
    var favoriteMovies: [Movie] { get set }
}

class MyListInteractor: MyListBusinessLogic, MyListDataStore {
    
    var presenter: MyListPresentationLogic?
    
    // MARK: - MyListDataStore
    var favoriteMovies: [Movie] = []
    
    // MARK: - MyListBusinessLogic
    func fetchFavoriteMovies() {
        guard let response = UserPreferences.shared.getFavoriteMovies() else {
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
        
        self.favoriteMovies = favMovies
        self.presenter?.presentFavoriteMovies(response: MyListModels.FetchFavoriteMovies.Response(favoriteMovies: favMovies))
    }
}
