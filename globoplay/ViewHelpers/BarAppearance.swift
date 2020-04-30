//
//  BarAppearance.swift
//  globoplay
//
//  Created by Marcos Curvello on 19/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI

struct BarAppearance {
    static var navigationBarDefault: UINavigationBarAppearance {
        let appearance = UINavigationBarAppearance()
        appearance.titleTextAttributes = [.foregroundColor: UIColor.black]
        appearance.largeTitleTextAttributes = [.foregroundColor: UIColor.black]
        
        let button = UIBarButtonItemAppearance()
        button.normal.titleTextAttributes = [.foregroundColor: UIColor.white]
        appearance.buttonAppearance = button
        return appearance
    }
    
    static var tabbarDefault: UITabBarAppearance {
        let appearance = UITabBarAppearance()
        appearance.backgroundColor = UIColor.black
        return appearance
    }
}
