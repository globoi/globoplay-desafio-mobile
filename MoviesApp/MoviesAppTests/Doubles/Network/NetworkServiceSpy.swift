//
//  NetworkServiceSpy.swift
//  MoviesAppTests
//
//  Created by Gustavo Tiecker on 02/03/22.
//

import XCTest
@testable import MoviesApp

final class NetworkServiceSpy: NetworkServiceProtocol {
    
    private(set) var requestCalled = false
    private(set) var completionPassed: ((Result<MovieList, APIError>) -> Void)?
    
    var resultToBeReturned: Result<MovieList, APIError>?
    
    func getMoviesList(id: Int, completion: @escaping ((Result<MovieList, APIError>) -> Void)) {
        requestCalled = true
        completionPassed = completion
        
        if let resultToBeReturned = resultToBeReturned {
            completion(resultToBeReturned)
        }
    }
    
    func downloadImage(from urlString: String, completion: @escaping (UIImage?) -> Void) {
        
    }
}
