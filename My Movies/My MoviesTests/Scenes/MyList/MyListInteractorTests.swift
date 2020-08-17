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
        
        // MARK: Argument expectations
        var favMoviesResponse: MyListModels.FetchFavoriteMovies.Response!
        
        // MARK: Spied methods
        func presentFavoriteMovies(response: MyListModels.FetchFavoriteMovies.Response) {
            presentFavoriteMoviesCalled = true
            favMoviesResponse = response
        }
    }
    
    class MyListWorkerSpy: MyListWorker {
        
        // MARK: Method call expectations
        var fetchFavoriteMoviesCalled = false
        
        override func fetchFavoriteMovies(completion: @escaping (MyListWorker.FavoriteMoviesResponse) -> Void) {
            fetchFavoriteMoviesCalled = true
            
            completion(.success([Seeds.Movies.movie1]))
        }
    }
    
    // MARK: - Tests
    
    func testFetchFavoriteMoviesShouldAskPresenterToFormatResults() {
        
        // Given
        let myListPresentationLogicSpy = MyListPresentationLogicSpy()
        sut.presenter = myListPresentationLogicSpy
        let myListWorkerSpy = MyListWorkerSpy()
        sut.worker = myListWorkerSpy
        
        // When
        sut.fetchFavoriteMovies()
        
        // Then
        XCTAssert(myListWorkerSpy.fetchFavoriteMoviesCalled, "FetchFavoriteMovies should ask MyListWorker to fetch movies")
        XCTAssert(myListPresentationLogicSpy.presentFavoriteMoviesCalled, "FetchFavoriteMovies should ask presenter to format movies results")
    }
}
