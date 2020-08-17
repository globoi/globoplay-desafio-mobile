//
//  MovieDetailsViewControllerTests.swift
//  My MoviesTests
//
//  Created by Rafael Valer on 16/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import XCTest
@testable import My_Movies

class MovieDetailsViewControllerTests: XCTestCase {
    
    // MARK: - Subject under test
    
    var sut: MovieDetailsViewController!
    var window: UIWindow!
    
    // MARK: - Test lifecycle
    
    override func setUp() {
        super.setUp()
        window = UIWindow()
        setupMovieDetailsViewController()
    }
    
    override func tearDown() {
        window = nil
        super.tearDown()
    }

    // MARK: - Test setup
    
    func setupMovieDetailsViewController() {
        let bundle = Bundle.main
        let storyboard = UIStoryboard(name: "Main", bundle: bundle)
        sut = storyboard.instantiateViewController(identifier: MovieDetailsViewController.identifier) { (coder) -> MovieDetailsViewController? in
            return MovieDetailsViewController(coder: coder, movieId: 1)
        }
    }
    
    func loadView() {
        window.addSubview(sut.view)
        RunLoop.current.run(until: Date())
    }

    // MARK: - Test doubles
    
    class MovieDetailsBusinessLogicSpy: MovieDetailsBusinessLogic {
        
        // MARK: - Method call expectations
        var fetchMovieDetailsCalled = false
        var fetchMovieRecommendationsCalled = false
        var addOrRemoveMovieFromFavoritesCalled = false
        var checkIfMovieIsFavoriteCalled = false
        var fetchMovieTrailerCalled = false
        
        func fetchMovieDetails(_ movieId: Int) {
            fetchMovieDetailsCalled = true
        }
        
        func fetchMovieRecommendations(_ movieId: Int) {
            fetchMovieRecommendationsCalled = true
        }
        
        func addOrRemoveMovieFromFavorites(_ movieId: Int) {
            addOrRemoveMovieFromFavoritesCalled = true
        }
        
        func checkIfMovieIsFavorite(_ movieId: Int) {
            checkIfMovieIsFavoriteCalled = true
        }
        
        func fetchMovieTrailer(_ movieId: Int) {
            fetchMovieTrailerCalled = true
        }
    }
    
    class MoviesCollectionViewSpy: MoviesCollectionView {
        
        // MARK: Method call expectations
        var setMoviesCalled = false
        
        // MARK: Spied methods
        override func setMovies(_ displayableMovies: [DisplayableMovie]) {
            super.setMovies(displayableMovies)
            setMoviesCalled = true
        }
    }
    
    class MovieDetailsViewSpy: MovieDetailsView {
            
        // MARK: Method call expectations
        var setMovieDetailsCalled = false
        
        override func setMovieDetails(_ displayableMovie: DisplayableMovieDetails) {
            super.setMovieDetails(displayableMovie)
            setMovieDetailsCalled = true
        }
    }
    
    // MARK: - Tests
    
    func testShouldFetchMoviesDetailsWhenViewDidLoad() {
        // Given
        let movieDetailsBusinessLogicSpy = MovieDetailsBusinessLogicSpy()
        sut.interactor = movieDetailsBusinessLogicSpy
        loadView()
        
        // When
        sut.viewDidLoad()
        
        // Then
        XCTAssert(movieDetailsBusinessLogicSpy.fetchMovieDetailsCalled, "Should fetch movie details after view is loaded")
    }
    
    func testShouldFetchMovieRecommendationsWhenViewDidLoad() {
        // Given
        let movieDetailsBusinessLogicSpy = MovieDetailsBusinessLogicSpy()
        sut.interactor = movieDetailsBusinessLogicSpy
        loadView()
        
        // When
        sut.viewDidLoad()
        
        // Then
        XCTAssert(movieDetailsBusinessLogicSpy.fetchMovieRecommendationsCalled, "Should fetch movie recommendations after view is loaded")
    }
    
    func testShouldCheckIfMovieIsOnFavoritesWhenViewDidLoad() {
        // Given
        let movieDetailsBusinessLogicSpy = MovieDetailsBusinessLogicSpy()
        sut.interactor = movieDetailsBusinessLogicSpy
        loadView()
        
        // When
        sut.viewDidLoad()
        
        // Then
        XCTAssert(movieDetailsBusinessLogicSpy.checkIfMovieIsFavoriteCalled, "Should check if movie is on favorites after view is loaded")
    }
    
    func testShouldDisplayFetchedRecommendedMovies() {
        // Given
        loadView()
        let moviesCollectionViewSpy = MoviesCollectionViewSpy(frame: .infinite)
        sut.moviesCollectionView = moviesCollectionViewSpy
        
        // When
        let displayedMovies = [MovieDetailsModels.FetchMovieRecommendations.ViewModel.DisplayedMovie(title: "Movie 1", posterPath: "path.jpg")]
        let viewModel = MovieDetailsModels.FetchMovieRecommendations.ViewModel(displayedMovies: displayedMovies)
        sut.displayFetchedMovieRecommendations(viewModel: viewModel)
        
        // Then
        XCTAssert(moviesCollectionViewSpy.setMoviesCalled, "Displaying fetched recommended movies should set movies to be displayed")
    }
    
    func testShouldDisplayFetchedMovieDetails() {
        // Given
        loadView()
        let movieDetailsViewSpy = MovieDetailsViewSpy(frame: .infinite)
        sut.movieDetailsView = movieDetailsViewSpy
        
        // When
        let displayableMovie = MovieDetailsModels.FetchMovieDetails.ViewModel.DisplayedMovie(title: "Movie 1", posterPath: "poster ", backdropPath: nil, overview: nil, type: nil, releaseDate: nil, genres: nil, originalTitle: nil, score: nil, productionCountries: nil)
        let viewModel = MovieDetailsModels.FetchMovieDetails.ViewModel(displayedMovie: displayableMovie)
        sut.displayFetchedMovieDetails(viewModel: viewModel)
        
        // Then
        XCTAssert(movieDetailsViewSpy.setMovieDetailsCalled, "Displaying fetched movie details should set movie details to be displayed")
    }
    
    func testShouldFetchMovieTrailerWhenWatchButtonPressed() {
        // Given
        let movieDetailsBusinessLogicSpy = MovieDetailsBusinessLogicSpy()
        sut.interactor = movieDetailsBusinessLogicSpy
        loadView()
        
        // When
        sut.watchButtonTouched("test")
        
        // Then
        XCTAssert(movieDetailsBusinessLogicSpy.fetchMovieTrailerCalled, "Should fetch movie trailer when watch button pressed")
    }
}
