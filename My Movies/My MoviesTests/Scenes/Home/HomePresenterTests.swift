//
//  HomePresenterTests.swift
//  My MoviesTests
//
//  Created by Rafael Valer on 15/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import XCTest
@testable import My_Movies

class HomePresenterTests: XCTestCase {
    
    // MARK: - Subject under test
    var sut: HomePresenter!

    // MARK: - Test lifecycle
    override func setUp() {
        super.setUp()
        setupHomePresenter()
    }
    
    override func tearDown() {
        super.tearDown()
    }

    // MARK: - Test setup
    func setupHomePresenter() {
        sut = HomePresenter()
    }
    
    // MARK: - Test doubles
    class HomeDisplayLogicSpy: HomeDisplayLogic {
        
        // MARK: Method call expectations
        var displayFetchedMoviesCalled = false
        var displayErrorCalled = false
        
        // MARK: Argument expectations
        var fetchedMoviesViewModel: HomeModels.FetchMovies.ViewModel!
        var errorMessage: String!
        
        // MARK: Spied methods
        func displayFetchedMovies(viewModel: HomeModels.FetchMovies.ViewModel,
                                  forGenre genre: Int) {
            displayFetchedMoviesCalled = true
            fetchedMoviesViewModel = viewModel
        }
        
        func displayError(withMessage message: String) {
            displayErrorCalled = true
            errorMessage = message
        }
    }
    
    // MARK: - Tests
    
    func testPresentFetchedMovies() {
        
        // Given
        let homeDisplayLogicSpy = HomeDisplayLogicSpy()
        sut.viewController = homeDisplayLogicSpy
        
        // When
        let movie = Seeds.Movies.movie1
        let genre = 3
        let response = HomeModels.FetchMovies.Response(genre: genre, movies: [movie])
        sut.presentFetchedMovies(response: response)
        
        // Then
        let displayedMovies = homeDisplayLogicSpy.fetchedMoviesViewModel.displayedMovies
        for displayedMovie in displayedMovies {
            XCTAssertEqual(displayedMovie.title, "Movie 1")
            XCTAssertEqual(displayedMovie.posterPath,
                           Constants.baseImagesURL + "w185/posterPath.jpg")
        }
        
        XCTAssert(homeDisplayLogicSpy.displayFetchedMoviesCalled, "Presenting fetched movies should ask view controller to display them")
    }
    
    func testPresentErrorMessage() {
        
        // Given
        let homeDisplayLogicSpy = HomeDisplayLogicSpy()
        sut.viewController = homeDisplayLogicSpy
        
        // When
        let error = ApplicationError.commonError
        sut.presentError(error)
        
        // Then
        XCTAssertEqual(homeDisplayLogicSpy.errorMessage, "Houve algum problema ao conectar no My Movies.\nTente novamente mais tarde.")
        XCTAssert(homeDisplayLogicSpy.displayErrorCalled, "Presenting error should ask view controller to display message")
    }
}
