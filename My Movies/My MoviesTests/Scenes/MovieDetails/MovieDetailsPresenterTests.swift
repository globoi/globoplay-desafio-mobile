//
//  MovieDetailsPresenterTests.swift
//  My MoviesTests
//
//  Created by Rafael Valer on 15/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import XCTest
@testable import My_Movies

class MovieDetailsPresenterTests: XCTestCase {
    
    // MARK: - Subject under test
    var sut: MovieDetailsPresenter!

    // MARK: - Test lifecycle
    override func setUp() {
        super.setUp()
        setupMovieDetailsPresenter()
    }
    
    override func tearDown() {
        super.tearDown()
    }

    // MARK: - Test setup
    func setupMovieDetailsPresenter() {
        sut = MovieDetailsPresenter()
    }
    
    // MARK: - Test doubles
    class MovieDetailsDisplayLogicSpy: MovieDetailsDisplayLogic {
        
        // MARK: Method call expectations
        var displayFetchedMovieDetailsCalled = false
        var displayFetchedMovieRecommendationsCalled = false
        var displayFavoriteButtonCalled = false
        var displayFetchedTrailerCalled = false
        var displayErrorAlertCalled = false
        
        // MARK: Argument expectations
        var fetchedMovieDetailsViewModel: MovieDetailsModels.FetchMovieDetails.ViewModel!
        var fetchedMovieRecommendationsViewModel: MovieDetailsModels.FetchMovieRecommendations.ViewModel!
        var fetchedMovieTraillerURL: URL!
        var favoriteButtonText: String!
        
        // MARK: Spied methods
        func displayFetchedMovieDetails(viewModel: MovieDetailsModels.FetchMovieDetails.ViewModel) {
            displayFetchedMovieDetailsCalled = true
            fetchedMovieDetailsViewModel = viewModel
        }
        
        func displayFetchedMovieRecommendations(viewModel: MovieDetailsModels.FetchMovieRecommendations.ViewModel) {
            displayFetchedMovieRecommendationsCalled = true
            fetchedMovieRecommendationsViewModel = viewModel
        }
        
        func displayFavoriteButton(withImage image: UIImage?, text: String) {
            displayFavoriteButtonCalled = true
            favoriteButtonText = text
        }
        
        func displayTrailer(withURL url: URL) {
            fetchedMovieTraillerURL = url
            displayFetchedTrailerCalled = true
        }
        
        func displayErrorAlert(withTitle title: String, message: String) {
            displayErrorAlertCalled = true
        }
    }
    
    // MARK: - Tests
    
    func testPresentFetchedMovieDetails() {
        
        // Given
        let movieDetailsDisplayLogicSpy = MovieDetailsDisplayLogicSpy()
        sut.viewController = movieDetailsDisplayLogicSpy
        
        // When
        let movie = Seeds.Movies.movie1
        let response = MovieDetailsModels.FetchMovieDetails.Response(movie: movie)
        sut.presentFetchedMovieDetails(response: response)
        
        // Then
        let displayedMovie = movieDetailsDisplayLogicSpy.fetchedMovieDetailsViewModel.displayedMovie
        
        XCTAssertEqual(displayedMovie.title, "Movie 1")
        XCTAssertEqual(displayedMovie.posterPath, Constants.baseImagesURL + "w185/posterPath.jpg")
        XCTAssertEqual(displayedMovie.backdropPath, Constants.baseImagesURL + "w500/backdropPath.jpg")
        XCTAssertEqual(displayedMovie.overview, "Movie about a character that fights the evil")
        XCTAssertEqual(displayedMovie.type, "Filme")
        XCTAssertEqual(displayedMovie.releaseDate, "Data de Lançamento: 21/10/2018")
        XCTAssertEqual(displayedMovie.genres, "Gêneros: Comedy")
        XCTAssertEqual(displayedMovie.originalTitle, "Título Original: Original Movie 1")
        XCTAssertEqual(displayedMovie.score, "Avaliação: 7.9/10")
        XCTAssertEqual(displayedMovie.productionCountries, "Países: Brazil")
        
        XCTAssert(movieDetailsDisplayLogicSpy.displayFetchedMovieDetailsCalled, "Presenting fetched movie details should ask view controller to display them.")
    }
    
    func testPresentFetchedRecommendationMovies() {
        
        // Given
        let movieDetailsDisplayLogicSpy = MovieDetailsDisplayLogicSpy()
        sut.viewController = movieDetailsDisplayLogicSpy
        
        // When
        let movie = Seeds.Movies.movie1
        let response = MovieDetailsModels.FetchMovieRecommendations.Response(movies: [movie])
        sut.presentFetchedRecommendations(response: response)

        // Then
        let displayedMovies = movieDetailsDisplayLogicSpy.fetchedMovieRecommendationsViewModel.displayedMovies
        
        for displayedMovie in displayedMovies {
            XCTAssertEqual(displayedMovie.title, "Movie 1")
            XCTAssertEqual(displayedMovie.posterPath, Constants.baseImagesURL + "w185/posterPath.jpg")
        }
        
        XCTAssert(movieDetailsDisplayLogicSpy.displayFetchedMovieRecommendationsCalled, "Presenting fetched movie recommendations should ask view controller to display them.")
    }
    
    func testPresentFavoriteButton() {
        // Given
        let movieDetailsDisplayLogicSpy = MovieDetailsDisplayLogicSpy()
        sut.viewController = movieDetailsDisplayLogicSpy
        
        // When
        sut.presentIsMovieOnFavorites(true)
        
        // Then
        XCTAssertEqual(movieDetailsDisplayLogicSpy.favoriteButtonText, "Adicionado")
        XCTAssert(movieDetailsDisplayLogicSpy.displayFavoriteButtonCalled, "Presenting movie is on favorites should ask view controller to display it.")
    }
    
    func testPresentFetchedMovieTrailer() {
        // Given
        let movieDetailsDisplayLogicSpy = MovieDetailsDisplayLogicSpy()
        sut.viewController = movieDetailsDisplayLogicSpy
        
        // When
        let response = MovieDetailsModels.FetchMovieTrailer.Response(trailer: Seeds.Videos.video1)
        sut.presentFetchedTrailer(response: response)

        // Then
        let displayedTrailer = movieDetailsDisplayLogicSpy.fetchedMovieTraillerURL
        XCTAssertNotNil(displayedTrailer, "URL to be displayed should not be nil")
        
        XCTAssert(movieDetailsDisplayLogicSpy.displayFetchedTrailerCalled, "Presenting fetched movie trailer should ask view controller to display it.")
    }
}
