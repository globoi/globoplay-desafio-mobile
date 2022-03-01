//
//  DetailsViewModel.swift
//  MoviesApp
//
//  Created by Gustavo Tiecker on 01/03/22.
//

import UIKit

protocol DetailsBusinessLogic {
    func getMovie() -> Movie
    func downloadImage(of path: String, completion: @escaping (UIImage?) -> Void)
}

final class DetailsViewModel {
    
    private var model: Movie
    private let networkService = NetworkService()
    
    init(model: Movie) {
        self.model = model
    }
}

extension DetailsViewModel: DetailsBusinessLogic {
    func getMovie() -> Movie {
        return model
    }
    
    func downloadImage(of path: String, completion: @escaping (UIImage?) -> Void) {
        networkService.downloadImage(from: path, completion: completion)
    }
}
