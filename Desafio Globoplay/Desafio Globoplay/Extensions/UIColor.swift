//
//  UIColor.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 22/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import Foundation
import UIKit

extension UIColor {
    public convenience init(hex: String, alpha: CGFloat) {
        var hexInt: Int32 = 0
        let scanner: Scanner = Scanner(string: hex)
        
        scanner.currentIndex = scanner.string.index(after: scanner.currentIndex)
        hexInt = scanner.scanInt32() ?? 0
        
        self.init(
            red: CGFloat((hexInt & 0xFF0000) >> 16) / 255.0,
            green: CGFloat((hexInt & 0x00FF00) >> 8) / 255.0,
            blue: CGFloat(hexInt & 0x0000FF) / 255.0,
            alpha: alpha)
    }
}
