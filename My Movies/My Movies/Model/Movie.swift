//
//  Movie.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

struct Movie {
    let id: Int?
    let title: String?
    let originalTitle: String?
    let overview: String?
    let genres: [Genre]?
    let genreIds: [Int]?
    let releaseDate: String?
    let posterPath: String?
    let backdropPath: String?
    let voteAverage: Double?
    let productionCountries: [ProductionCountry]?
    let productionCompanies: [Company]?
}

extension Movie: Decodable {
    
    enum CodingKeys: String, CodingKey {
        case id, title, overview, genres
        case originalTitle = "original_title"
        case releaseDate = "release_date"
        case posterPath = "poster_path"
        case genreIds = "genre_ids"
        case backdropPath = "backdrop_path"
        case voteAverage = "vote_average"
        case productionCountries = "production_countries"
        case productionCompanies = "production_companies"
    }
    
    init?(data: Data) {
        guard let movie = try? JSONDecoder.defaultDecoder.decode(Movie.self, from: data) else {
            return nil
        }
        self = movie
    }
}
