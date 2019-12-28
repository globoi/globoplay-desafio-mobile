//
//  FavoritedMedia.swift
//  Movs
//
//  Created by gmmoraes on 25/12/19.
//  Copyright © 2019 gmmoraes. All rights reserved.
//

import Foundation

struct FavoritedMedia: Codable {
    var movies: Movies?
    var series: Series?
    var isFavorited: Bool
    
    enum CodingKeys: String, CodingKey {
        case movies
        case series
        case isFavorited
    }
}
