//
//  HomeViewModel.swift
//  MoviesApp
//
//  Created by Gustavo Tiecker on 22/02/22.
//

import Foundation
import UIKit

protocol HomeBusinessLogic {
    // MARK: - CollectionView Configuration
    func getNumberOfSections() -> Int
    func getNumberOfRowsIn(section: Int) -> Int
    
    // MARK: - Service
    func fetchTrendMovieList(completion: @escaping () -> Void)
    func fetchRecomendedMovieList(completion: @escaping () -> Void)
    func downloadImage(at indexPath: IndexPath, completion: @escaping (UIImage?) -> Void)
    
    // MARK: - Navigation
    func goToDetailsScreen(of indexPath: IndexPath) -> UIViewController
}

protocol HomeViewModelViewDelegate: AnyObject {
    func didFinishWithError(_ error: APIError)
}

final class HomeViewModel {
    
    enum Sections {
        case trend
        case recomended
    }
    
    // MARK: - Properties
    private let networkService = NetworkService()
    weak var viewDelegate: HomeViewModelViewDelegate?
    
    private var trendMovieList: [Movie]?
    private var recomendedMovieList: [Movie]?
    private var sections: [Sections] = [.trend]
    
    // MARK: - Public Methods
    func requestMovieList(id: Int, completion: @escaping ((MovieList) -> Void)) {
        networkService.getMoviesList(id: id) { result in
            switch result {
            case.success(let movies):
                completion(movies)
            case .failure(let error):
                self.viewDelegate?.didFinishWithError(error)
            }
        }
    }
}

extension HomeViewModel: HomeBusinessLogic {
    
    // MARK: - CollectionView Configuration
    func getNumberOfSections() -> Int {
        return sections.count
    }
    
    func getNumberOfRowsIn(section: Int) -> Int {
        if section == 0 {
            return trendMovieList?.count ?? 0
        }
        return recomendedMovieList?.count ?? 0
    }
    
    // MARK: - Service
    func fetchTrendMovieList(completion: @escaping () -> Void) {
        requestMovieList(id: 1) { movies in
            self.trendMovieList = movies.items
            completion()
        }
    }
    
    func fetchRecomendedMovieList(completion: @escaping () -> Void) {
        requestMovieList(id: 2) { movies in
            self.recomendedMovieList = movies.items
            completion()
        }
    }
    
    func downloadImage(at indexPath: IndexPath, completion: @escaping (UIImage?) -> Void) {
        guard let movieList = trendMovieList else { return }
        
        networkService.downloadImage(from: movieList[indexPath.row].posterPath, completion: completion)
    }
    
    // MARK: - Navigation
    func goToDetailsScreen(of indexPath: IndexPath) -> UIViewController {
        if let movieList = trendMovieList {
            let viewModel = DetailsViewModel(model: movieList[indexPath.row])
            let viewController = DetailsViewController(viewModel: viewModel)
            viewModel.viewDelegate = viewController
            return viewController
        }
        return UIViewController()
    }
}
