//
//  MyListViewControllerTests.swift
//  My MoviesTests
//
//  Created by Rafael Valer on 15/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import XCTest
@testable import My_Movies

class MyListViewControllerTests: XCTestCase {
    
    // MARK: - Subject under test
    
    var sut: MyListViewController!
    var window: UIWindow!
    
    // MARK: - Test lifecycle
    
    override func setUp() {
        super.setUp()
        window = UIWindow()
        setupMyListViewController()
    }
    
    override func tearDown() {
        window = nil
        super.tearDown()
    }

    // MARK: - Test setup
    
    func setupMyListViewController() {
        let bundle = Bundle.main
        let storyboard = UIStoryboard(name: "Main", bundle: bundle)
        sut = storyboard.instantiateViewController(identifier: MyListViewController.identifier) as? MyListViewController
    }
    
    func loadView() {
        window.addSubview(sut.view)
        RunLoop.current.run(until: Date())
    }
    
    // MARK: - Test doubles
    
    class MyListBusinessLogicSpy: MyListBusinessLogic {
        
        // MARK: - Method call expectations
        var fetchFavoriteMoviesCalled = false
        
        func fetchFavoriteMovies() {
            fetchFavoriteMoviesCalled = true
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
    
    func testShouldFetchFavoriteMoviesWhenViewDidLoad() {
        // Given
        let myListBusinessLogicSpy = MyListBusinessLogicSpy()
        sut.interactor = myListBusinessLogicSpy
        loadView()
        
        // When
        sut.viewDidLoad()
        
        // Then
        XCTAssert(myListBusinessLogicSpy.fetchFavoriteMoviesCalled, "Should fetch favorite movies after view is loaded")
    }
    
    func testShouldDisplayFetchedFavoriteMovies() {
        // Given
        loadView()
        let moviesCollectionViewSpy = MoviesCollectionViewSpy(frame: .infinite)
        sut.moviesCollectionView = moviesCollectionViewSpy
        
        // When
        let displayedMovies = [MyListModels.FetchFavoriteMovies.ViewModel.DisplayedMovie(title: "Movie 1", posterPath: "path.jpg")]
        let viewModel = MyListModels.FetchFavoriteMovies.ViewModel(displayedMovies: displayedMovies)
        sut.displayFavoriteMovies(viewModel: viewModel)
        
        // Then
        XCTAssert(moviesCollectionViewSpy.setMoviesCalled, "Displaying fetched favorite movies should set movies to be displayed")
    }
    
    func testNumberOfItemsShouldEqualNumberOfMoviesToDisplay() {
        // Given
        loadView()
        let moviesCollectionViewSpy = MoviesCollectionViewSpy(frame: .infinite)
        sut.moviesCollectionView = moviesCollectionViewSpy
        
        // When
        let displayedMovies = [MyListModels.FetchFavoriteMovies.ViewModel.DisplayedMovie(title: "Movie 1", posterPath: "path1.jpg"), MyListModels.FetchFavoriteMovies.ViewModel.DisplayedMovie(title: "Movie 2", posterPath: "path2.jpg")]
        let viewModel = MyListModels.FetchFavoriteMovies.ViewModel(displayedMovies: displayedMovies)
        sut.displayFavoriteMovies(viewModel: viewModel)
        let numberOfItems = sut.moviesCollectionView.collectionView(moviesCollectionViewSpy.collectionView, numberOfItemsInSection: 0)
        
        // Then
        XCTAssertEqual(numberOfItems, displayedMovies.count, "The number of collection view items should be equal to the number of movies to display")
    }
}
