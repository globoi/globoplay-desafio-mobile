//
//  SearchViewController.swift
//  My Movies
//
//  Created by Rafael Valer on 17/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import UIKit

protocol SearchDisplayLogic: class {
    func displayFetchedSearchMovies(viewModel: SearchModels.FetchSearchMovies.ViewModel)
    func displayErrorMessage(_ message: String)
}

class SearchViewController: UIViewController {
    
    static let identifier: String = "SearchViewController"
    
    var interactor: SearchBusinessLogic?
    var router: (NSObject & SearchRoutingLogic & SearchDataPassing)?
    
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
        let interactor = SearchInteractor()
        let presenter = SearchPresenter()
        let router = SearchRouter()
        viewController.interactor = interactor
        viewController.router = router
        interactor.presenter = presenter
        presenter.viewController = viewController
        router.viewController = viewController
        router.dataStore = interactor
    }

    // MARK: View lifecycle

    @IBOutlet weak var searchBar: UISearchBar!
    @IBOutlet weak var moviesCollectionView: MoviesCollectionView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        searchBar.delegate = self
        moviesCollectionView.delegate = self
    }
}

// MARK: - UISearchBarDelegate
extension SearchViewController: UISearchBarDelegate {
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        searchBar.resignFirstResponder()
        
        guard let text = searchBar.text else { return }
        interactor?.fetchSearchMovies(text)
    }
    
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        // to limit network activity, reload half a second after last key press.
        NSObject.cancelPreviousPerformRequests(withTarget: self, selector: #selector(self.reload), object: nil)
        self.perform(#selector(self.reload), with: nil, afterDelay: 0.4)
    }
    
    @objc func reload() {
        guard let text = searchBar.text else { return }
        interactor?.fetchSearchMovies(text)
    }
}

// MARK: - SearchDisplayLogic
extension SearchViewController: SearchDisplayLogic {
    func displayFetchedSearchMovies(viewModel: SearchModels.FetchSearchMovies.ViewModel) {
        
        moviesCollectionView.emptyResultMessage = "Nenhum filme foi encontrado."
        let displayedMovies = viewModel.displayedMovies
        moviesCollectionView.setMovies(displayedMovies)
    }
    
    func displayErrorMessage(_ message: String) {
        moviesCollectionView.emptyResultMessage = message
        moviesCollectionView.setMovies([])
    }
}

// MARK: - MoviesCollectionViewDelegate
extension SearchViewController: MoviesCollectionViewDelegate {
    func didSelectItem(at indexPath: IndexPath) {
        router?.navigateToMovieDetails(atIndexPath: indexPath)
    }
}
