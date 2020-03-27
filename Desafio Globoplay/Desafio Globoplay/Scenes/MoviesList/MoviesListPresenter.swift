//
//  MoviesListPresenter.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 25/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import Foundation

protocol MoviesListPresenterProtocol: class {
    func configure(_ cell: MovieCellProtocol, at indexPath: IndexPathDict)
    func showDetails(of indexPath: IndexPathDict)
    func update(withList movies: [Movie], withSectionIndex section: String)
    func getNumberOfItems(for sectionIndex: String) -> Int
    func getNumberOfSections() -> Int
    func getData()
}

//MOCK
var bookmarks: [Movie] = []

class MoviesListPresenter: MoviesListPresenterProtocol {
    private var moviesList: [String : [Movie]] = [:]
    
    var interactor: MoviesListInteractorProtocol!
    var view: MoviesListViewProtocol!
    var router: MoviesListRouter!
    
    func configure(_ cell: MovieCellProtocol, at indexPath: IndexPathDict) {
        cell.display(withURL: moviesList[indexPath.section]?[indexPath.row].smallPictureUrl ?? "")
    }
    
    func getNumberOfItems(for sectionIndex: String) -> Int {
        return (moviesList[sectionIndex] ?? []).count
    }
    
    func getNumberOfSections() -> Int {
        return moviesList.count
    }
    
    func getData() {
        self.interactor.getMostPopularTVShows()
        self.interactor.getMostPopularMovies()
        self.interactor.getTopRatedMovies()
    }
    
    func showDetails(of indexPath: IndexPathDict) {
        self.router.showDetails(of: moviesList[indexPath.section]?[indexPath.row] ?? Movie())
//        bookmarks.append(moviesList[indexPath.section]?[indexPath.row] ?? Movie())
    }
    
    func update(withList movies: [Movie], withSectionIndex section: String) {
        if self.moviesList[section] == nil {
            self.moviesList[section] = movies
            
        } else {
            self.moviesList[section]! += movies
            
        }
            
        self.view.display()
    }
}
