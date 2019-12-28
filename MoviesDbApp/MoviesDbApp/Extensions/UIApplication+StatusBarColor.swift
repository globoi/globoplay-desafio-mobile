//
//  UIApplication+StatusBarColor.swift
//  Movs
//
//  Created by gmmoraes on 21/12/19.
//  Copyright © 2019 gmmoraes. All rights reserved.
//

import Foundation
import UIKit

extension UIApplication {

    var statusBarView: UIView? {
        return value(forKey: "statusBar") as? UIView
    }

}
