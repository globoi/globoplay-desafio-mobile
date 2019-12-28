//
//  Series.swift
//  Movs
//
//  Created by gmmoraes on 21/12/19.
//  Copyright © 2019 gmmoraes. All rights reserved.
//

import Foundation

struct SeriesInfo: Codable {
    let page: Int
    let total_pages: Int
    let results: [Series]

}

struct Series: Codable {
    let id: Int
    let name: String
    let popularity: Double
    let vote_count: Int
    let vote_average: Double
    let overview: String
    let poster_path: String
    let genre_ids: [Int]
    let first_air_date: String
    
    enum CodingKeys: String, CodingKey {
        case id
        case name
        case popularity
        case vote_count
        case vote_average
        case overview
        case poster_path
        case genre_ids
        case first_air_date
    }
    
}

//struct SearchResults<T: Decodable>: Decodable {
//    let results: [T]
//    let info: T
//}



/**
 
 
 
 {"Expected to decode Double but found a string/data instead.", underlyingError: nil))
 "page": 1,
 "total_results": 10000,
 "total_pages": 500,
 "results": [
   {
     "popularity": 432.363,
     "vote_count": 189,
     "video": false,
     "poster_path": "/l4iknLOenijaB85Zyb5SxH1gGz8.jpg",
     "id": 512200,
     "adult": false,
     "backdrop_path": "/zTxHf9iIOCqRbxvl8W5QYKrsMLq.jpg",
     "original_language": "en",
     "original_title": "Jumanji: The Next Level",
     "genre_ids": [
       28,
       12,
       35,
       14
     ],
     "title": "Jumanji: The Next Level",
     "vote_average": 6.8,
     "overview": "In Jumanji: The Next Level, the gang is back but the game has changed. As they return to rescue one of their own, the players will have to brave parts unknown from arid deserts to snowy mountains, to escape the world's most dangerous game.",
     "release_date": "2019-12-04"
   },
 */
