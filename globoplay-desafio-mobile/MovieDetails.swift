//
//  MovieDetails.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 09/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import Foundation

struct MovieDetails: Decodable {
    
    var original_title  : String
    var genres          : [Genre]?
    var release_date    : String
    var overview        : String
    var vote_average    : Double

}
