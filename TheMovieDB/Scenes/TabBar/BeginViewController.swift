//
//  BeginViewController.swift
//  TheMovieDB
//
//  Created by Edwy Lugo on 03/02/20.
//  Copyright © 2020 Edwy Lugo. All rights reserved.
//

import UIKit

class BeginViewController: UITabBarController, UITabBarControllerDelegate {
        
    override func viewDidAppear(_ animated: Bool) {
           super.viewDidAppear(animated)
       }

       override func viewDidLoad() {
           super.viewDidLoad()

           // Assign self for delegate for that ViewController can respond to UITabBarControllerDelegate methods
           self.delegate = self
        
           let imageView = UIImageView(frame: CGRect(x: 0, y: 0, width: 28, height: 28))
           imageView.contentMode = .scaleAspectFit
           let image = UIImage(named: "globoplay-logo")
           imageView.image = image
           self.navigationItem.titleView = imageView
       }
    
    override func viewWillAppear(_ animated: Bool) {
           super.viewWillAppear(animated)

           // Custom Tab bar
           self.tabBar.barTintColor = UIColor(named: "black")
           UITabBar.appearance().tintColor = UIColor(named: "white")
           UITabBar.appearance().barTintColor = UIColor(named: "black")
           UITabBar.appearance().unselectedItemTintColor = UIColor(named: "unselect")
           UITabBar.appearance().isTranslucent = false

        
        // Create Tab one to HeaderViewController
        let tabOne = HomeViewController()
           let tabOneBarItem = UITabBarItem(title: "Início", image: UIImage(named: "baseline_home_black_24"), selectedImage: UIImage(named: "baseline_home_black_24"))
           tabOne.tabBarItem = tabOneBarItem

           let tabTwo = MyListFavoriteViewController()
           let tabTwoBarItem2 = UITabBarItem(title: "Minha Lista", image: UIImage(named: "baseline_star_rate_black_24"), selectedImage: UIImage(named: "baseline_star_rate_black_24"))
           tabTwo.tabBarItem = tabTwoBarItem2

           self.viewControllers = [tabOne, tabTwo]
       }
    
    // UITabBarControllerDelegate method
       func tabBarController(_: UITabBarController, didSelect viewController: UIViewController) {
           if viewController.isKind(of: HomeViewController.self) {
               
            let imageView = UIImageView(frame: CGRect(x: 0, y: 0, width: 28, height: 28))
            imageView.contentMode = .scaleAspectFit
            let image = UIImage(named: "globoplay-logo")
            imageView.image = image
            self.navigationItem.titleView = imageView
            
            
           } else if viewController.isKind(of: MyListFavoriteViewController.self) {
               let imageView = UIImageView(frame: CGRect(x: 0, y: 0, width: 28, height: 28))
               imageView.contentMode = .scaleAspectFit
               let image = UIImage(named: "globoplay-logo")
               imageView.image = image
               self.navigationItem.titleView = imageView
            
               
           } else {
               let imageView = UIImageView(frame: CGRect(x: 0, y: 0, width: 28, height: 28))
               imageView.contentMode = .scaleAspectFit
               let image = UIImage(named: "globoplay-logo")
               imageView.image = image
               self.navigationItem.titleView = imageView
               
           }
       }
}


