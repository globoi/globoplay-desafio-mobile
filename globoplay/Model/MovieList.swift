//
//  MovieList.swift
//  globoplay
//
//  Created by Marcos Curvello on 28/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation

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

extension MovieList {
    init(movie: Movie) {
        self.posterPath = movie.posterPath
        self.adult = movie.adult
        self.overview = movie.overview ?? ""
        self.releaseDate = movie.releaseDate
        self.genreIds = movie.genres.map { $0.id }
        self.id = movie.id
        self.originalTitle = movie.originalTitle
        self.originalLanguage = movie.originalLanguage
        self.title = movie.title
        self.backdropPath = movie.backdropPath
        self.popularity = movie.popularity
        self.voteCount = movie.voteCount
        self.video = movie.video
        self.voteAverage = movie.voteAverage
    }
    
    init(movieObject: MovieObject) {
        self.posterPath = movieObject.posterPath
        self.adult = movieObject.adult
        self.overview = movieObject.overview ?? ""
        self.releaseDate = movieObject.releaseDate
        self.genreIds = movieObject.genres != nil ? movieObject.genres!.map { $0.id } : []
        self.id = Int(movieObject.id)!
        self.originalTitle = movieObject.originalTitle
        self.originalLanguage = movieObject.originalLanguage
        self.title = movieObject.title
        self.backdropPath = movieObject.backdropPath
        self.popularity = movieObject.popularity
        self.voteCount = movieObject.voteCount
        self.video = movieObject.video
        self.voteAverage = movieObject.voteAverage
    }
}

