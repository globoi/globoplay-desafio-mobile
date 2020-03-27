//
//  BookmarksRouter.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 27/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import Foundation
import UIKit

class BookmarksRouter: Router {
    var children: [Router]
    
    private var navigationController: UINavigationController
    
    init(navigationController: UINavigationController) {
        self.navigationController = navigationController
        self.children = []
    }
    
    func start() {
        let presenter = BookmarksPresenter()
        let interactor = BookmarksInteractor()
        let view = BookmarksViewController.instantiate() as! BookmarksViewController
        
        view.presenter = presenter
        interactor.presenter = presenter
        
        presenter.interactor = interactor
        presenter.view = view
        
        self.navigationController.setViewControllers([view], animated: false)
    }
}
