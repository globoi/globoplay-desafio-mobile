//
//  MyListViewController.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import UIKit

protocol MyListDisplayLogic: class {
    func displayFavoriteMovies(viewModel: MyListModels.FetchFavoriteMovies.ViewModel)
}

class MyListViewController: UIViewController {
    
    static let identifier: String = "MyListViewController"
    
    var interactor: MyListBusinessLogic?
    var router: (NSObject & MyListRoutingLogic & MyListDataPassing)?
    
    // MARK: Object lifecycle
    
    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
        setup()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setup()
    }
    
    // MARK: Setup
    
    private func setup() {
        let viewController = self
        let interactor = MyListInteractor()
        let presenter = MyListPresenter()
        let router = MyListRouter()
        viewController.interactor = interactor
        viewController.router = router
        interactor.presenter = presenter
        presenter.viewController = viewController
        router.viewController = viewController
        router.dataStore = interactor
    }
    
    // MARK: View lifecycle

    @IBOutlet weak var moviesCollectionView: MoviesCollectionView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        fetchFavoriteMovies()
        
        moviesCollectionView.delegate = self
        moviesCollectionView.emptyResultMessage = "Você ainda não possui nenhum filme\n na sua lista de favoritos."
        NotificationCenter.default.addObserver(self, selector: #selector(fetchFavoriteMovies), name: .FavoriteMoviesUpdated, object: nil)
    }
    
    @objc private func fetchFavoriteMovies() {
        interactor?.fetchFavoriteMovies()
    }
}

// MARK: - MyListDisplayLogic
extension MyListViewController: MyListDisplayLogic {
    
    func displayFavoriteMovies(viewModel: MyListModels.FetchFavoriteMovies.ViewModel) {
        let displayedMovies = viewModel.displayedMovies
        moviesCollectionView.setMovies(displayedMovies)
    }
}

// MARK: - MoviesCollectionViewDelegate
extension MyListViewController: MoviesCollectionViewDelegate {
    func didSelectItem(at indexPath: IndexPath) {
        router?.navigateToMovieDetails(atIndexPath: indexPath)
    }
}
