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
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }

    // MARK: - API
    
    // MARK: - Helper Methods
    
    func configureUI() {
        view.backgroundColor = .homeBlack
    }
    
    func configureViewControllers() {
        let feed = FeedController(collectionViewLayout: UICollectionViewFlowLayout())
        let nav1 = templateNatigationController(image: UIImage(systemName: "house.fill"), rootViewControoler: feed)
        
        let myList = MyListController()
        let nav2 = templateNatigationController(image: UIImage(systemName: "star.fill"), rootViewControoler: myList)
        
        viewControllers = [nav1, nav2]
        /// Costumization of the `TabBar`.
        tabBar.backgroundColor = .black
        tabBar.tintColor = .customWhite
    }
    
    func templateNatigationController(image: UIImage?, rootViewControoler: UIViewController) -> UINavigationController {
        let nav = UINavigationController(rootViewController: rootViewControoler)
        nav.tabBarItem.image = image
        /// Costumization of the `NavigationBar` color after the iOS 15 atualizations.
        /// Now the `standardAppearance` must be equal to `scrollEdgeAppearance` to not be transparent.
        let appearance = UINavigationBarAppearance()
        appearance.configureWithOpaqueBackground()
        appearance.backgroundColor = .black
        appearance.titleTextAttributes = [.foregroundColor: UIColor.lightText] // With a balck background, make the title more readable.
        
        nav.navigationBar.standardAppearance = appearance
        nav.navigationBar.scrollEdgeAppearance = nav.navigationBar.standardAppearance
        nav.navigationBar.barStyle = .black
        nav.navigationBar.isTranslucent = false
        nav.navigationBar.titleTextAttributes = [.foregroundColor: UIColor.white]
        return nav
    }
    
    // MARK: - Selectors

}

extension UINavigationController {
   open override var preferredStatusBarStyle: UIStatusBarStyle {
       return .lightContent
   }
}

