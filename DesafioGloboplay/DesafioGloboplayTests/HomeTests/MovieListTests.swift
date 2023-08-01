//
//  MovieListTests.swift
//  DesafioGloboplayTests
//
//  Created by Thalles Araújo on 01/08/23.
//

import Foundation
@testable import DesafioGloboplay
import XCTest

final class MovieListTests: XCTestCase{
    
    
    func testMovieList(){
        
        HomeViewModelStubs.mockMovieResponse()
        
        let viewModel = HomeViewModel()
        
        let expectation = XCTestExpectation(description: "not nil")
        
        viewModel.completion = {
            let firstMovie = viewModel.movieList?.results?.first
            XCTAssertNotNil(firstMovie)
            expectation.fulfill()
            print("OK")
        }
        
        viewModel.getMovieList()
        wait(for: [expectation], timeout: 30.0)
        
        
        
        
    }
    
}
