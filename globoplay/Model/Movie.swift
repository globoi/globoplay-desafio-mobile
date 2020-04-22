//
//  Movie.swift
//  globoplay
//
//  Created by Marcos Curvello on 17/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

struct Movie: Codable, Identifiable {
    var adult: Bool
    var backdropPath: String?
    var collection: Collection?
    var budget: Int?
    var genres: [Genre]
    var homepage: String?
    var id: Int
    var imdbId: String?
    var originalLanguage: String
    var originalTitle: String
    var overview: String?
    var popularity: Double
    var posterPath: String?
    var productionCompanies: [Company]
    var productionCountries: [Country]
    var releaseDate: String
    var revenue: Int
    var runtime: Int?
    var spokenLanguages: [Language]
    var status: String
    var tagline: String?
    var title: String
    var video: Bool
    var voteAverage: Double
    var voteCount: Int
    
    enum CodingKeys: String, CodingKey {
        case adult
        case backdropPath = "backdrop_path"
        case collection = "belongs_to_collection"
        case budget
        case genres
        case homepage
        case id
        case imdbId = "imdb_id"
        case originalLanguage = "original_language"
        case originalTitle = "original_title"
        case overview
        case popularity
        case posterPath = "poster_path"
        case productionCompanies = "production_companies"
        case productionCountries = "production_countries"
        case releaseDate = "release_date"
        case revenue
        case runtime
        case spokenLanguages = "spoken_languages"
        case status
        case tagline
        case title
        case video
        case voteAverage = "vote_average"
        case voteCount = "vote_count"
    }
}

extension Movie {
    init(from object: MovieObject) {
        self.init(
            adult: object.adult,
            backdropPath: object.backdropPath,
            collection: object.collection,
            budget: object.budget,
            genres: object.genres ?? [],
            homepage: object.homepage,
            id: object.id,
            imdbId: object.imdbId,
            originalLanguage: object.originalLanguage,
            originalTitle: object.title,
            overview: object.overview,
            popularity: object.popularity,
            posterPath: object.posterPath,
            productionCompanies: object.productionCompanies ?? [],
            productionCountries: object.productionCountries ?? [],
            releaseDate: object.releaseDate,
            revenue: object.revenue,
            runtime: object.runtime,
            spokenLanguages: object.spokenLanguages ?? [],
            status: object.status,
            tagline: object.tagline,
            title: object.title,
            video: object.video,
            voteAverage: object.voteAverage,
            voteCount: object.voteCount)
    }
}

extension Movie: Detailable {
    var information: [Info] {
        [
            Info(id: id, name: "Título Original", value: originalTitle),
            Info(id: id, name: "Gênero", value: genres.first?.name.capitalized ?? ""),
            Info(id: id, name: "Ano de produção", value: releaseDate)
        ]
    }
}

extension Movie: Equatable {
    static func == (lhs: Movie, rhs: Movie) -> Bool {
        lhs.id == rhs.id
    }
}

struct Collection: Codable {
    var id: Int
    var name: String
    var posterPath: String
    var backdropPath: String
    
    enum CodingKeys: String, CodingKey {
        case id
        case name
        case posterPath = "poster_path"
        case backdropPath = "backdrop_path"
    }
}

struct Genre: Codable {
    var id: Int
    var name: String
}

struct Company: Codable {
    var name: String
    var id: Int
    var logoPath: String?
    var originCountry: String
    
    enum CodingKeys: String, CodingKey {
        case name
        case id
        case logoPath = "logo_path"
        case originCountry = "origin_country"
    }
}

struct Country: Codable {
    var iso: String
    var name: String
    
    enum CodingKeys: String, CodingKey {
        case iso = "iso_3166_1"
        case name
    }
}

struct Language: Codable {
    var iso: String
    var name: String
    
    enum CodingKeys: String, CodingKey {
        case iso = "iso_639_1"
        case name
    }
}

struct MovieList: Codable, Identifiable, Hashable {
    var posterPath: String?
    var adult: Bool
    var overview: String
    var releaseDate: String
    var genreIds: [Int]
    var id: Int
    var originalTitle: String
    var originalLanguage: String
    var title: String
    var backdropPath: String?
    var popularity: Double
    var voteCount: Int
    var video: Bool
    var voteAverage: Double
    
    enum CodingKeys: String, CodingKey {
        case posterPath = "poster_path"
        case adult
        case overview
        case releaseDate = "release_date"
        case genreIds = "genre_ids"
        case id
        case originalTitle = "original_title"
        case originalLanguage = "original_language"
        case title
        case backdropPath = "backdrop_path"
        case popularity
        case voteCount = "vote_count"
        case video
        case voteAverage = "vote_average"
    }
}

protocol Detailable {
    var information: [Info] { get }
}

struct Info: Hashable, Identifiable {
    var id: Int
    var name: String
    var value: String
}
