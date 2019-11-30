//
//  StaticValues.swift
//  Desafio GloboPlay
//
//  Created by Filipo Negrao on 03/11/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import Foundation
import UIKit

let window = UIScreen.main.bounds
let screenWidth = window.width
let screenHeight = window.height

// MARK: URLs & Mensagens

let API_URL = "https://api.themoviedb.org/3"
let IMAGE_URL = "http://image.tmdb.org/t/p/w"
let API_SUGGESTIONS = "https://www.hurb.com/search/api/suggestion?q="
let ERROR_SERVER_MESSAGE = "Erro interno no servidor! Tente novamente mais tarde"
let ERROR_NO_CONNECTION = "Sem conexão com a internet!"

// MARK: Colors

let blue = UIColor.init(rgb: 0x1D4E89)
let red = UIColor.init(rgb: 0xDF3B57)
let gray = UIColor.init(rgb: 0x1c1c1c)

// MARK: Extensions

extension UIColor {
   convenience init(red: Int, green: Int, blue: Int) {
       assert(red >= 0 && red <= 255, "Invalid red component")
       assert(green >= 0 && green <= 255, "Invalid green component")
       assert(blue >= 0 && blue <= 255, "Invalid blue component")

       self.init(red: CGFloat(red) / 255.0, green: CGFloat(green) / 255.0, blue: CGFloat(blue) / 255.0, alpha: 1.0)
   }

   convenience init(rgb: Int) {
       self.init(
           red: (rgb >> 16) & 0xFF,
           green: (rgb >> 8) & 0xFF,
           blue: rgb & 0xFF
       )
   }
}

//extension UILabel {
//    private struct AssociatedKeys {
//        static var padding = UIEdgeInsets()
//    }
//
//    public var padding: UIEdgeInsets? {
//        get {
//            return objc_getAssociatedObject(self, &AssociatedKeys.padding) as? UIEdgeInsets
//        }
//        set {
//            if let newValue = newValue {
//                objc_setAssociatedObject(self, &AssociatedKeys.padding, newValue as UIEdgeInsets?, objc_AssociationPolicy.OBJC_ASSOCIATION_RETAIN_NONATOMIC)
//            }
//        }
//    }
//
//    override open func draw(_ rect: CGRect) {
//        if let insets = padding {
//            self.drawText(in: rect.inset(by: insets))
//        } else {
//            self.drawText(in: rect)
//        }
//    }
//
//    override open var intrinsicContentSize: CGSize {
//        guard let text = self.text else { return super.intrinsicContentSize }
//
//        var contentSize = super.intrinsicContentSize
//        var textWidth: CGFloat = frame.size.width
//        var insetsHeight: CGFloat = 0.0
//        var insetsWidth: CGFloat = 0.0
//
//        if let insets = padding {
//            insetsWidth += insets.left + insets.right
//            insetsHeight += insets.top + insets.bottom
//            textWidth -= insetsWidth
//        }
//
//        let newSize = text.boundingRect(with: CGSize(width: textWidth, height: CGFloat.greatestFiniteMagnitude),
//                                        options: NSStringDrawingOptions.usesLineFragmentOrigin,
//                                        attributes: [NSAttributedString.Key.font: self.font], context: nil)
//
//        contentSize.height = ceil(newSize.size.height) + insetsHeight
//        contentSize.width = ceil(newSize.size.width) + insetsWidth
//
//        return contentSize
//    }
//}
