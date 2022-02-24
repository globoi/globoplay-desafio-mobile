//
//  FeedController.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 23/02/22.
//

import UIKit

private let reuseIdentifier = "MovieCell"

class FeedController: UICollectionViewController {
    
    // MARK: - Properties
    
    private lazy var viewModel = FeedViewModel()
    

    // MARK: - Lifecycle
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    // MARK: - API
    
    // MARK: - Helper Methods
    
    func configureUI() {
        view.backgroundColor = .homeBlack
        collectionView.register(MovieCollectionViewCell.self, forCellWithReuseIdentifier: reuseIdentifier)
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .horizontal
        layout.minimumLineSpacing = 8
        collectionView.collectionViewLayout = layout
        fetchMovies()
    }
    
    func fetchMovies() {
        MovieClient.shared.getMovies {  [weak self] result in
            guard let self = self else { return }
            switch result {
            case .success(let apiResponse):
                guard let result = apiResponse.results else { return }
                self.viewModel.movies = result
                self.viewModel.formatImageUrl(for: self.viewModel.movies)
                DispatchQueue.main.async {
                    self.collectionView.reloadData()
                }
                print("DEBUG: SUCESS")
            case .failure(_):
                AlertUtils.showAlert(message: "Falha ao acessar os filmes, por favor tente novamente.")
                print("DEBUG: ERROR")
            }
        }
    }
    
    // MARK: - Selectors
    
}

// MARK: - UICollectionViewDataSource

extension FeedController {
    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        print("Count:", viewModel.movies.count)
        return viewModel.movies.count
    }
    
    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! MovieCollectionViewCell
        cell.movie = self.viewModel.movies[indexPath.row]
        return cell
    }
}

// MARK: - UICollectionViewDelegate

extension FeedController {
    
}

// MARK: - UICollectionViewFlowLayout

extension FeedController: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: 100, height: 150)
    }
}
