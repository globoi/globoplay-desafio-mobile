//
//  HomeModels.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

struct HomeModels {
    
    // MARK: - Use Cases
    
    enum FetchMovies {
        struct Request {
            let page: Int
            let genre: Int
        }
        
        struct Response: Decodable {
            var genre: Int
            var movies: [Movie]
        }
        
        struct ViewModel {
            struct DisplayedMovie {
                var title: String
                var posterPath: String?
            }
            var displayedStatements: [DisplayedMovie]
        }
    }
}
