//
//  SearchController.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 25/02/22.
//

import UIKit

class SearchController: UIViewController {
    
    // MARK: - Properties
    
    private var movies: [Movie] = [Movie]()
    
    private let searchTableView: UITableView = {
        let tableView = UITableView()
        tableView.register(SearchTableViewCell.self, forCellReuseIdentifier: SearchTableViewCell.reuseIdentifier)
        return tableView
    }()
    
    private let searchController: UISearchController = {
        let controller = UISearchController(searchResultsController: SearchResultsController())
        controller.searchBar.placeholder = "Pesquise por um filme ou série, aqui."
        controller.searchBar.searchBarStyle = .minimal
        return controller
    }()
    
    private var searchBarIsEmpty: Bool?
    
    // MARK: - Lifecycle
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
        fetchSearch()
        searchController.searchBar.isUserInteractionEnabled = true
        searchController.searchResultsUpdater = self
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        searchTableView.frame = view.bounds
    }
    
    // MARK: - API
    
    private func fetchSearch() {
        MovieAPIService.shared.getDiscoverMovies { [weak self] result in
            switch result {
            case .success(let movies):
                self?.movies = movies ?? []
                DispatchQueue.main.async {
                    self?.searchTableView.reloadData()
                }
                
            case .failure(let error):
                DispatchQueue.main.async {
                    self?.searchController.searchBar.isUserInteractionEnabled = false
                    AlertUtils.showAlert(message: error.localizedDescription)
                }
                print(error.localizedDescription)
            }
        }
    }
    
    // MARK: - Helper Methods
    
    func configureUI() {
        view.backgroundColor = .black
        view.addSubview(searchTableView)
        searchTableView.delegate = self
        searchTableView.dataSource = self
        
        title = "Descubra"
        navigationController?.navigationBar.prefersLargeTitles = true
        navigationController?.navigationItem.largeTitleDisplayMode = .always
        navigationItem.searchController = searchController
        navigationItem.searchController?.obscuresBackgroundDuringPresentation = false
        navigationController?.navigationBar.tintColor = .customWhite
        definesPresentationContext = true
    }
}

// MARK: - UITableViewDelegate + UITableViewDataSource

extension SearchController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return movies.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        guard let cell = tableView.dequeueReusableCell(withIdentifier: SearchTableViewCell.reuseIdentifier, for: indexPath) as? SearchTableViewCell else {
            return UITableViewCell()
        }
        
        let movie = movies[indexPath.row]
        let model = SearchTableViewCellViewModel(movieName: (movie.originalTitle ?? movie.originalName) ?? "Unknown title name", posterURL: movie.posterPath ?? "")
        cell.configure(with: model)
        return cell
    }
    
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 140
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let movie = movies[indexPath.row]
        
        guard let movieName = movie.originalTitle ?? movie.originalName else { return }
        
        tableView.startActivityView(forView: tableView)
        MovieAPIService.shared.getMovieTrailer(with: movieName) { [weak self] result in
            switch result {
            case .success(let youtubeElement):
                DispatchQueue.main.async {
                    let viewController = MoviePreviewController()
                    viewController.configurePreview(with: MoviePreviewViewModel(movieTitleText: movieName,
                                                                                youtubeView: youtubeElement!,
                                                                                movieDescriptionText: movie.overview ?? "--"))
                    self?.navigationController?.pushViewController(viewController, animated: true)
                }
            case .failure(let error):
                DispatchQueue.main.async {
                    AlertUtils.showAlert(message: error.localizedDescription)
                }
                print(error.localizedDescription)
            }
        }
    }
}

//MARK: - UISearchResultsUpdating + SearchResultsControllerDelegate

extension SearchController: UISearchResultsUpdating, SearchResultsControllerDelegate {
    
    func updateSearchResults(for searchController: UISearchController) {
        let searchBar = searchController.searchBar
        
        if ((searchBar.text?.isEmpty) == true) {
            self.searchTableView.isHidden = false
        } else if searchBar.text?.count ?? 0 > 0 {
            self.searchTableView.isHidden = true
        }
        
        guard let query = searchBar.text,
              !query.trimmingCharacters(in: .whitespaces).isEmpty,
              query.trimmingCharacters(in: .whitespaces).count >= 3,
              let resultsController = searchController.searchResultsController as? SearchResultsController else {
                  return
              }
        
        resultsController.delegate = self
        
        MovieAPIService.shared.search(with: query) { result in
            DispatchQueue.main.async {
                switch result {
                case .success(let movies):
                    resultsController.movies = movies ?? []
                    self.searchTableView.isHidden = true
                    print(resultsController.movies)
                    resultsController.searchResultsCollectionView.reloadData()
                case .failure(let error):
                    DispatchQueue.main.async {
                        AlertUtils.showAlert(message: error.localizedDescription)
                    }
                    print(error.localizedDescription)
                }
            }
        }
    }
    
    func searchResultsControllerDidTapMovie(_ viewModel: MoviePreviewViewModel) {
        DispatchQueue.main.async {
            let viewController = MoviePreviewController()
            viewController.configurePreview(with: viewModel)
            self.navigationController?.pushViewController(viewController, animated: true)
        }
    }
    
}
