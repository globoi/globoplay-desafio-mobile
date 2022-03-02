//
//  MovieList.swift
//  MoviesApp
//
//  Created by Gustavo Tiecker on 22/02/22.
//

import Foundation

struct MovieList: Codable {
    let id: String
    let items: [Movie]
    
    init(id: String, items: [Movie]) {
        self.id = id
        self.items = items
    }
}
