//
//  SearchInteractorTests.swift
//  My MoviesTests
//
//  Created by Rafael Valer on 17/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import XCTest
@testable import My_Movies

class SearchInteractorTests: XCTestCase {

    // MARK: - Subject under test
    var sut: SearchInteractor!
    
    // MARK: - Test lifecycle
    
    override func setUp() {
        super.setUp()
        setupSearchInteractor()
    }
    
    override func tearDown() {
        super.tearDown()
    }

    // MARK: - Test setup
    func setupSearchInteractor() {
        sut = SearchInteractor()
    }
    
    // MARK: - Test doubles
    
    class SearchPresentationLogicSpy: SearchPresentationLogic {

        // MARK: Method call expectations
        var presentFetchedMoviesCalled = false
        var presentErrorCalled = false
        
        // MARK: Spied methods
        func presentFetchedMovies(response: SearchModels.FetchSearchMovies.Response) {
            presentFetchedMoviesCalled = true
        }
        
        func presentError(_ error: Error) {
            presentErrorCalled = true
        }
    }
    
    class SearchWorkerSpy: SearchWorker {
        
        static let invalidQueryText: String = "dsa l$1 2ldasd 123asdl Sasldas d*&(!@# %20"
        
        // MARK: Method call expectations
        var fetchMoviesCalled = false
        
        override func fetchMovies(request: SearchModels.FetchSearchMovies.Request, completion: @escaping (SearchWorker.MoviesResponse) -> Void) {
            
            fetchMoviesCalled = true
            
            if request.queryText == SearchWorkerSpy.invalidQueryText {
                completion(.error(ApplicationError.commonError))
            } else {
                completion(.success([Seeds.Movies.movie1]))
            }
        }
    }

    // MARK: - Tests
    
    func testFetchSearchMoviesShouldAskSearchWorkerToFetchAndPresenterToFormatResults() {

        // Given
        let searchPresentationLogicSpy = SearchPresentationLogicSpy()
        sut.presenter = searchPresentationLogicSpy
        let searchWorkerSpy = SearchWorkerSpy()
        sut.worker = searchWorkerSpy

        // When
        sut.fetchSearchMovies("movie 1")

        // Then
        XCTAssert(searchWorkerSpy.fetchMoviesCalled, "FetchSearchMovies should ask SearchWorker to fetch movies")
        XCTAssert(searchPresentationLogicSpy.presentFetchedMoviesCalled, "FetchSearchMovies should ask presenter to format the movies")
    }

    func testFetchSearchMoviesWithInvalidInfoShouldAskPresenterToPresentError() {
        // Given
        let searchPresentationLogicSpy = SearchPresentationLogicSpy()
        sut.presenter = searchPresentationLogicSpy
        let searchWorkerSpy = SearchWorkerSpy()
        sut.worker = searchWorkerSpy

        // When
        sut.fetchSearchMovies(SearchWorkerSpy.invalidQueryText)

        // Then
        XCTAssert(searchPresentationLogicSpy.presentErrorCalled, "FetchSearchMovies with an invalid query text should ask presenter to present an error.")
    }
}
