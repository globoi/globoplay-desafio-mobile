//
//  NavigationController.swift
//  TheMovieDB
//
//  Created by Edwy Lugo on 03/02/20.
//  Copyright © 2020 Edwy Lugo. All rights reserved.
//

import UIKit

final class NavigationController: UINavigationController {
    override func loadView() {
        super.loadView()
        configureNavigation()
    }

    private func configureNavigation() {
        navigationBar.tintColor = .white
        navigationBar.prefersLargeTitles = false
        navigationBar.barStyle = .black
        navigationBar.isTranslucent = false
    }
}

