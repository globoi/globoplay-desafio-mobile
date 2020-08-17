//
//  SearchViewControllerTests.swift
//  My MoviesTests
//
//  Created by Rafael Valer on 17/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import XCTest
@testable import My_Movies

class SearchViewControllerTests: XCTestCase {
    
    // MARK: - Subject under test
    
    var sut: SearchViewController!
    var window: UIWindow!
    
    // MARK: - Test lifecycle
    
    override func setUp() {
        super.setUp()
        window = UIWindow()
        setupSearchViewController()
    }
    
    override func tearDown() {
        window = nil
        super.tearDown()
    }

    // MARK: - Test setup
    
    func setupSearchViewController() {
        let bundle = Bundle.main
        let storyboard = UIStoryboard(name: "Main", bundle: bundle)
        sut = storyboard.instantiateViewController(identifier: SearchViewController.identifier) as? SearchViewController
    }
    
    func loadView() {
        window.addSubview(sut.view)
        RunLoop.current.run(until: Date())
    }
    
    // MARK: - Test doubles
    
    class SearchBusinessLogicSpy: SearchBusinessLogic {
        
        // MARK: - Method call expectations
        var fetchSearchMoviesCalled = false
        var discoverPopularMoviesCalled = false
        
        func fetchSearchMovies(_ queryText: String) {
            fetchSearchMoviesCalled = true
        }
        
        func discoverPopularMovies() {
            discoverPopularMoviesCalled = true
        }
    }
    
    class MoviesCollectionViewSpy: MoviesCollectionView {
        
        // MARK: - Method call expectations
        var setMoviesCalled = false
        
        // MARK: - Spied methods
        override func setMovies(_ displayableMovies: [DisplayableMovie]) {
            super.setMovies(displayableMovies)
            setMoviesCalled = true
        }
    }
    
    // MARK: - Tests
    
    func testShouldFetchSearchMoviesWhenUserTypes() {
        // Given
        let searchBusinessLogicSpy = SearchBusinessLogicSpy()
        sut.interactor = searchBusinessLogicSpy
        loadView()
        
        // When
        sut.searchBar.text = "Text"
        sut.reload()
        
        // Then
        XCTAssert(searchBusinessLogicSpy.fetchSearchMoviesCalled, "Should fetch movies after user types a text in the search bar")
    }
    
    func testShouldFetchPopularMoviesWhenViewDidLoad() {
        // Given
        let searchBusinessLogicSpy = SearchBusinessLogicSpy()
        sut.interactor = searchBusinessLogicSpy
        loadView()
        
        // When
        sut.viewDidLoad()
        
        // Then
        XCTAssert(searchBusinessLogicSpy.discoverPopularMoviesCalled, "Should fetch popular movies after view is loaded")
    }
    
    func testShouldDisplayFetchedSearchMovies() {
        // Given
        loadView()
        let moviesCollectionViewSpy = MoviesCollectionViewSpy(frame: .infinite)
        sut.moviesCollectionView = moviesCollectionViewSpy

        // When
        let displayedMovies = [SearchModels.FetchSearchMovies.ViewModel.DisplayedMovie(title: "Movie 1", posterPath: "path.jpg")]
        let viewModel = SearchModels.FetchSearchMovies.ViewModel(displayedMovies: displayedMovies)
        sut.displayFetchedSearchMovies(viewModel: viewModel)

        // Then
        XCTAssert(moviesCollectionViewSpy.setMoviesCalled, "Displaying fetched favorite movies should set movies to be displayed")
    }

    func testNumberOfItemsShouldEqualNumberOfMoviesToDisplay() {
        // Given
        loadView()
        let moviesCollectionViewSpy = MoviesCollectionViewSpy(frame: .infinite)
        sut.moviesCollectionView = moviesCollectionViewSpy

        // When
        let displayedMovies = [SearchModels.FetchSearchMovies.ViewModel.DisplayedMovie(title: "Movie 1", posterPath: "path.jpg")]
        let viewModel = SearchModels.FetchSearchMovies.ViewModel(displayedMovies: displayedMovies)
        sut.displayFetchedSearchMovies(viewModel: viewModel)
        
        let numberOfItems = sut.moviesCollectionView.collectionView(moviesCollectionViewSpy.collectionView, numberOfItemsInSection: 0)

        // Then
        XCTAssertEqual(numberOfItems, displayedMovies.count, "The number of collection view items should be equal to the number of movies to display")
    }
}
