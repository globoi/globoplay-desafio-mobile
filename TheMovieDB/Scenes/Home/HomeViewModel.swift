//
//  HomeViewModel.swift
//  TheMovieDB
//
//  Created by Edwy Lugo on 04/02/20.
//  Copyright © 2020 Edwy Lugo. All rights reserved.
//

import Foundation


protocol HomeViewNavigationProtocol: AnyObject {
    func gotoNotesTabBar(movie: Movies)
}

protocol HomeViewModelProtocol {
    var movie: Movies { get }
    func didSelectItemAt(indexPath: IndexPath)
}

struct HomeViewModel {
    private weak var navigationDelegate: HomeViewNavigationProtocol?
    var movie: Movies

    init(navigationDelegate: HomeViewNavigationProtocol,
         movie: Movies) {
        self.navigationDelegate = navigationDelegate
        self.movie = movie
    }
}

extension HomeViewModel: HomeViewModelProtocol {
    func didSelectItemAt(indexPath: IndexPath) {
        navigationDelegate?.gotoNotesTabBar(movie: movie)
    }
}
