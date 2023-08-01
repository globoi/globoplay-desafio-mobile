//
//  HomeViewModelStubs.swift
//  DesafioGloboplayTests
//
//  Created by Thalles Araújo on 01/08/23.
//

import OHHTTPStubs
import Foundation
@testable import DesafioGloboplay

class HomeViewModelStubs{
    
    static func mockMovieResponse(){
        //isAbsoluteURLString("https://api.themoviedb.org/3/movie/now_playing")
        
        stub(condition: isAbsoluteURLString("https://api.themoviedb.org/3/movie/now_playing")) { request in
            
            let stubPath = OHPathForFile("Movies.json", HomeViewModelStubs.self)
            
            return HTTPStubsResponse(
                fileAtPath: stubPath!,
                statusCode: 200,
                headers:    stubsDefaultHeaders)
        }
    }
    
    static func mockTVShowsResponse(){
        
        stub(condition: isAbsoluteURLString("https://api.themoviedb.org/3/tv/airing_today")) { request in
            
            let stubPath = OHPathForFile("TVShows.json", HomeViewModelStubs.self)
            
            return HTTPStubsResponse(
                fileAtPath: stubPath!,
                statusCode: 200,
                headers:    stubsDefaultHeaders)
        }
    }
    
    
    
}
