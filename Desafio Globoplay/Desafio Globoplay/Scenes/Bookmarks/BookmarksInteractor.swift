//
//  BookmarksInteractor.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 27/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import Foundation

protocol BookmarksInteractorProtocol: class {
    func getBookmarkedMovies()
}

class BookmarksInteractor: BookmarksInteractorProtocol {
    var presenter: BookmarksPresenterProtocol!
    
    func getBookmarkedMovies() {
        self.presenter.update(withList: bookmarks)
    }
}
