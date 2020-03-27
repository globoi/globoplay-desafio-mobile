//
//  UIView.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 27/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import UIKit

extension UIView {
    func gradient(colors: [CGColor]) {
        let gradientLayer = CAGradientLayer()
        gradientLayer.frame = bounds
        gradientLayer.colors = colors
        
        layer.addSublayer(gradientLayer)
    }
}
