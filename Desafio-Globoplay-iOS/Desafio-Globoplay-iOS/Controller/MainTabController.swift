//
//  ViewController.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 22/02/22.
//

import UIKit

class MainTabController: UITabBarController {

    // MARK: - Properties
    
    // MARK: - Lifecycle
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureViewControllers()
    }

    // MARK: - API
    
    // MARK: - Helper Methods
    
    func configureUI() {
        view.backgroundColor = .homeBlack
    }
    
    func configureViewControllers() {
        let feed = FeedController()
        let nav1 = templateNatigationController(image: UIImage(systemName: "house.fill"), rootViewControoler: feed)
        
        let myList = MyListController()
        let nav2 = templateNatigationController(image: UIImage(systemName: "star.fill"), rootViewControoler: myList)
        
        viewControllers = [nav1, nav2]
        /// Costumization of the `TabBar` color before the iOS 15 atualizations.
        tabBar.backgroundColor = .homeBlack
        tabBar.tintColor = .customWhite
    }
    
    func templateNatigationController(image: UIImage?, rootViewControoler: UIViewController) -> UINavigationController {
        let nav = UINavigationController(rootViewController: rootViewControoler)
        nav.tabBarItem.image = image
        /// Costumization of the `NavigationBar` color before the iOS 15 atualizations.
        /// Now the `standardAppearance` must be equal to `scrollEdgeAppearance` to not be transparent.
        let appearance = UINavigationBarAppearance()
        appearance.configureWithOpaqueBackground()
        appearance.backgroundColor = .white
        nav.navigationBar.standardAppearance = appearance;
        nav.navigationBar.scrollEdgeAppearance = nav.navigationBar.standardAppearance
        
        return nav
    }
    
    // MARK: - Selectors

}

