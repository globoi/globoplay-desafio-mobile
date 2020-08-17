//
//  HomeViewControllerTests.swift
//  My MoviesTests
//
//  Created by Rafael Valer on 15/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import XCTest
@testable import My_Movies

class HomeViewControllerTests: XCTestCase {
    
    // MARK: - Subject under test
    
    var sut: HomeViewController!
    var window: UIWindow!
    
    // MARK: - Test lifecycle
    
    override func setUp() {
        super.setUp()
        window = UIWindow()
        setupHomeViewController()
    }
    
    override func tearDown() {
        window = nil
        super.tearDown()
    }

    // MARK: - Test setup
    
    func setupHomeViewController() {
        let bundle = Bundle.main
        let storyboard = UIStoryboard(name: "Main", bundle: bundle)
        sut = storyboard.instantiateViewController(identifier: HomeViewController.identifier) as? HomeViewController
    }
    
    func loadView() {
        window.addSubview(sut.view)
        RunLoop.current.run(until: Date())
    }
    
    // MARK: - Test doubles
    
    class HomeBusinessLogicSpy: HomeBusinessLogic {
        
        // MARK: - Method call expectations
        var fetchMoviesCalled = false
        
        func fetchMovies(withPage page: Int, genre: Int) {
            fetchMoviesCalled = true
        }
    }
    
    class TableViewSpy: UITableView {
        
        // MARK: - Method call expectations
        var reloadDataCalled = false
        
        // MARK: - Spied methods
        override func reloadData() {
            reloadDataCalled = true
        }
    }
    
    // MARK: - Tests
    
    func testShouldFetchMoviesWhenViewDidLoad() {
        // Given
        let homeBusinessLogicSpy = HomeBusinessLogicSpy()
        sut.interactor = homeBusinessLogicSpy
        loadView()
        
        // When
        sut.viewDidLoad()
        
        // Then
        XCTAssert(homeBusinessLogicSpy.fetchMoviesCalled, "Should fetch movies after view is loaded")
    }
    
    func testShouldDisplayFetchedMovies() {
        // Given
        let tableViewSpy = TableViewSpy()
        sut.tableView = tableViewSpy
        
        // When
        let displayedMovies = [HomeModels.FetchMovies.ViewModel.DisplayedMovie(title: "Movie 1", posterPath: "path.jpg")]
        
        let viewModel = HomeModels.FetchMovies.ViewModel(displayedMovies: displayedMovies)
        sut.displayFetchedMovies(viewModel: viewModel, forGenre: 1)
        
        // Then
        let asyncExpectation = expectation(description: "Async block executed")
        DispatchQueue.main.async {
            XCTAssert(tableViewSpy.reloadDataCalled, "Displaying fetched movies should reload table view")
            asyncExpectation.fulfill()
        }
        waitForExpectations(timeout: 1, handler: nil)
    }
}
