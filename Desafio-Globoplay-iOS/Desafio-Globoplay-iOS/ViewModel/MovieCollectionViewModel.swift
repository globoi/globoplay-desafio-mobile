//
//  MovieCollectionViewModel.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 23/02/22.
//

import Foundation

class MovieCollectionViewModel {
    
    // MARK: - Properties
    
    var movies = [Movie]()
    
    // MARK: - Lifecycle
    
    // MARK: - Helper Methods
    
    func formatImageUrl(for movies: [Movie]) {
        for movie in movies {
            if let posterPath = movie.posterPath {
                movie.urlImage = Constants.ProductionServer.IMAGE_URL + posterPath
            }
        }
    }
    
}
