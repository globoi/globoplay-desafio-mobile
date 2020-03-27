//
//  UIViewController.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 26/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import Foundation
import UIKit

extension UIViewController {
    class func instantiate() -> UIViewController {
        let name = "\(self)"
        let storyboard = UIStoryboard(name: name, bundle: nil)
        let viewController = storyboard.instantiateViewController(withIdentifier: name)
        
        return viewController
    }
}
