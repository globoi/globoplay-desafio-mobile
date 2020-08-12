//
//  MovieDetailsModels.swift
//  My Movies
//
//  Created by Rafael Valer on 11/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

struct MovieDetailsModels {
    
    // MARK: - Use Cases
    
    enum FetchMovieDetails {
        struct Request {
            let movieId: String
        }
        
        struct Response: Decodable {
            var movie: Movie
        }
        
        struct ViewModel {
            struct DisplayedMovie {
                var title: String
                var posterPath: String?
                var backdropPath: String?
                var overview: String?
                var type: String?
            }
            var displayedMovie: DisplayedMovie
        }
    }
}
