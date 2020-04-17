//
//  Show.swift
//  globoplay
//
//  Created by Marcos Curvello on 15/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation

struct Show {
    var backdropPath: String
    var createdBy: [Author]?
    var episodeRunTime: [Int]?
    var firstAirDate: String
    var genres: [Genre]?
    var homepage: String?
    var id: Int
    var inProduction: Bool?
    var languages: [String]?
    var lastAirDate: String?
    var lastEpisodeToAir: Episode?
    var name: String
    var nextEpisodeToAir: Episode?
    var networks: [Company]?
    var numberOfEpisodes: Int?
    var numberOfSeasons: Int?
    var originCountry: [String]
    var originalLanguage: String
    var originalName: String
    var overview: String
    var posterPath: String?
    var popularity: Double
    var productionCompanies: [Company]?
    var seasons: [Season]?
    var status: String?
    var type: String?
    var voteAverage: Double
    var voteCount: Int
    
    enum CodingKeys: String, CodingKey {
        case backdropPath = "backdrop_path"
        case createdBy = "created_by"
        case episodeRunTime = "episode_run_time"
        case firstAirDate = "first_air_date"
        case genres
        case homepage
        case id
        case inProduction = "in_production"
        case languages
        case lastAirDate = "last_air_date"
        case lastEpisodeToAir = "last_episode_to_air"
        case name
        case nextEpisodeToAir = "next_episode_to_air"
        case networks
        case numberOfEpisodes = "number_of_episodes"
        case numberOfSeasons = "number_of_seasons"
        case originCountry = "origin_country"
        case originalLanguage = "original_language"
        case originalName = "original_name"
        case overview
        case posterPath = "poster_path"
        case popularity
        case productionCompanies = "production_companies"
        case seasons
        case status
        case type
        case voteAverage = "vote_average"
        case voteCount = "vote_count"
    }
}

struct ShowResult: Codable, Identifiable {
    var posterPath: String?
    var popularity: Double
    var id: Int
    var backdropPath: String?
    var voteAverage: Double
    var overview: String
    var firstAirDate: String
    var originCountry: [String]
    var genreIds: [Int]
    var originalLanguage: String
    var voteCount: Int
    var name: String
    var originalName: String
    
    enum CodingKeys: String, CodingKey {
        case posterPath = "poster_path"
        case popularity
        case id
        case backdropPath = "backdrop_path"
        case voteAverage = "vote_average"
        case overview
        case firstAirDate = "first_air_date"
        case originCountry = "origin_country"
        case genreIds = "genre_ids"
        case originalLanguage = "original_language"
        case voteCount = "vote_count"
        case name
        case originalName = "original_name"
    }
}

struct Season: Codable {
    var airDate: String
    var episodeCount: Int
    var id: Int
    var name: String
    var overview: String
    var posterPath: String
    var seasonNumber: Int
    
    enum CodingKeys: String, CodingKey {
        case airDate = "air_date"
        case episodeCount = "episode_count"
        case id
        case name
        case overview
        case posterPath = "poster_path"
        case seasonNumber = "season_number"
    }
}

struct Episode: Codable {
    var airDate: String
    var episodeNumber: Int
    var id: Int
    var name: String
    var overview: String
    var productionCode: String
    var seasonNumber: Int
    var showId: Int
    var stillPath: String?
    var voteAverage: Double
    var voteCount: Int
    
    enum CodingKeys: String, CodingKey {
        case airDate = "air_date"
        case episodeNumber = "episode_number"
        case id
        case name
        case overview
        case productionCode = "production_code"
        case seasonNumber = "season_number"
        case showId = "show_id"
        case stillPath = "still_path"
        case voteAverage = "vote_average"
        case voteCount = "vote_count"
    }
}

struct Author: Codable {
    var id: Int
    var creditId: String
    var name: String
    var gender: Int
    var profilePath: String
    
    enum CodingKeys: String, CodingKey {
        case id
        case creditId = "credit_id"
        case name
        case gender
        case profilePath = "profile_path"
    }
}
