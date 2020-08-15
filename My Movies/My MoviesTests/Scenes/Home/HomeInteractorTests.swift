//
//  HomeInteractorTests.swift
//  My MoviesTests
//
//  Created by Rafael Valer on 15/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import XCTest
@testable import My_Movies

class HomeInteractorTests: XCTestCase {

    // MARK: - Subject under test
    var sut: HomeInteractor!
    
    // MARK: - Test lifecycle
    
    override func setUp() {
        super.setUp()
        setupHomeInteractor()
    }
    
    override func tearDown() {
        super.tearDown()
    }

    // MARK: - Test setup
    func setupHomeInteractor() {
        sut = HomeInteractor()
    }
    
    // MARK: - Test doubles
    
    class HomePresentationLogicSpy: HomePresentationLogic {
        
        // MARK: Method call expectations
        var presentFetchedMoviesCalled = false
        var presentErrorCalled = false
        
        // MARK: Argument expectations
        var moviesResponse: HomeModels.FetchMovies.Response!
        
        // MARK: Spied methods
        func presentFetchedMovies(response: HomeModels.FetchMovies.Response) {
            presentFetchedMoviesCalled = true
            moviesResponse = response
        }
        
        func presentError(_ error: Error) {
            presentErrorCalled = true
        }
    }
    
    class HomeWorkerSpy: HomeWorker {
        
        static let invalidGenreId: Int = -1
        
        // MARK: Method call expectations
        var fetchMoviesCalled = false
        
        override func fetchMovies(request: HomeModels.FetchMovies.Request, completion: @escaping (HomeWorker.MoviesResponse) -> Void) {
            fetchMoviesCalled = true
            
            if request.genre == HomeWorkerSpy.invalidGenreId {
                completion(.error(ApplicationError.commonError))
            } else {
                completion(.success([Seeds.Movies.movie1]))
            }
        }
    }
    
    // MARK: - Tests
    
    func testFetchMoviesShouldAskHomeWorkerToFetchAndPresenterToFormatResults() {
        
        // Given
        let homePresentationLogicSpy = HomePresentationLogicSpy()
        sut.presenter = homePresentationLogicSpy
        let homeWorkerSpy = HomeWorkerSpy()
        sut.worker = homeWorkerSpy
        
        // When
        sut.fetchMovies(withPage: 1, genre: 1)
        
        // Then
        XCTAssert(homeWorkerSpy.fetchMoviesCalled, "FetchMovies should ask HomeWorker to fetch movies")
        XCTAssert(homePresentationLogicSpy.presentFetchedMoviesCalled, "FetchMovies should ask presenter to format the movies")
    }
    
    func testFetchMoviesWithInvalidInfoShouldAskPresenterToPresentError() {
        // Given
        let homePresentationLogicSpy = HomePresentationLogicSpy()
        sut.presenter = homePresentationLogicSpy
        let homeWorkerSpy = HomeWorkerSpy()
        sut.worker = homeWorkerSpy
        
        // When
        sut.fetchMovies(withPage: 1, genre: HomeWorkerSpy.invalidGenreId)
        
        // Then
        XCTAssert(homePresentationLogicSpy.presentErrorCalled, "FetchMovies with an invalid genre id should ask presenter to present an error.")
    }
}
