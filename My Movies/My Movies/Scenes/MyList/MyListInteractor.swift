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
    var worker: MyListWorker = MyListWorker()
    
    // MARK: - MyListDataStore
    var favoriteMovies: [Movie] = []
    
    // MARK: - MyListBusinessLogic
    func fetchFavoriteMovies() {
        
        worker.fetchFavoriteMovies { [weak self] (response) in
            switch response {
            case .success(let movies):
                self?.favoriteMovies = movies
                self?.presenter?.presentFavoriteMovies(response: MyListModels.FetchFavoriteMovies.Response(favoriteMovies: movies))
                break
            }
        }
    }
}
