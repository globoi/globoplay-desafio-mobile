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
    
    // MARK: - FetchMovieDetails
    enum FetchMovieDetails {
        struct Request {
            let movieId: String
        }
        struct Response: Decodable {
            var movie: Movie
        }
        struct ViewModel {
            struct DisplayedMovie: DisplayableMovieDetails {
                var title: String
                var posterPath: String?
                var backdropPath: String?
                var overview: String?
                var type: String?
                var releaseDate: String?
                var genres: String?
                var originalTitle: String?
                var score: String?
                var productionCountries: String?
            }
            var displayedMovie: DisplayedMovie
        }
    }
    
    // MARK: - FetchMovieRecommendations
    enum FetchMovieRecommendations {
        struct Request {
            let movieId: String
        }
        struct Response {
            var movies: [Movie]
        }
        struct ViewModel {
            struct DisplayedMovie: DisplayableMovie {
                var title: String
                var posterPath: String?
            }
            var displayedMovies: [DisplayedMovie]
        }
    }
    
    // MARK: - FetchMovieTrailer
    enum FetchMovieTrailer {
        struct Request {
            let movieId: String
        }
        struct Response {
            var trailer: Video
        }
    }
}
