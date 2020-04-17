//
//  Discover.swift
//  globoplay
//
//  Created by Marcos Curvello on 17/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation

struct Discover<A: Codable>: Codable {
    var id: Int?
    var page: Int
    var totalResults: Int
    var totalPages: Int
    var results: [A]
    
    enum CodingKeys: String, CodingKey {
        case id
        case page
        case totalResults = "total_results"
        case totalPages = "total_pages"
        case results
    }
}
