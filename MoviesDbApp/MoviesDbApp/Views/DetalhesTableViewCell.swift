//
//  DetalhesTableViewCell.swift
//  Movs
//
//  Created by gmmoraes on 25/12/19.
//  Copyright © 2019 gmmoraes. All rights reserved.
//

import Foundation
import UIKit

class DetalhesTableViewCell:UITableViewCell {
    
    
    @IBOutlet weak var overviewLabel: UILabel!
    
    @IBOutlet weak var assistaBtn: DetalhesButton!
    @IBOutlet weak var minhaListaBtn: DetalhesButton!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.backgroundColor = .navbarBackgroundColor
        
        self.layoutIfNeeded()
        

        assistaBtn.tintColor = .unselectedTabBarIconColor
        assistaBtn.clipsToBounds = true
        minhaListaBtn.tintColor = .unselectedTabBarIconColor
        minhaListaBtn.clipsToBounds = true
        
    }
    
    @IBAction func buttonPressed(_ sender: DetalhesButton) {
        sender.isSelected = !sender.isSelected
        
        if (sender.isSelected)
        {
            sender.tintColor = .selectedTabBarIconColor
            sender.backgroundColor = .white
            sender.icon =  sender.icon?.maskWithColor(color: .navbarBackgroundColor)
        }
        else
        {
            sender.tintColor = .unselectedTabBarIconColor
            sender.backgroundColor = .navbarBackgroundColor
            sender.icon =  sender.icon?.maskWithColor(color: .unselectedTabBarIconColor)
        }
        
        if sender.tag == 1, let favoritedMedia = sender.favoritedMedia {
            let selectedIcon =  UIImage(named: "baseline_check_black_24")
            sender.setImage(selectedIcon, for: .selected)
            let currentId = favoritedMedia.movies != nil ? favoritedMedia.movies?.id : favoritedMedia.series?.id
            let currentFileName = String(currentId!) + ".json"
            let currentFavoritedMedia = FavoritedMedia(movies: favoritedMedia.movies, series: favoritedMedia.series, isFavorited: !favoritedMedia.isFavorited)
            if Storage.fileExists(currentFileName, in: .documents) {
                Storage.remove(currentFileName, from: .documents)
                
                Storage.store(currentFavoritedMedia, to: .documents, as: currentFileName)
            } else {
               Storage.store(currentFavoritedMedia, to: .documents, as: currentFileName)
            }
        }
    }
    
}
