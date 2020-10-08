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

struct RootS : Decodable {
   let results: [Serie]
}

struct Movie:  Decodable {
    
    var id: Int
    var title: String
    var poster_path: String

}

struct Serie: Decodable {
    
    var id: Int
    var poster_path: String
    var overview: String
    var name: String
    
}
