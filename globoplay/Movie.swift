//
//  Movie.swift
//  globoplay
//
//  Created by Marcos Curvello on 14/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation

struct Movie {
    var adult: Bool
    var backdropPath: String?
    var collection: Collection?
    var budge: Int
    var genres: [Genre]
    var homepage: String?
    var id: Int
    var imdbId: String
    var originalLanguage: String
    var originalTitle: String
    var overview: String?
    var popularity: Double
    var posterPath: String?
    var productionCompanies: [Company]
    var productionCountries: [Country]
    var releaseDate: String
    var revenue: Int
    var runtime: Int
    var spokenLanguages: [Language]
    var status: String
    var tagline: String?
    var title: String
    var video: Bool
    var voteAverage: Double
    var voteCount: Int
}

struct Collection {
    var id: Int
    var name: String
    var posterPath: String
    var backdropPath: String
}

struct Genre {
    var id: Int
    var name: String
}

struct Language {
    var iso: String
    var name: String
}

struct Country {
    var iso: String
    var name: String
}

struct Company {
    var name: String
    var id: Int
    var logoPath: String?
    var originCountry: String
}

