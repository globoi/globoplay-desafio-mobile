//
//  HomeViewModel.swift
//  globoplay-desafio
//
//  Created by Wagner Junior  on 01/05/21.
//

import Foundation

protocol HomeViewModelDelegate: class {
    func charactersDidChange()
    func fetchDidFailed()
}

class HomeViewModel {
    
    let movieService = MovieService()
    weak var viewDelegate: HomeViewModelDelegate?
    var moviesPopular: [MovieResponse] = [MovieResponse]()
    
    func loadPopularMovies() {
        movieService.fetch { [weak self] result in
            switch result {
            case .success(let movies):
                self?.moviesPopular.append(contentsOf: movies)
            case .failure(let error):
                self?.viewDelegate?.fetchDidFailed()
            }
        }
    }
    
}
