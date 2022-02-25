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
        let feed = HomeViewController()
        let nav1 = templateNatigationController(image: UIImage(systemName: "house.fill"), rootViewControoler: feed)
        
        let myList = MyListController()
        let nav2 = templateNatigationController(image: UIImage(systemName: "star.fill"), rootViewControoler: myList)
        
        viewControllers = [nav1, nav2]
        
        /// Costumization of the `TabBar`.
        nav1.title = "Início"
        nav2.title = "Minha lista"
        
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
        appearance.titleTextAttributes = [.foregroundColor: UIColor.lightText] // With a black background, make the title more readable.
        
        nav.navigationBar.standardAppearance = appearance
        nav.navigationBar.scrollEdgeAppearance = nav.navigationBar.standardAppearance
        nav.navigationBar.tintColor = .customWhite
        nav.navigationBar.barStyle = .black
        nav.navigationBar.isTranslucent = false
        nav.navigationBar.titleTextAttributes = [.foregroundColor: UIColor.white]
        nav.hidesBarsOnSwipe = true
        
        configureNavigationBar(nav: nav)
        
        
        return nav
    }
    
    // MARK: - Selectors
    
    func configureNavigationBar(nav: UINavigationController) {
        let imageView = UIImageView(image: UIImage(named: "globoplay-text-logo"))
        imageView.contentMode = .scaleAspectFit
        imageView.anchor(height: 35)
        nav.navigationBar.topItem?.titleView = imageView
        
        let profileImage = UIImage(systemName: "person")
        profileImage?.withTintColor(.customWhite)
        profileImage?.withRenderingMode(.alwaysOriginal)
                
        navigationItem.rightBarButtonItem = UIBarButtonItem(image: profileImage, style: .done, target: self, action: #selector(handlePerfilTapped))
    }
    
    @objc func handlePerfilTapped() {
        AlertUtils.showAlert(message: "Por favor, crie uma conta para acessar a página de perfil.")
    }

}

extension UINavigationController {
   open override var preferredStatusBarStyle: UIStatusBarStyle {
       return .lightContent
   }
}

