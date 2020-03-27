//
//  Movie.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 25/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import Foundation

struct Movie {
    let id: Int
    let title: String
    let pictureURL: String
    let backgroundURL: String
    let date: String
    let overview: String
    let runtime: Int
    let voteAverage: Double
    
    var smallPictureUrl: String {
        return "https://image.tmdb.org/t/p/w500\(pictureURL)"
    }
    
    var originalPictureUrl: String {
        return "https://image.tmdb.org/t/p/original\(pictureURL)"
    }
    
    var smallBackgroundUrl: String {
        return "https://image.tmdb.org/t/p/w500\(backgroundURL)"
    }
    
    var orignalBackgroundUrl: String {
        return "https://image.tmdb.org/t/p/original\(backgroundURL)"
    }
    
    init(dict: [String: Any]) {
        self.id = dict["id"] as? Int ?? 0
        self.title = dict["title"] as? String ?? ""
        self.pictureURL = dict["poster_path"] as? String ?? ""
        self.backgroundURL = dict["backdrop_path"] as? String ?? ""
        self.date = dict["release_date"] as? String ?? ""
        self.overview = dict["overview"] as? String ?? ""
        self.runtime = dict["runtime"] as? Int ?? 0
        self.voteAverage = dict["vote_average"] as? Double ?? 0
    }
    
    init() {
        self.id = 0
        self.title = ""
        self.pictureURL = ""
        self.backgroundURL = ""
        self.date = ""
        self.overview = ""
        self.runtime = 0
        self.voteAverage = 0
    }
}
