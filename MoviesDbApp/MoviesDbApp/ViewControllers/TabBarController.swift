//
//  TabBarController.swift
//  Movs
//
//  Created by gmmoraes on 26/12/19.
//  Copyright © 2019 gmmoraes. All rights reserved.
//

import Foundation
import UIKit

class TabBarController: UITabBarController {
    
    let navbarFont = UIFont.loadFont(name: "NavBarFont")
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.navigationController?.navigationBar.barTintColor =  .navbarBackgroundColor
        self.tabBar.barTintColor =  .navbarBackgroundColor
        self.tabBar.unselectedItemTintColor =  .unselectedTabBarIconColor
        self.navigationController?.navigationBar.isTranslucent = false

        self.navigationController?.navigationBar.titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.white,NSAttributedString.Key.font: navbarFont]
        self.tabBar.tintColor =  .selectedTabBarIconColor
        self.navigationController?.navigationBar.tintColor = .white

    }
}
