//
//  SearchPresenterTests.swift
//  My MoviesTests
//
//  Created by Rafael Valer on 17/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import XCTest
@testable import My_Movies

class SearchPresenterTests: XCTestCase {
    
    // MARK: - Subject under test
    var sut: SearchPresenter!

    // MARK: - Test lifecycle
    override func setUp() {
        super.setUp()
        setupSearchPresenter()
    }
    
    override func tearDown() {
        super.tearDown()
    }

    // MARK: - Test setup
    func setupSearchPresenter() {
        sut = SearchPresenter()
    }
    
    // MARK: - Test doubles
    class SearchDisplayLogicSpy: SearchDisplayLogic {

        // MARK: Method call expectations
        var displayFetchedSearchMoviesCalled = false
        var displayErrorMessageCalled = false
        
        // MARK: Argument expectations
        var fetchedSearchMoviesViewModel: SearchModels.FetchSearchMovies.ViewModel!
        var errorMessage: String!
        
        // MARK: Spied methods
        func displayFetchedSearchMovies(viewModel: SearchModels.FetchSearchMovies.ViewModel) {
            displayFetchedSearchMoviesCalled = true
            fetchedSearchMoviesViewModel = viewModel
        }
        
        func displayErrorMessage(_ message: String) {
            displayErrorMessageCalled = true
            errorMessage = message
        }
    }

    // MARK: - Tests
    
    func testPresentFetchedSearchedMovies() {
        
        // Given
        let searchDisplayLogicSpy = SearchDisplayLogicSpy()
        sut.viewController = searchDisplayLogicSpy
        
        // When
        let movie = Seeds.Movies.movie1
        let response = SearchModels.FetchSearchMovies.Response(movies: [movie])
        sut.presentFetchedMovies(response: response)
        
        // Then
        let displayedMovies = searchDisplayLogicSpy.fetchedSearchMoviesViewModel.displayedMovies
        for displayedMovie in displayedMovies {
            XCTAssertEqual(displayedMovie.title, "Movie 1")
            XCTAssertEqual(displayedMovie.posterPath,
                           Constants.baseImagesURL + "w185/posterPath.jpg")
        }
        
        XCTAssert(searchDisplayLogicSpy.displayFetchedSearchMoviesCalled, "Presenting fetched searched movies should ask view controller to display them")
    }
    
    func testPresentErrorMessage() {
        
        // Given
        let searchDisplayLogicSpy = SearchDisplayLogicSpy()
        sut.viewController = searchDisplayLogicSpy
        
        // When
        let error = ApplicationError.commonError
        sut.presentError(error)
        
        // Then
        XCTAssertEqual(searchDisplayLogicSpy.errorMessage, "Desculpe, parece que ocorreu um erro. \nTente novamente mais tarde.")
        XCTAssert(searchDisplayLogicSpy.displayErrorMessageCalled, "Presenting error should ask view controller to display message")
    }
}
