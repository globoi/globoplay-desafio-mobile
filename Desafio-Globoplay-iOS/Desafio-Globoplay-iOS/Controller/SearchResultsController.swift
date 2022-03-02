//
//  SearchResultsController.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 25/02/22.
//

import UIKit

protocol SearchResultsControllerDelegate: AnyObject {
    func searchResultsControllerDidTapMovie(_ viewModel: MoviePreviewViewModel)
}

class SearchResultsController: UIViewController {
    
    // MARK: - Properties
    
    public var movies: [Movie] = [Movie]()
    
    public weak var delegate: SearchResultsControllerDelegate?
    
    public let searchResultsCollectionView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        layout.itemSize = CGSize(width:  100, height: 150)
        layout.minimumInteritemSpacing = 0
        let collectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
        collectionView.register(MovieCollectionViewCell.self, forCellWithReuseIdentifier: MovieCollectionViewCell.reuseIdentifier)
        return collectionView
    }()
    
    // MARK: - Lifecycle
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        searchResultsCollectionView.frame = view.bounds
    }
    
    // MARK: - Helper Methods
    
    func configureUI() {
        searchResultsCollectionView.delegate = self
        searchResultsCollectionView.dataSource = self
        view.backgroundColor = .black
        view.addSubview(searchResultsCollectionView)
    }
    
    // MARK: - Selectors
    
}

// MARK: - UICollectionViewDelegate, UICollectionViewDataSource

extension SearchResultsController: UICollectionViewDelegate, UICollectionViewDataSource {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return movies.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: MovieCollectionViewCell.reuseIdentifier, for: indexPath) as? MovieCollectionViewCell else {
            return UICollectionViewCell()
        }
        
        let movie = movies[indexPath.row]
        cell.configureMoviesCell(with: movie.posterPath ?? "")
        
        cell.backgroundColor = .darkGray
        
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        
        collectionView.deselectItem(at: indexPath, animated: true)
        
        let movie = movies[indexPath.row]
        
        guard let movieName = movie.originalTitle ?? movie.originalName else { return }

        searchResultsCollectionView.startActivityView(forView: searchResultsCollectionView)
        MovieClient.shared.getMovieTrailler(with: movieName) { [weak self] result in
            switch result {
            case .success(let youtubeElement):
                self?.delegate?.searchResultsControllerDidTapMovie(MoviePreviewViewModel(movieTitleText: movieName, youtubeView: youtubeElement!, movieDescriptionText: movie.overview ?? "--"))
            case .failure(let error):
                print(error.localizedDescription)
            }
        }
    }
    
}
