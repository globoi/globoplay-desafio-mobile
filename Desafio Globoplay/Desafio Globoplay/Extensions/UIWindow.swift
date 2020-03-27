//
//  UIWindow.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 26/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import Foundation
import UIKit

extension UIWindow {
    func updateRootViewController(with viewController: UIViewController) {
        UIView.transition(with: self, duration: 0.2, options: .transitionCrossDissolve, animations: {
            self.rootViewController = viewController
        }, completion: nil)
    }
}
