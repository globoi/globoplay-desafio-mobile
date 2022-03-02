//
//  MovieList+Fixtures.swift
//  MoviesAppTests
//
//  Created by Gustavo Tiecker on 01/03/22.
//

import XCTest
@testable import MoviesApp

extension MovieList {
    
    static func fixture() -> MovieList {
        let movies: [Movie] = [Movie.fixture()]
        return MovieList.init(id: "1", items: movies)
    }
}

extension Movie {
    
    static func fixture() -> Movie {
        .init(adult: false, backdropPath: "/123abc", genreIDS: [1, 2], id: 1, mediaType: "movie", originalLanguage: "English", originalTitle: "Spider-Man", overview: "A great movie", popularity: 2.2, posterPath: "/abc123", releaseDate: "04/02/2022", title: "Spider-Man", video: true, voteAverage: 2.2, voteCount: 3)
    }
}
