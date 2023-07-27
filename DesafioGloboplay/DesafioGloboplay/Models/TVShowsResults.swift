//
//  TVShowsResults.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import Foundation
struct TVShowsResults: Codable {
    var page: Int?
    var results: [TVShowResult]?
    var totalPages, totalResults: Int?

    enum CodingKeys: String, CodingKey {
        case page, results
        case totalPages = "total_pages"
        case totalResults = "total_results"
    }
}

// MARK: - Result
struct TVShowResult: Codable, Result {
    var backdropPath: String?
    var firstAirDate: String?
    var genreIDS: [Int]?
    var id: Int?
    var name: String?
    var originCountry: [String]?
    var originalLanguage, originalName, overview: String?
    var popularity: Double?
    var posterPath: String?
    var voteAverage: Double?
    var voteCount: Int?

    enum CodingKeys: String, CodingKey {
        case backdropPath = "backdrop_path"
        case firstAirDate = "first_air_date"
        case genreIDS = "genre_ids"
        case id, name
        case originCountry = "origin_country"
        case originalLanguage = "original_language"
        case originalName = "original_name"
        case overview, popularity
        case posterPath = "poster_path"
        case voteAverage = "vote_average"
        case voteCount = "vote_count"
    }
    
    func getTitle() -> String? {
        return name
    }
}
