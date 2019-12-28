//
//  FirstHalfTableVIewCell.swift
//  Movs
//
//  Created by gmmoraes on 27/12/19.
//  Copyright © 2019 gmmoraes. All rights reserved.
//

import Foundation
import UIKit

class FirstHalfTableVIewCell: UITableViewCell {
    
    @IBOutlet weak var titleImageView: UIImageView!
    
    @IBOutlet weak var titleLabel: UILabel!
    
    @IBOutlet weak var tipoLabel: UILabel!
    
    @IBOutlet weak var backImageView: UIImageView!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.backgroundColor = .clear
        
        let blurEffect = UIBlurEffect(style: UIBlurEffect.Style.dark)
        let blurEffectView = UIVisualEffectView(effect: blurEffect)
        blurEffectView.frame = self.bounds
        blurEffectView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        backImageView.addSubview(blurEffectView)
        backImageView.contentMode = .scaleToFill
        
        
    }
}
