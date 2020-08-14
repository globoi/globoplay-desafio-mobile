//
//  GenresList.swift
//  My Movies
//
//  Created by Rafael Valer on 11/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

enum GenresList: CaseIterable {
    
    case action, comedy, crime, romance, scifi
    
    func getId() -> Int {
        switch self {
        case .action: return 28
        case .comedy: return 35
        case .crime: return 80
        case .scifi: return 878
        case .romance: return 10749
        }
    }
    
    func getTitle() -> String {
        switch self {
        case .action: return "Ação"
        case .comedy: return "Comédia"
        case .crime: return "Crime"
        case .romance: return "Romance"
        case .scifi: return "Ficção Científica"
        }
    }
}
