//
//  UIFont+CustomFonts.swift
//  Movs
//
//  Created by gmmoraes on 25/12/19.
//  Copyright © 2019 gmmoraes. All rights reserved.
//

import Foundation
import UIKit

extension UIFont {
    
    
    static func getCustomFontDict(fontsDictName: String) -> [String: AnyObject]{
        
        if let path = Bundle.main.path(forResource: "Config", ofType: "plist"), let dict = NSDictionary(contentsOfFile: path) as? [String: AnyObject] {
                   
            if let fonts = dict["Fonts"] as? [String: AnyObject], let fontsDict = fonts[fontsDictName] as? [String: AnyObject]{
                       return fontsDict
                   }
               }
        return [:]
    }
    
    static func getCustomFontFamilyName(fontsDictName: String) -> String {
        let fontsDict = getCustomFontDict(fontsDictName: fontsDictName)
        guard let fontName = fontsDict["fontName"] as? String else {return ""}
        
        return fontName
        
    }
    
    
    static func getCustomFontSize(fontsDictName: String) -> CGFloat {
        let fontsDict = getCustomFontDict(fontsDictName: fontsDictName)
        guard let fontSize = fontsDict["fontSize"] as? CGFloat else {return 0.0}
        
        return fontSize
        
    }
    
    static func loadFont(name: String) -> UIFont {
        
        let fontName = getCustomFontFamilyName(fontsDictName:name)
        let fontSize = getCustomFontSize(fontsDictName:name)
        guard let font = UIFont(name: fontName, size: fontSize) else {return UIFont.systemFont(ofSize: fontSize)}
        return font
        
        
    }
    
}
