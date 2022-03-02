//
//  Worker.swift
//  SmartMic
//
//  Created by Marcel Mendes Filho on 12/08/19.
//  Copyright © 2019 Marcel Mendes Filho. All rights reserved.
//

import Foundation
import UIKit

class Worker{
    
    static let headerViewHeight = CGFloat(30)
    
    static func getHeaderViewLabel(width: CGFloat) -> UILabel{
        let height = headerViewHeight
        
        let headerLabel = UILabel(frame: CGRect(x: 0, y: 0, width: width, height: height))
        headerLabel.layer.cornerRadius = 4
        headerLabel.layer.masksToBounds = true
        headerLabel.font = UIFont.systemFont(ofSize: 20, weight: .semibold)
        headerLabel.backgroundColor = .black
        headerLabel.textColor = .white
        headerLabel.adjustsFontSizeToFitWidth = true
        return headerLabel
        
    }
    
}
