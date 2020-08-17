//
//  MovieDetailsInteractorTests.swift
//  My MoviesTests
//
//  Created by Rafael Valer on 16/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import XCTest
@testable import My_Movies

class MovieDetailsInteractorTests: XCTestCase {

    // MARK: - Subject under test
    var sut: MovieDetailsInteractor!
    
    // MARK: - Test lifecycle
    
    override func setUp() {
        super.setUp()
        setupMovieDetailsInteractor()
    }
    
    override func tearDown() {
        super.tearDown()
    }

    // MARK: - Test setup
    func setupMovieDetailsInteractor() {
        sut = MovieDetailsInteractor()
    }

    // MARK: - Test doubles
    
    class MovieDetailsPresentationLogicSpy: MovieDetailsPresentationLogic {
        
        // MARK: Method call expectations
        var presentFetchedMovieDetailsCalled = false
        var presentFetchedRecommendationsCalled = false
        var presentIsMovieOnFavoritesCalled = false
        var presentErrorCalled = false
        var presentFetchedTrailerCalled = false
        
        // MARK: Argument expectations
        var movieDetailsResponse: MovieDetailsModels.FetchMovieDetails.Response!
        var movieRecommendationsResponse: MovieDetailsModels.FetchMovieRecommendations.Response!
        var isFavorite: Bool!
        
        // MARK: Spied methods
        func presentFetchedMovieDetails(response: MovieDetailsModels.FetchMovieDetails.Response) {
            presentFetchedMovieDetailsCalled = true
            movieDetailsResponse = response
        }
        
        func presentFetchedRecommendations(response: MovieDetailsModels.FetchMovieRecommendations.Response) {
            presentFetchedRecommendationsCalled = true
            movieRecommendationsResponse = response
        }
        
        func presentIsMovieOnFavorites(_ isFavorite: Bool) {
            presentIsMovieOnFavoritesCalled = true
        }
        
        func presentError(_ error: Error) {
            presentErrorCalled = true
        }
        
        func presentFetchedTrailer(response: MovieDetailsModels.FetchMovieTrailer.Response) {
            presentFetchedTrailerCalled = true
        }
    }
    
    class MovieDetailsWorkerSpy: MovieDetailsWorker {

        static let invalidMovieId = -1
        
        var fetchMovieDetailsCalled = false
        var fetchMovieRecommendationsCalled = false
        var fetchMovieTrailerCalled = false
        
        override func fetchMovieDetails(request: MovieDetailsModels.FetchMovieDetails.Request, completion: @escaping (MovieDetailsWorker.MovieDetailsResponse) -> Void) {
            
            fetchMovieDetailsCalled = true
            if request.movieId == String(MovieDetailsWorkerSpy.invalidMovieId) {
                completion(.error(ApplicationError.commonError))
            } else {
                completion(.success(Seeds.Movies.movie1))
            }
            
        }
        
        override func fetchMovieRecommendations(request: MovieDetailsModels.FetchMovieRecommendations.Request, completion: @escaping (MovieDetailsWorker.MovieRecommendationsResponse) -> Void) {
            
            fetchMovieRecommendationsCalled = true
            if request.movieId == String(MovieDetailsWorkerSpy.invalidMovieId) {
                completion(.error(ApplicationError.commonError))
            } else {
                completion(.success([Seeds.Movies.movie1]))
            }
        }
        
        override func fetchMovieTrailer(request: MovieDetailsModels.FetchMovieTrailer.Request, completion: @escaping (MovieDetailsWorker.MovieTrailerResponse) -> Void) {
            
            fetchMovieTrailerCalled = true
            completion(.success(Seeds.Videos.video1))
        }
    }
    
    // MARK: - Tests
    
    func testFetchMovieDetailsShouldAskMovieDetailsWorkerToFetchAndPresenterToFormatResults() {
        
        // Given
        let movieDetailsPresentationLogicSpy = MovieDetailsPresentationLogicSpy()
        sut.presenter = movieDetailsPresentationLogicSpy
        let movieDetailsWorkerSpy = MovieDetailsWorkerSpy()
        sut.worker = movieDetailsWorkerSpy
        
        // When
        sut.fetchMovieDetails(1)
        
        // Then
        XCTAssert(movieDetailsWorkerSpy.fetchMovieDetailsCalled, "FetchMoviesDetails should ask MovieDetailsWorker to fetch movie details")
        XCTAssert(movieDetailsPresentationLogicSpy.presentFetchedMovieDetailsCalled, "FetchMovieDetails should ask presenter to format the movie result")
    }
    
    func testFetchMovieDetailsWithInvalidInfoShouldPresentError() {
        // Given
        let movieDetailsPresentationLogicSpy = MovieDetailsPresentationLogicSpy()
        sut.presenter = movieDetailsPresentationLogicSpy
        let movieDetailsWorkerSpy = MovieDetailsWorkerSpy()
        sut.worker = movieDetailsWorkerSpy
        
        // When
        sut.fetchMovieDetails(MovieDetailsWorkerSpy.invalidMovieId)
        
        XCTAssert(movieDetailsPresentationLogicSpy.presentErrorCalled, "FetchMoviesDetails with invalid id should ask presenter to present error")
    }
    
    func testFetchMovieRecommendationsShouldAskMovieDetailsWorkerToFetchAndPresenterToFormatResults() {
        // Given
        let movieDetailsPresentationLogicSpy = MovieDetailsPresentationLogicSpy()
        sut.presenter = movieDetailsPresentationLogicSpy
        let movieDetailsWorkerSpy = MovieDetailsWorkerSpy()
        sut.worker = movieDetailsWorkerSpy
        
        // When
        sut.fetchMovieRecommendations(1)
        
        // Then
        XCTAssert(movieDetailsWorkerSpy.fetchMovieRecommendationsCalled, "FetchMoviesRecommendations should ask MovieDetailsWorker to fetch movie recommendations")
        XCTAssert(movieDetailsPresentationLogicSpy.presentFetchedRecommendationsCalled, "FetchMovieRecommendations should ask presenter to format the movies result")
    }
    
    func testFetchMovieRecommendationsWithInvalidInfoShouldPresentError() {
        // Given
        let movieDetailsPresentationLogicSpy = MovieDetailsPresentationLogicSpy()
        sut.presenter = movieDetailsPresentationLogicSpy
        let movieDetailsWorkerSpy = MovieDetailsWorkerSpy()
        sut.worker = movieDetailsWorkerSpy
        
        // When
        sut.fetchMovieRecommendations(MovieDetailsWorkerSpy.invalidMovieId)
        
        XCTAssert(movieDetailsPresentationLogicSpy.presentErrorCalled, "FetchMoviesDetails with invalid id should ask presenter to present error")
    }
    
    func testFetchMovieTrailerShouldAskMovieDetailsWorkerToFetchAndPresenterToFormatResults() {
        // Given
        let movieDetailsPresentationLogicSpy = MovieDetailsPresentationLogicSpy()
        sut.presenter = movieDetailsPresentationLogicSpy
        let movieDetailsWorkerSpy = MovieDetailsWorkerSpy()
        sut.worker = movieDetailsWorkerSpy
        
        // When
        sut.fetchMovieTrailer(1)
        
        // Then
        XCTAssert(movieDetailsWorkerSpy.fetchMovieTrailerCalled, "FetchMovieTrailer should ask MovieDetailsWorker to fetch movie trailer")
        XCTAssert(movieDetailsPresentationLogicSpy.presentFetchedTrailerCalled, "FetchMovieTrailer should ask presenter to format the video result")
    }
}
