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
    
    // MARK: - API
    
    // MARK: - Helper Methods
    
    func configureUI() {
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
    
    // MARK: - Selectors
    
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
        cell.configure(with: SearchViewModel(movieName: (movie.originalName ?? movie.originalTitle) ?? "Título Indisponível", posterURL: movie.posterPath ?? ""))
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
}
