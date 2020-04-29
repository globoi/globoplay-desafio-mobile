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
    
    var movie: Movie?
    
    override func setUpWithError() throws {
        
        movie = Movie(
            adult: false,
            backdropPath: "/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg",
            collection: nil,
            budget: 3231,
            genres: [],
            homepage: nil,
            id: 1,
            imdbId: "imdb.com/heyf",
            originalLanguage: "English",
            originalTitle: "Forest Gump",
            overview: "Hello",
            popularity: 22.1,
            posterPath: "",
            productionCompanies: [],
            productionCountries: [],
            releaseDate: "",
            revenue: 12,
            runtime: 313,
            spokenLanguages: [],
            status: "",
            tagline: "",
            title: "Forest Gump",
            video: true,
            voteAverage: 10.0,
            voteCount: 50
        )
        
    }
    
    func testMovieToMovieObjectConversion_ShouldReturnTrue() {
        guard let movie = movie else {
            fatalError()
        }
        
        let movieObject = movie.managedObject()
        XCTAssertTrue(movieObject.isKind(of: MovieObject.self))
    }
    
    func testMovieObjectToMovie_ShouldReturnTrue() {
        guard let movie = movie else {
            fatalError()
        }
        
        let movieObject = movie.managedObject()
        let convertedMovie = Movie(managedObject: movieObject)
        XCTAssertTrue(convertedMovie == movie)
    }
    
    func testMovieDetailInformation_ShouldReturnTrue() {
        guard let movie = movie else {
            fatalError()
        }
        
        let title = movie.title
        let titleInfo = movie.information.first
        XCTAssertTrue(titleInfo?.value == title)
    }
    
}
