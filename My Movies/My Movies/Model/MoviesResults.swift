//
//  MoviesResults.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

struct MoviesResults {
    let movies: [Movie]
}

extension MoviesResults: Decodable {
    enum CodingKeys: String, CodingKey {
        case movies = "results"
    }
    
    init?(data: Data) {
        guard let movies = try? JSONDecoder.defaultDecoder.decode(MoviesResults.self, from: data) else {
            return nil
        }
        self = movies
    }
}
