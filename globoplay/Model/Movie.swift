//
//  Movie.swift
//  globoplay
//
//  Created by Marcos Curvello on 17/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//
import Foundation

struct Movie: Codable, Identifiable {
    var adult: Bool
    var backdropPath: String?
    var collection: MovieCollection?
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

// MARK: - MovieCollection

struct MovieCollection: Codable {
    var id: Int
    var name: String
    var posterPath: String?
    var backdropPath: String?
    
    enum CodingKeys: String, CodingKey {
        case id
        case name
        case posterPath = "poster_path"
        case backdropPath = "backdrop_path"
    }
}

// MARK: - Company

struct Company: Codable {
    var id: Int
    var logoPath: String?
    var name: String
    var originCountry: String
    
    enum CodingKeys: String, CodingKey {
        case name
        case id
        case logoPath = "logo_path"
        case originCountry = "origin_country"
    }
}

// MARK: - Country

struct Country: Codable {
    var iso: String
    var name: String
    
    enum CodingKeys: String, CodingKey {
        case iso = "iso_3166_1"
        case name
    }
}

// MARK: - Language

struct Language: Codable {
    var iso: String
    var name: String
    
    enum CodingKeys: String, CodingKey {
        case iso = "iso_639_1"
        case name
    }
}

// MARK: - Info

struct Info: Hashable, Identifiable {
    let id = UUID()
    var name: String
    var value: String
}


// MARK: - Genre

struct Genre: Codable, Hashable {
    var id: Int
    var name: String
}

extension Genre {
    static func random(sequence count: Int) -> [Genre] {
        var interations: Int = count
        var randomGenres: [Genre] = []
        
        while interations > 0 {
            let genre = GenreType.allCases.randomElement()!.genre
            guard !randomGenres.contains(genre) else {
                continue
            }
            
            randomGenres.append(genre)
            interations -= 1
        }
        return randomGenres
    }
}

// MARK: - Protocol: Detailable

protocol Detailable {
    var information: [Info] { get }
}

extension Movie: Detailable {
    var information: [Info] {
        var info: [Info] = [
            Info(name: "Original title", value: originalTitle),
            Info(
                name: "Genre",
                value: genres.map { $0.name }.joined(separator: ", ")
            )
        ]
        if productionCountries.count > 0 {
            info.append(
                Info(
                    name: "Production Countries",
                    value: productionCountries.map { $0.name }.joined(separator: ", ") )
            )
        }
        if productionCompanies.count > 0 {
            info.append(
                Info(
                    name: "Production Companies",
                    value: productionCompanies.map { $0.name }.joined(separator: ", ") )
            )
        }
        return info
    }
}

// MARK: - Protocol: Equatable

extension Movie: Equatable {
    static func == (lhs: Movie, rhs: Movie) -> Bool {
        lhs.id == rhs.id
    }
}
