//
//  DetailsPresenter.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 27/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import Foundation

protocol DetailsPresenterProtocol: class {
    func getMovie()
    func bookmark(movie: Movie)
    func isMovieBookmarked() -> Bool
}

class DetailsPresenter: DetailsPresenterProtocol {
    var view: DetailsViewProtocol!
    var interactor: DetailsInteractorProtocol!
    
    private var movie: Movie!
    
    init(withMovie movie: Movie) {
        self.movie = movie
    }
    
    func getMovie() {
        self.view.display(movie: movie)
    }
    
    func bookmark(movie: Movie) {
        if !bookmarks.contains(where: { (moviePred) -> Bool in
            return movie.id == moviePred.id
        }) {
            
            bookmarks.append(movie)
            self.view.bookmarked()
        }
    }
    
    func isMovieBookmarked() -> Bool {
        return bookmarks.contains(where: { (moviePred) -> Bool in
            return self.movie.id == moviePred.id
        })
    }
}
