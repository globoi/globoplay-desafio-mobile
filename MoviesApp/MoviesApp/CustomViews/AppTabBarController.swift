//
//  AppTabBarController.swift
//  MoviesApp
//
//  Created by Gustavo Tiecker on 22/02/22.
//

import UIKit

class AppTabBarController: UITabBarController {

    override func viewDidLoad() {
        super.viewDidLoad()
        UITabBar.appearance().tintColor = .white
        viewControllers = [createHomeViewController(), createFavoritesViewController()]
        
        let attributes = [NSAttributedString.Key.font:  UIFont(name: "Helvetica-Bold", size: 0.1)!, NSAttributedString.Key.foregroundColor: UIColor.clear]

        UIBarButtonItem.appearance().setTitleTextAttributes(attributes, for: .normal)
        UIBarButtonItem.appearance().setTitleTextAttributes(attributes, for: .highlighted)
        UIBarButtonItem.appearance().tintColor = .white
    }
    
    func createHomeViewController() -> UINavigationController {
        let viewModel = HomeViewModel()
        let viewController = HomeViewController(viewModel: viewModel)
        viewModel.viewDelegate = viewController
        viewController.title = "globoplay"
        viewController.tabBarItem = UITabBarItem(title: "Início", image: UIImage(systemName: "house.fill"), tag: 0)
        
        return UINavigationController(rootViewController: viewController)
    }
    
    func createFavoritesViewController() -> UINavigationController {
        let viewModel = FavoritesViewModel()
        let favoritesViewController = FavoritesViewController(viewModel: viewModel)
        favoritesViewController.title = "Minha lista"
        favoritesViewController.tabBarItem = UITabBarItem(title: "Minha lista", image: UIImage(systemName: "star.fill"), tag: 0)
        
        return UINavigationController(rootViewController: favoritesViewController)
    }
}
