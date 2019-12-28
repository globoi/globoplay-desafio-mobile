//
//  DetalhesButton.swift
//  Movs
//
//  Created by gmmoraes on 25/12/19.
//  Copyright © 2019 gmmoraes. All rights reserved.
//

import Foundation
import UIKit

class DetalhesButton : UIButton {
    
    var icon: UIImage?
    var title: String?
    var selectedTitle: String?
    var favoritedMedia : FavoritedMedia? {
        didSet{
            if let favoritedMedia = favoritedMedia {
                if favoritedMedia.isFavorited == true {
                    self.isSelected = true
                    configureUI()
                    self.imageView?.backgroundColor = .clear
                    self.icon =  self.icon?.maskWithColor(color: .navbarBackgroundColor)
                    self.tintColor = .white
                    self.backgroundColor = .white
                    
                    self.layoutIfNeeded()
                }
                
            }
        }
    }
    
    override init(frame: CGRect) {


        super.init(frame: frame)
                
        if let _ = icon, let _ = title, let _ = selectedTitle {
            configureUI() 
        }
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    func configureUI() {
        self.setTitle(title,for: .normal)
        self.setTitle(selectedTitle,for: .selected)
        self.setTitleColor(.unselectedTabBarIconColor, for: .normal)
        self.setTitleColor(.navbarBackgroundColor, for: .selected)
        self.backgroundColor = self.isSelected ? .white : .navbarBackgroundColor
        self.layer.cornerRadius = 5
        self.layer.borderWidth = 2
        self.layer.borderColor = UIColor.unselectedTabBarIconColor.cgColor
        icon =  icon?.maskWithColor(color: .unselectedTabBarIconColor)
        self.setImage(icon, for: .normal)
        let selectedIcon =  UIImage(named: "baseline_check_black_24")
        self.setImage(selectedIcon, for: .selected)
        
        self.imageView?.contentMode = .scaleAspectFit
        self.imageEdgeInsets = UIEdgeInsets(top: 0, left: -20, bottom: 0, right: 0)
         self.isUserInteractionEnabled = true
        
    }
}
