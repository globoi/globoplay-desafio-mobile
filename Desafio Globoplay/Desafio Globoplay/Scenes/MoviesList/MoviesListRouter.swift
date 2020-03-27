//
//  MoviesListRouter.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 26/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import Foundation
import UIKit

class MoviesListRouter: Router {
    var children: [Router]
    
    private var navigationController: UINavigationController
    
    init(navigationController: UINavigationController) {
        self.navigationController = navigationController
        self.children = []
    }
    
    func start() {
        let presenter = MoviesListPresenter()
        let interactor = MoviesListInteractor()
        let view = MoviesListViewController.instantiate() as! MoviesListViewController
        
        view.presenter = presenter
        interactor.presenter = presenter
        
        presenter.interactor = interactor
        presenter.view = view
        presenter.router = self
        
        self.navigationController.setViewControllers([view], animated: false)
    }
    
    func showDetails(of movie: Movie) {
        let router = DetailsRouter(navigationController: self.navigationController, withMovie: movie)
        self.children = [router]
        router.start()
        
    }
}
