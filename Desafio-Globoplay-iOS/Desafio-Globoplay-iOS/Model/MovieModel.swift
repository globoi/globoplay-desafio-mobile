//
//  MovieModel.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 23/02/22.
//

import Foundation

// MARK: - Movie Model

class Movie: Codable {
    
    let backdropPath, originalName: String?
    let originCountry: [String]?
    let voteCount: Int?
    let posterPath: String?
    let voteAverage: Double?
    let overview: String?
    let id: Int?
    let name, firstAirDate: String?
    let genreIDS: [Int]?
    let popularity: Double?
    let mediaType: MediaType?
    let originalLanguage: String?
    let releaseDate: String?
    let adult: Bool?
    let originalTitle: String?
    let video: Bool?
    let title: String?
    var urlImage: String?
    
    
    enum CodingKeys: String, CodingKey {
        case backdropPath = "backdrop_path"
        case originalName = "original_name"
        case originCountry = "origin_country"
        case originalLanguage = "original_language"
        case voteCount = "vote_count"
        case posterPath = "poster_path"
        case voteAverage = "vote_average"
        case overview, id, name
        case firstAirDate = "first_air_date"
        case genreIDS = "genre_ids"
        case popularity
        case mediaType = "media_type"
        case releaseDate = "release_date"
        case adult
        case originalTitle = "original_title"
        case video, title
        case urlImage
    }
}
