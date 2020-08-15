//
//  MyListPresenterTests.swift
//  My MoviesTests
//
//  Created by Rafael Valer on 15/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import XCTest
@testable import My_Movies

class MyListPresenterTests: XCTestCase {
    
    // MARK: - Subject under test
    var sut: MyListPresenter!

    // MARK: - Test lifecycle
    override func setUp() {
        super.setUp()
        setupMyListPresenter()
    }
    
    override func tearDown() {
        super.tearDown()
    }

    // MARK: - Test setup
    func setupMyListPresenter() {
        sut = MyListPresenter()
    }
    
    // MARK: - Test doubles
    class MyListDisplayLogicSpy: MyListDisplayLogic {
        
        // MARK: Method call expectations
        var displayFavoriteMoviesCalled = false
        var displayMessageCalled = false
        
        // MARK: Argument expectations
        var fetchedFavoriteMoviesViewModel: MyListModels.FetchFavoriteMovies.ViewModel!
        var displayedMessage: String!
        
        // MARK: Spied methods
        func displayFavoriteMovies(viewModel: MyListModels.FetchFavoriteMovies.ViewModel) {
            displayFavoriteMoviesCalled = true
            fetchedFavoriteMoviesViewModel = viewModel
        }
        
        func displayMessage(_ message: String) {
            displayMessageCalled = true
            displayedMessage = message
        }
    }
    
    // MARK: - Tests
    
    func testPresentFetchedFavoriteMovies() {
        
        // Given
        let myListDisplayLogicSpy = MyListDisplayLogicSpy()
        sut.viewController = myListDisplayLogicSpy
        
        // When
        let movie = Seeds.Movies.movie1
        let response = MyListModels.FetchFavoriteMovies.Response(favoriteMovies: [movie])
        sut.presentFavoriteMovies(response: response)
        
        // Then
        let displayedMovies = myListDisplayLogicSpy.fetchedFavoriteMoviesViewModel.displayedMovies
        for displayedMovie in displayedMovies {
            XCTAssertEqual(displayedMovie.title, "Movie 1")
            XCTAssertEqual(displayedMovie.posterPath,
                           Constants.baseImagesURL + "w185/posterPath.jpg")
        }
        
        XCTAssert(myListDisplayLogicSpy.displayFavoriteMoviesCalled, "Presenting fetched favorite movies should ask view controller to display them")
    }

    func testPresentEmptyMessage() {

        // Given
        let myListDisplayLogicSpy = MyListDisplayLogicSpy()
        sut.viewController = myListDisplayLogicSpy

        // When
        sut.presentEmptyMessage()

        // Then
        XCTAssertEqual(myListDisplayLogicSpy.displayedMessage, "Você ainda não possui nenhum filme\n na sua lista de favoritos.")
        XCTAssert(myListDisplayLogicSpy.displayMessageCalled, "Presenting empty message should ask view controller to display it")
    }
}
