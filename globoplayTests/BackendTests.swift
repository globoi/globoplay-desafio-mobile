//
//  BackendTests.swift
//  globoplayTests
//
//  Created by Marcos Curvello on 04/05/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import XCTest
@testable
import globoplay

class BackendTests: XCTestCase {

    func testImageRequestUrlInit_shouldReturnEqual() {
        let stringURL = "https://image.tmdb.org/t/p/w200/4E4TTsCXVFyhBtYu9fKy0gIT3Ih.jpg"
        let url = URL(string: stringURL)!
        
        let request = Request(.image("w200", "/4E4TTsCXVFyhBtYu9fKy0gIT3Ih.jpg"))
        XCTAssertEqual(request.imageUrl!, url)
    }
    
    func testMovieDetailRequestUrlInit_shouldReturnEqual() {
        let stringURL = "https://api.themoviedb.org/3/movie/181812"
        let url = URL(string: stringURL)!
        
        let request = Request(.detail(.movie, 181812))
        XCTAssertEqual(request.url!, url)
    }
}
