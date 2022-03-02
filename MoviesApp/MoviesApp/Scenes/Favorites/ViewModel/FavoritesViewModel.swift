//
//  FavoritesViewModel.swift
//  MoviesApp
//
//  Created by Gustavo Tiecker on 01/03/22.
//

import UIKit

protocol FavoritesBusinessLogic {
    func getFavoritesList(completion: @escaping ([Movie]) -> Void)
    func setFavoritesList(with movieList: [Movie])
    func getFavoritesCount() -> Int
    func downloadImage(at indexPath: IndexPath, completion: @escaping (UIImage?) -> Void)
    func goToDetailsScreen(of indexPath: IndexPath) -> UIViewController
}

final class FavoritesViewModel {
    
    private var model: [Movie]?
    private let networkService = NetworkService()
}

extension FavoritesViewModel: FavoritesBusinessLogic {
    
    func setFavoritesList(with movieList: [Movie]) {
        model = movieList
    }
    
    func getFavoritesList(completion: @escaping ([Movie]) -> Void) {
        PersistenceManager.retrieveFavorites { result in
            switch result {
            case .success(let favorites):
                completion(favorites)
                
            case.failure(let error):
                debugPrint(error)
            }
        }
    }
    
    func getFavoritesCount() -> Int {
        return model?.count ?? 0
    }

    func downloadImage(at indexPath: IndexPath, completion: @escaping (UIImage?) -> Void) {
        guard let movieList = model else { return }
        
        networkService.downloadImage(from: movieList[indexPath.row].posterPath, completion: completion)
    }
    
    // MARK: - Navigation
    func goToDetailsScreen(of indexPath: IndexPath) -> UIViewController {
        if let movieList = model {
            let viewModel = DetailsViewModel(model: movieList[indexPath.row])
            let viewController = DetailsViewController(viewModel: viewModel)
            viewModel.viewDelegate = viewController
            return viewController
        }
        return UIViewController()
    }
}
