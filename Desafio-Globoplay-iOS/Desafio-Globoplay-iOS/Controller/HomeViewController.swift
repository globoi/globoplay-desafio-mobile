//
//  HomeViewController.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 24/02/22.
//

import UIKit

enum Sections: Int {
    case PopularMovies = 0
    case TrendingTV = 1
    case TrendingMovies = 2
    case TopRatedMovies = 3
    case UpcomingMovies = 4
}

class HomeViewController: UIViewController {
    
    // MARK: - Properties
    
    private let homeTableView: UITableView = {
        let tableView = UITableView(frame: .zero, style: .grouped)
        tableView.register(HomeTableViewCell.self, forCellReuseIdentifier: HomeTableViewCell.identifier)
        tableView.backgroundColor = .black
        return tableView
    }()
    
    let sectionTitles: [String] = ["Filmes Populares", "Tendências na TV", "Tendências em Filmes", "Bem avaliados", "Em breve"]
        
    // MARK: - Lifecycle
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        homeTableView.frame = view.bounds
    }
    
    // MARK: - API
    
    // MARK: - Helper Methods
    
    func configureUI() {
        view.backgroundColor = .black
        
        view.addSubview(homeTableView)
        homeTableView.delegate = self
        homeTableView.dataSource = self
        homeTableView.anchor(top: view.topAnchor, leading: view.leadingAnchor)
        
        let headerView = HomeHeaderView(frame: CGRect(x: 0, y: 0, width: view.bounds.width, height: 450))
        homeTableView.tableHeaderView = headerView
        
    }
    
    // MARK: - Selectors
}

// MARK: - Extensions

extension HomeViewController: UITableViewDelegate, UITableViewDataSource {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        sectionTitles.count
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return sectionTitles[section]
    }
    
    func tableView(_ tableView: UITableView, willDisplayHeaderView view: UIView, forSection section: Int) {
        guard let header = view as? UITableViewHeaderFooterView else { return }
        header.textLabel?.font = UIFont.boldSystemFont(ofSize: 18)
        header.textLabel?.frame = CGRect(x: header.bounds.origin.x + 20, y: header.bounds.origin.y,
                                         width: 100, height: header.bounds.height)
        header.textLabel?.textColor = .customWhite
        header.textLabel?.text = header.textLabel?.text?.capitalizedFirstLetter()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: HomeTableViewCell.identifier, for: indexPath) as? HomeTableViewCell else {
            return UITableViewCell()
        }

        switch indexPath.section {
        case Sections.PopularMovies.rawValue:
            MovieClient.shared.getPopularMovies { result in
                switch result {
                case .success(let movies):
                    cell.configureCell(with: movies ?? [])
                case .failure(let error):
                    print(error.localizedDescription)
                }
            }
        case Sections.TrendingTV.rawValue:
            MovieClient.shared.getTrendingTV { result in
                switch result {
                case .success(let movies):
                    cell.configureCell(with: movies ?? [])
                case .failure(let error):
                    print(error.localizedDescription)
                }
            }
        case Sections.TrendingMovies.rawValue:
            MovieClient.shared.getTrendingMovies { result in
                switch result {
                case .success(let movies):
                    cell.configureCell(with: movies ?? [])
                case .failure(let error):
                    print(error.localizedDescription)
                }
            }
        case Sections.TopRatedMovies.rawValue:
            MovieClient.shared.getTopRatedMovies { result in
                switch result {
                case .success(let movies):
                    cell.configureCell(with: movies ?? [])
                case .failure(let error):
                    print(error.localizedDescription)
                }
            }
        case Sections.UpcomingMovies.rawValue:
            MovieClient.shared.getUpcomingMovies { result in
                switch result {
                case .success(let movies):
                    cell.configureCell(with: movies ?? [])
                case .failure(let error):
                    print(error.localizedDescription)
                }
            }
        default:
            return UITableViewCell()
        }
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 200
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 40
    }
}


