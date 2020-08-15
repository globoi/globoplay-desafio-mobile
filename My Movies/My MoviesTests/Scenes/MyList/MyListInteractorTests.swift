//
//  MyListInteractorTests.swift
//  My MoviesTests
//
//  Created by Rafael Valer on 15/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import XCTest
@testable import My_Movies

class MyListInteractorTests: XCTestCase {

    // MARK: - Subject under test
    var sut: MyListInteractor!
    
    // MARK: - Test lifecycle
    
    override func setUp() {
        super.setUp()
        setupMyListInteractor()
    }
    
    override func tearDown() {
        super.tearDown()
    }

    // MARK: - Test setup
    func setupMyListInteractor() {
        sut = MyListInteractor()
    }
    
    // MARK: - Test doubles
    
    class MyListPresentationLogicSpy: MyListPresentationLogic {
        
        // MARK: Method call expectations
        var presentFavoriteMoviesCalled = false
        var presentEmptyMessageCalled = false
        
        // MARK: Argument expectations
        var favMoviesResponse: MyListModels.FetchFavoriteMovies.Response!
        
        // MARK: Spied methods
        func presentFavoriteMovies(response: MyListModels.FetchFavoriteMovies.Response) {
            presentFavoriteMoviesCalled = true
            favMoviesResponse = response
        }
        
        func presentEmptyMessage() {
            presentEmptyMessageCalled = true
        }
    }
    
    class MyListWorkerSpy: MyListWorker {
        
        var shouldReturnEmpty = false
        
        // MARK: Method call expectations
        var fetchFavoriteMoviesCalled = false
        
        override func fetchFavoriteMovies(completion: @escaping (MyListWorker.FavoriteMoviesResponse) -> Void) {
            fetchFavoriteMoviesCalled = true
            
            if shouldReturnEmpty {
                completion(.emptyResult(()))
            } else {
                completion(.success([Seeds.Movies.movie1]))
            }
        }
    }
    
    // MARK: - Tests
    
    func testFetchFavoriteMoviesShouldAskPresenterToFormatResults() {
        
        // Given
        let myListPresentationLogicSpy = MyListPresentationLogicSpy()
        sut.presenter = myListPresentationLogicSpy
        let myListWorkerSpy = MyListWorkerSpy()
        myListWorkerSpy.shouldReturnEmpty = false
        sut.worker = myListWorkerSpy
        
        // When
        sut.fetchFavoriteMovies()
        
        // Then
        XCTAssert(myListWorkerSpy.fetchFavoriteMoviesCalled, "FetchFavoriteMovies should ask MyListWorker to fetch movies")
        XCTAssert(myListPresentationLogicSpy.presentFavoriteMoviesCalled, "FetchFavoriteMovies should ask presenter to format movies results")
    }
    
    func testFetchFavoriteMoviesWithNoResultShouldAskPresenterToPresentEmptyMessage() {
        // Given
        let myListPresentationLogicSpy = MyListPresentationLogicSpy()
        sut.presenter = myListPresentationLogicSpy
        let myListWorkerSpy = MyListWorkerSpy()
        myListWorkerSpy.shouldReturnEmpty = true
        sut.worker = myListWorkerSpy
        
        // When
        sut.fetchFavoriteMovies()
        
        // Then
        XCTAssert(myListWorkerSpy.fetchFavoriteMoviesCalled, "FetchFavoriteMovies should ask MyListWorker to fetch movies")
        XCTAssert(myListPresentationLogicSpy.presentEmptyMessageCalled, "FetchFavoriteMovies with no result should ask presenter to present an empty message")
    }
}
