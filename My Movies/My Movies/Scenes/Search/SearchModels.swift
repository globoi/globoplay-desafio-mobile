//
//  SearchModels.swift
//  My Movies
//
//  Created by Rafael Valer on 17/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

struct SearchModels {
    
    // MARK: - Use Cases
    
    enum FetchSearchMovies {
        struct Request {
            let queryText: String
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
}
