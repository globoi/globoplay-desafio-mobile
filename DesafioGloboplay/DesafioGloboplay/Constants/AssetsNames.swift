//
//  AssetsNames.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import Foundation

enum assets{
    
    enum images: String{
        case logo = "globoplay_logo_wikipedia"
    }
    
    enum icons{
        enum details: String{
            case myListInitial = "star"
            case myListItemAlreadyAdded = "checkmark"
        }
        
        enum tabIcons: String{
            case home = "house"
            case myList = "star"
        }
        
        enum myList: String{
            case emptyListIcon = "list.star"
        }
        
        enum error: String{
            case generic = "multiply.square"
            case wifi = "wifi"
        }
    }
    
}
