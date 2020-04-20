//
//  UINavigationController+BarAppearance.swift
//  globoplay
//
//  Created by Marcos Curvello on 19/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI

extension UINavigationController {
    override open func viewDidLoad() {
        super.viewDidLoad()
        navigationBar.standardAppearance = BarAppearance.navigationBarDefault
    }
}
