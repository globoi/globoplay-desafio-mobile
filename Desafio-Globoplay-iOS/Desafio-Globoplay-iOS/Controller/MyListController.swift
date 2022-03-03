//
//  MyListController.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 23/02/22.
//

import UIKit

class MyListController: UIViewController {
    
    // MARK: - Properties
    
    private var movies: [MovieModel] = [MovieModel]()
    
    private let reuseIdentifier = "minhaListaTableViewCell"
    
    private let mylistTableView: UITableView = {
        let tableView = UITableView()
        tableView.register(SearchTableViewCell.self, forCellReuseIdentifier: SearchTableViewCell.reuseIdentifier)
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "minhaListaTableViewCell")
        return tableView
    }()
    
    private let activityView: UIView = {
        let view = UIView()
        view.backgroundColor = .black
        view.isHidden = true
        return view
    }()
    
    // MARK: - Lifecycle
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        mylistTableView.frame = view.bounds
    }
    
    // MARK: - CoreData Functions
    
    private func fetchLocalMoviesList() {
        DataPersistenceManager.shared.fetchMoviesFromDataBaseLocal { [weak self] result in
            switch result {
            case .success(let movies):
                self?.movies = movies
                DispatchQueue.main.async {
                    self?.mylistTableView.reloadData()
                }
            case .failure(let error):
                print(error.localizedDescription)
            }
        }
    }
    
    // MARK: - Helper Methods
    
    func configureUI() {
        view.stopActivityView()
        view.addSubview(activityView)
        
        activityView.anchor(top: view.topAnchor, leading: view.leadingAnchor, bottom: view.bottomAnchor, trailling: view.trailingAnchor)
        
        view.backgroundColor = .homeBlack
        title = "Minha lista"
        navigationController?.navigationBar.prefersLargeTitles = true
        navigationController?.navigationItem.largeTitleDisplayMode = .always
        
        view.addSubview(mylistTableView)
        mylistTableView.delegate = self
        mylistTableView.dataSource = self
        
        fetchLocalMoviesList()
        
        NotificationCenter.default.addObserver(forName: NSNotification.Name("adicionadoALista"), object: nil, queue: nil) { _ in
            self.fetchLocalMoviesList()
        }
    }
}

// MARK: - UITableViewDelegate

extension MyListController: UITableViewDelegate, UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        movies.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: SearchTableViewCell.reuseIdentifier, for: indexPath) as? SearchTableViewCell else {
            return UITableViewCell()
        }
        
        let movie = movies[indexPath.row]
        cell.configure(with: SearchTableViewCellViewModel(movieName: (movie.originalName ?? movie.originalTitle) ?? "Título Indisponível", posterURL: movie.posterPath ?? ""))
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 140
    }
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        switch editingStyle {
        case .delete:
            DataPersistenceManager.shared.deleteTitleWith(model: movies[indexPath.row]) { [weak self] result in
                switch result {
                case .success():
                    AlertUtils.showAlert(message: "Filme deletado da sua lista de favoritos.")
                case .failure(let error):
                    AlertUtils.showAlert(message: "Erro: \(error)")
                    print("DEBUG: Error -> \(error.localizedDescription)")
                }
                self?.movies.remove(at: indexPath.row)
                tableView.deleteRows(at: [indexPath], with: .fade)
            }
        default: break
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        activityView.isHidden = false
        activityView.startActivityView(forView: activityView)
        view.bringSubviewToFront(activityView)
        
        let movie = movies[indexPath.row]
        guard let movieName = (movie.originalName ?? movie.originalTitle) ?? movie.name else  { return }
        guard let movieDescription = movie.overview else { return }
        
        MovieAPIService.shared.getMovieTrailer(with: movieName) { [weak self] result in
            switch result {
            case .success(let youtubeElement):
                DispatchQueue.main.async {
                    self?.activityView.stopActivityView()
                    self?.activityView.isHidden = true
                    let viewController = MoviePreviewController()
                    viewController.configurePreview(with: MoviePreviewViewModel(
                        movieTitleText: movieName,
                        youtubeView: youtubeElement!,
                        movieDescriptionText: movieDescription))
                    self?.navigationController?.pushViewController(viewController, animated: true)
                }
            case .failure(let error):
                self?.activityView.stopActivityView()
                self?.activityView.isHidden = true
                AlertUtils.showAlert(message: "ERRO ao carregar o trailer do filme. Por favor tente novamente.")
                print(error.localizedDescription)
            }
        }
    }
}
