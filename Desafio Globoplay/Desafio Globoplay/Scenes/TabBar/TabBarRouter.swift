//
//  TabBarROuter.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 26/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import Foundation
import UIKit

class TabBarRouter: Router {
    var children: [Router]

    private let window: UIWindow
    private let tabBarController: UITabBarController
    
    private enum Tab {
        case Movies, Bookmarks
    }
    
    init(window: UIWindow) {
        self.window = window
        self.tabBarController = UITabBarController()
        self.children = []
        
        self.tabBarController.tabBar.barTintColor = .black
        self.tabBarController.tabBar.tintColor = Colors.white
    }
    
    func start() {
        self.add(tab: .Movies)
        self.add(tab: .Bookmarks)
        self.window.updateRootViewController(with: self.tabBarController)
        self.window.makeKeyAndVisible()
    }
    
    private func add(tab: Tab) {
        let navigationController = UINavigationController()
        navigationController.navigationBar.tintColor = Colors.white
        navigationController.navigationBar.barTintColor = Colors.black
        navigationController.navigationBar.isTranslucent = false
        
        var viewControllers: [UIViewController] = self.tabBarController.viewControllers ?? []
        viewControllers += [navigationController]
        self.tabBarController.setViewControllers(viewControllers, animated: true)
        
        var router: Router
        
        switch tab {
            case .Movies:
                router = MoviesListRouter(navigationController: navigationController)
                navigationController.tabBarItem = UITabBarItem(title: "Início",
                                                               image: UIImage(named: "home"),
                                                               selectedImage: nil)
            
            case .Bookmarks:
                router = BookmarksRouter(navigationController: navigationController)
                navigationController.tabBarItem = UITabBarItem(title: "Favoritos",
                                                               image: UIImage(named: "star"),
                                                               selectedImage: nil)
        }
        
        self.children.append(router)
        router.start()
    }
}
