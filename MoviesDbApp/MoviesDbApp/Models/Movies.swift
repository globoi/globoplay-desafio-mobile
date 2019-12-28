//
//  Movies.swift
//  Movs
//
//  Created by gmmoraes on 20/12/19.
//  Copyright © 2019 gmmoraes. All rights reserved.
//

import Foundation

struct Info: Codable {
    var page: Int
    var total_pages: Int
    let results: [Movies]

}

struct Movies: Codable {
    let id: Int
    let title: String
    let popularity: Double
    let vote_count: Int
    let vote_average: Double
    let overview: String
    let poster_path: String
    let adult: Bool
    let genre_ids: [Int]
    let release_date: String
    let video: Bool
    
    enum CodingKeys: String, CodingKey {
        case id
        case title
        case popularity
        case vote_count
        case vote_average
        case overview
        case poster_path
        case adult
        case genre_ids
        case release_date
        case video
    }
    
}
