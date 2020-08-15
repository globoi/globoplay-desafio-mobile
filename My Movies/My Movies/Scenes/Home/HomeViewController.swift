//
//  HomeViewController.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import UIKit

protocol HomeDisplayLogic: class {
    func displayFetchedMovies(viewModel: HomeModels.FetchMovies.ViewModel, forGenre genre: Int)
    func displayError(withMessage message: String)
}

class HomeViewController: UIViewController {
    
    static let identifier: String = "HomeViewController"
    
    var interactor: HomeBusinessLogic?
    var router: (NSObject & HomeRoutingLogic & HomeDataPassing)?
    
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
        let interactor = HomeInteractor()
        let presenter = HomePresenter()
        let router = HomeRouter()
        viewController.interactor = interactor
        viewController.router = router
        interactor.presenter = presenter
        presenter.viewController = viewController
        router.viewController = viewController
        router.dataStore = interactor
    }

    // MARK: View lifecycle

    @IBOutlet weak var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setupTable()
        fetchMovies()
    }
    
    private func setupTable() {
        tableView.dataSource = self
        tableView.delegate = self
        tableView.register(UINib(nibName: HomeTableViewCell.identifier, bundle: nil), forCellReuseIdentifier: HomeTableViewCell.identifier)
    }
    
    // MARK: - Fetch Data
    
    var displayedMovies: [Int : [HomeModels.FetchMovies.ViewModel.DisplayedMovie]] = [:]
    
    private func fetchMovies() {
        GenresList.allCases.forEach { (genre) in
            interactor?.fetchMovies(withPage: 1, genre: genre.getId())
        }
    }
}

// MARK: - UITableView
extension HomeViewController: UITableViewDataSource, UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        guard let cell = tableView.dequeueReusableCell(withIdentifier: HomeTableViewCell.identifier, for: indexPath) as? HomeTableViewCell else {
            return UITableViewCell()
        }
        
        let genreId = GenresList.allCases[indexPath.section].getId()
        guard let displayedMovies = displayedMovies[genreId] else {
            return UITableViewCell()
        }

        cell.render(displayedMovies: displayedMovies, withGenre: GenresList.allCases[indexPath.section])
        cell.delegate = self
        return cell
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        let genreId = GenresList.allCases[section].getId()
        
        guard let displayedMovies = displayedMovies[genreId] else { return 0 }
        return displayedMovies.count > 0 ? 1 : 0
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return GenresList.allCases.count
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return HomeTableViewCell.height
    }
}

// MARK: - HomeDisplayLogic
extension HomeViewController: HomeDisplayLogic {

    func displayError(withMessage message: String) {
        
        DispatchQueue.main.async { [weak self] in
            let errorMessageView = CustomMessageView(message: message, buttonTitle: "Tentar Novamente")
            errorMessageView.delegate = self
            self?.tableView.backgroundView = errorMessageView
        }
    }
    
    func displayFetchedMovies(viewModel: HomeModels.FetchMovies.ViewModel, forGenre genre: Int) {
        
        let displayedMovies = viewModel.displayedMovies
        DispatchQueue.main.async { [weak self] in
            self?.displayedMovies[genre] = displayedMovies
            self?.tableView.reloadData()
            self?.tableView.backgroundView = nil
        }
    }
}

// MARK: - HomeTableViewCellDelegate
extension HomeViewController: HomeTableViewCellDelegate {
    
    func didSelectMovieAtIndex(_ indexPath: IndexPath, forGenre genre: Int) {
        router?.navigateToMovieDetails(atIndexPath: indexPath, withGenre: genre)
    }
}

extension HomeViewController: CustomMessageViewDelegate {
    func tryAgainTouched() {
        fetchMovies()
    }
}
