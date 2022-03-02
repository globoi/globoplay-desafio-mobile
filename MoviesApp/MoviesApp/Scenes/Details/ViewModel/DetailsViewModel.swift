//
//  DetailsViewModel.swift
//  MoviesApp
//
//  Created by Gustavo Tiecker on 01/03/22.
//

import UIKit

protocol DetailsBusinessLogic {
    // MARK: - Getters and Setters
    func getMovie() -> Movie
    
    // MARK: - Network
    func downloadImage(of path: String, completion: @escaping (UIImage?) -> Void)
    
    // MARK: - Persistence
    func addToFavorites()
    func removeFromFavorites()
    func alreadyInFavorites(completion: @escaping (Bool) -> Void)
}

protocol DetailsViewDelegate: AnyObject {
    func didAddWithSuccess()
    func didRemoveWithSuccess()
}

final class DetailsViewModel {
    
    // MARK: - Properties
    private var model: Movie
    weak var viewDelegate: DetailsViewDelegate?
    private let networkService = NetworkService()
    
    // MARK: - Initializers
    init(model: Movie) {
        self.model = model
    }
}

extension DetailsViewModel: DetailsBusinessLogic {
    
    // MARK: - Getters and Setters
    func getMovie() -> Movie {
        return model
    }
    
    // MARK: - Network
    func downloadImage(of path: String, completion: @escaping (UIImage?) -> Void) {
        networkService.downloadImage(from: path, completion: completion)
    }
    
    // MARK: - Persistence
    func addToFavorites() {
        PersistenceManager.updateWith(favorite: model, actionType: .add) { error in
            guard let error = error else {
                self.viewDelegate?.didAddWithSuccess()
                return
            }
            debugPrint(error)
        }
    }
    
    func removeFromFavorites() {
        PersistenceManager.updateWith(favorite: model, actionType: .remove) { error in
            guard let error = error else {
                self.viewDelegate?.didRemoveWithSuccess()
                return
            }
            debugPrint(error)
        }
    }
    
    func alreadyInFavorites(completion: @escaping (Bool) -> Void) {
        PersistenceManager.retrieveFavorites { [weak self] result in
            guard let self = self else { return }
            switch result {
            case .success(let movieList):
                if (movieList.first(where: { movie in
                    return movie.id == self.model.id
                })) != nil {
                    completion(true)
                } else {
                    completion(false)
                }
            case .failure(_):
                debugPrint("Unable to retrieve favorites")
            }
        }
    }
}
