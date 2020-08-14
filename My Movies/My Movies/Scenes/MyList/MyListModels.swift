//
//  MyListModels.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

struct MyListModels {
    
    // MARK: - Use Cases
    
    enum FetchFavoriteMovies {
        
        struct Response {
            var favoriteMovies: [Movie]
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
