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
                                  genreIds: [3], releaseDate: "2018-10-21",
                                  posterPath: "posterPath.jpg",
                                  backdropPath: "backdropPath.jpg", voteAverage: 7.9,
                                  productionCountries: [Seeds.Countries.country1],
                                  productionCompanies: [Seeds.Companies.company1])
    }
    
    struct Videos {
        static let video1 = Video(key: "key1", site: "site1", type: "trailer")
        static let video2 = Video(key: "key2", site: "site2", type: "trailer")
    }
    
    struct Companies {
        static let company1 = Company(id: 1, name: "Company 1")
    }
    
    struct Countries {
        static let country1 = ProductionCountry(name: "Brazil")
    }
}
