//
//  BookmarksPresenter.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 27/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import Foundation

protocol BookmarksPresenterProtocol: class {
    func configure(_ cell: MovieCellProtocol, at indexRow: Int)
    func getNumberOfItems() -> Int
    func getData()
    func update(withList movies: [Movie])
}

class BookmarksPresenter: BookmarksPresenterProtocol {
    private var bookmarksList: [Movie] = []
    
    var interactor: BookmarksInteractorProtocol!
    var view: BookmarksViewProtocol!
    
    func configure(_ cell: MovieCellProtocol, at indexRow: Int) {
        cell.display(withURL: bookmarksList[indexRow].smallPictureUrl)
    }
    
    func getNumberOfItems() -> Int {
        return bookmarksList.count
    }
    
    func getData() {
        self.interactor.getBookmarkedMovies()
    }
    
    func update(withList movies: [Movie]) {
        self.bookmarksList = movies
            
        self.view.display()
    }
}
