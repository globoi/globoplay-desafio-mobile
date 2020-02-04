//
//  MovieDetailsViewModel.swift
//  TheMovieDB
//
//  Created by Edwy Lugo on 04/02/20.
//  Copyright © 2020 Edwy Lugo. All rights reserved.
//

import Foundation

protocol MovieDetailsNavigationProtocol: AnyObject {
}

protocol MovieDetailsViewModelProtocol {
    var movie: Movies { get }
}

struct MovieDetailsViewModel {
    private weak var navigationDelegate: MovieDetailsNavigationProtocol?
    var movie: Movies
    
    init(navigationDelegate: MovieDetailsNavigationProtocol? = nil, movie: Movies ) {
        self.navigationDelegate = navigationDelegate
        self.movie = movie
    }
}
