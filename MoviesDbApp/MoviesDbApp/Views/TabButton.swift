//
//  TabButton.swift
//  Movs
//
//  Created by gmmoraes on 28/12/19.
//  Copyright © 2019 gmmoraes. All rights reserved.
//

import Foundation
import UIKit

class TabButton: UIButton {
    
    var lineView = UIView()

    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.backgroundColor = .clear
        self.addSubview(lineView)
    }
}
