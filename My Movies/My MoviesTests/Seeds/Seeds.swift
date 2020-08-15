//
//  Seeds.swift
//  My MoviesTests
//
//  Created by Rafael Valer on 15/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//


@testable import My_Movies

struct Seeds {
    
    struct Movies {
        static let movie1 = Movie(id: 1, title: "Movie 1",
                                  originalTitle: "Original Movie 1",
                                  overview: "Movie about a character that fights the evil",
                                  genres: [Genre(id: 3, name: "Comedy")],
                                  genreIds: [3], releaseDate: "07-10-2018",
                                  posterPath: "posterPath.jpg", backdropPath: nil, voteAverage: 7.9,
                                  productionCountries: [ProductionCountry(name: "Brazil")])
    }
}
