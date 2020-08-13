//
//  UIButton+Extensions.swift
//  My Movies
//
//  Created by Rafael Valer on 13/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation
import UIKit

@IBDesignable extension UIButton {
    
    @IBInspectable var cornerRadius: CGFloat {
        set {
            layer.cornerRadius = newValue
        }
        get {
            return layer.cornerRadius
        }
    }
    
    @IBInspectable var borderWidth: CGFloat {
        set {
            layer.borderWidth = newValue
        }
        get {
            return layer.borderWidth
        }
    }

    @IBInspectable var borderColor: UIColor? {
        set {
            guard let color = newValue else { return }
            layer.borderColor = color.cgColor
        }
        get {
            guard let color = layer.borderColor else { return nil }
            return UIColor(cgColor: color)
        }
    }
}
