//
//  Movie.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 05/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import Foundation
import ObjectMapper


struct Root : Decodable {
   let results: [Movie]
}

struct VideoRoot : Decodable {
   let results: [Trailer]
}

struct Movie:  Decodable {
    
    var id: Int
    var title: String
    var poster_path: String
    var overview: String
    var vote_average: Double
    var release_date: String

}

struct Trailer:  Decodable {
    
    var key: String
}

struct MovieDetails: Decodable {
    
    var original_title : String
    var genres : [Genre]?
    var release_date: String
    var overview: String
    var vote_average: Double

}

struct Genre:  Decodable {
    
    var id: Int
    var name: String

}
