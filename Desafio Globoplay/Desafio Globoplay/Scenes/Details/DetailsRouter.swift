//
//  DetailsRouter.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 27/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import Foundation
import UIKit

class DetailsRouter: Router {
    var children: [Router]
    var movie: Movie!
    
    private var navigationController: UINavigationController
    
    init(navigationController: UINavigationController, withMovie movie: Movie) {
        self.navigationController = navigationController
        self.children = []
        self.movie = movie
    }
    
    func start() {
        let presenter = DetailsPresenter(withMovie: self.movie)
        let interactor = DetailsInteractor()
        let view = DetailsViewController.instantiate() as! DetailsViewController
        
        view.presenter = presenter
        interactor.presenter = presenter
        
        presenter.interactor = interactor
        presenter.view = view
        
        navigationController.pushViewController(view, animated: true)
    }
}
