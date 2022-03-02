//
//  HomeViewModelTests.swift
//  MoviesAppTests
//
//  Created by Gustavo Tiecker on 01/03/22.
//

import XCTest
@testable import MoviesApp

class HomeViewModelTests: XCTestCase {
    
    private var sut: HomeViewModel!
    private var networkServiceSpy: NetworkServiceSpy!
    
    override func setUp() {
        super.setUp()
        sut = HomeViewModel()
        networkServiceSpy = NetworkServiceSpy()
        sut.networkService = networkServiceSpy
    }
    
    func test_getNumberOfSections_shouldReturnSectionsCount() {
        XCTAssertEqual(sut.getNumberOfSections(), HomeViewModel.Sections.allCases.count)
    }
    
    func test_getNumberOfRowsInSection_shouldReturnTrendMovieListCount() {
        // Given
        networkServiceSpy.resultToBeReturned = Result<MovieList, APIError>.success(.fixture())
        
        // When
        sut.fetchTrendMovieList {}
        
        // Then
        XCTAssertEqual(self.sut.getNumberOfRowsIn(section: 0), 1)
    }
    
    func test_fetchTrenMovieList_GivenSucessResponse_ShoulSucceed() {
        // Given
        networkServiceSpy.resultToBeReturned = Result<MovieList, APIError>.success(.fixture())
        
        // When
        sut.fetchTrendMovieList {}
        
        // Then
        guard case .success(_) = networkServiceSpy.resultToBeReturned else {
            return XCTFail("Should be success")
        }
        
        XCTAssertTrue(networkServiceSpy.requestCalled)
        XCTAssertNotNil(networkServiceSpy.completionPassed)
    }
    
    func test_fetchTrenMovieList_GivenFailureResponse_ShoulFail() throws {
        // Given
        let expectedError = APIError.unableToComplete
        networkServiceSpy.resultToBeReturned = Result<MovieList, APIError>.failure(expectedError)
        
        // When
        sut.fetchTrendMovieList {}
        
        // Then
        XCTAssertTrue(networkServiceSpy.requestCalled)
        XCTAssertNotNil(networkServiceSpy.completionPassed)
        
        guard case .failure(let error) = networkServiceSpy.resultToBeReturned else {
            return XCTFail("Should be failure")
        }
        
        let receivedError = try XCTUnwrap(error)
        XCTAssertNotNil(receivedError)
        XCTAssertEqual(receivedError, .unableToComplete)
    }
}
