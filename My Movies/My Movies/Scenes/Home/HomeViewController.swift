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
        
        tableView.dataSource = self
        tableView.delegate = self
        
        fetchMovies()
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
        let cell = UITableViewCell()
        let movies = displayedMovies[GenresList.allCases[indexPath.section].getId()]!
        cell.textLabel?.text = movies[indexPath.row].title
        return cell
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        let genreId = GenresList.allCases[section].getId()
        return displayedMovies[genreId]?.count ?? 0
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return GenresList.allCases.count
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return GenresList.allCases[section].getTitle()
    }
}

// MARK: - HomeDisplayLogic
extension HomeViewController: HomeDisplayLogic {

    func displayError(withMessage message: String) {
        let alertController = UIAlertController(title: "Error", message: message, preferredStyle: .alert)
        alertController.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
        
        DispatchQueue.main.async {
            self.present(alertController, animated: true, completion: nil)
        }
    }
    
    func displayFetchedMovies(viewModel: HomeModels.FetchMovies.ViewModel, forGenre genre: Int) {
        let displayedMovies = viewModel.displayedStatements
        self.displayedMovies[genre] = displayedMovies
        
        DispatchQueue.main.async {
            self.tableView.reloadData()
        }
    }
}
