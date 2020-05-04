//
//  MovieTests.swift
//  globoplayTests
//
//  Created by Marcos Curvello on 24/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import XCTest
@testable import globoplay

class MovieTests: XCTestCase {
    
    var sut: Movie?
    
    override func setUp() {
        
        sut = Movie(
            adult: false,
            backdropPath: "/jOzrELAzFxtMx2I4uDGHOotdfsS.jpg",
            collection:
            MovieCollection(
                id: 10,
                name: "Star Wars Collection",
                posterPath: "/r8Ph5MYXL04Qzu4QBbq2KjqwtkQ.jpg",
                backdropPath: "/d8duYyyC9J5T825Hg7grmaabfxQ.jpg"
            ),
            budget: 250000000,
            genres: [
                Genre(id: 28, name: "Action"),
                Genre(id: 12, name: "Adventure"),
                Genre(id: 878, name: "Science Fiction")
            ],
            homepage: "https://www.starwars.com/films/star-wars-episode-ix-the-rise-of-skywalker",
            id: 181812,
            imdbId: "tt2527338",
            originalLanguage: "en",
            originalTitle: "Star Wars: The Rise of Skywalker",
            overview: "The surviving Resistance faces the First Order once again as the journey of Rey, Finn and Poe Dameron continues. With the power and knowledge of generations behind them, the final battle begins.",
            popularity: 151.648,
            posterPath: "/db32LaOibwEliAmSL2jjDF6oDdj.jpg",
            productionCompanies: [
                Company(id: 1, logoPath: "/o86DbpburjxrqAzEDhXZcyE8pDb.png", name: "Lucasfilm", originCountry: "US"),
                Company(id: 11461, logoPath: "/p9FoEt5shEKRWRKVIlvFaEmRnun.png", name: "Bad Robot", originCountry: "US"),
                Company(id: 2, logoPath: "/wdrCwmRnLFJhEoH8GSfymY85KHT.png", name: "Walt Disney Picturs", originCountry: "US"),
                Company(id: 120404, logoPath: nil, name: "British Film Comission", originCountry: "")
            ],
            productionCountries: [
                Country(iso: "US", name: "United States of America")
            ],
            releaseDate: "2019-12-18",
            revenue: 1073604458,
            runtime: 142,
            spokenLanguages: [
                Language(iso: "en", name: "English")
            ],
            status: "Released",
            tagline: "Every generation has a legend",
            title: "Star Wars: The Rise of Skywalker",
            video: false,
            voteAverage: 6.5,
            voteCount: 4328
        )
    }
    
    func testMovieToMovieObjectConversion_ShouldReturnTrue() {
        guard let movie = sut else {
            fatalError()
        }
        
        let movieObject = movie.managedObject()
        XCTAssertTrue(movieObject.isKind(of: MovieObject.self))
    }
    
    func testMovieObjectToMovie_ShouldReturnTrue() {
        guard let movie = sut else {
            fatalError()
        }
        
        let movieObject = movie.managedObject()
        let convertedMovie = Movie(managedObject: movieObject)
        XCTAssertTrue(convertedMovie == movie)
    }
    
    func testMovieDetailInformation_ShouldReturnTrue() {
        guard let movie = sut else {
            fatalError()
        }
        
        let title = "Star Wars: The Rise of Skywalker"
        let titleInfo = movie.information.first
        
        XCTAssertTrue(titleInfo?.value == title)
    }
    
}
