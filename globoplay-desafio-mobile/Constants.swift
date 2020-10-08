//
//  Constants.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 05/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import Foundation

//"https://api.themoviedb.org/3/movie/upcoming?api_key=2a0eb1c99630d71df118961ee0b5864e&language=en-US&page=1

struct CONST{
    struct API_CONSTANTS {
        static let API_KEY          = "api_key=2a0eb1c99630d71df118961ee0b5864e"
        static let BASE_URL         = "https://api.themoviedb.org/3/"
        static let MOVIE            = "movie/"
        static let TV               = "tv/"
        static let UPCOMING         = "upcoming?"
        static let POPULAR          = "popular?"
        static let NOW_PLAYING      = "now_playing?"
        static let VIDEO            = "/videos?"
        static let LANGUAGE_EN      = "&language=en-US&page=1"
        static let BASE_IMAGE_URL   = "https://image.tmdb.org/t/p/w185"
    }
}
