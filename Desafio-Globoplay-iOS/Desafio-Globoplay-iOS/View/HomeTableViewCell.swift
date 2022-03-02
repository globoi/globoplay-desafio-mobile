//
//  HomeTableViewCell.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 24/02/22.
//

import UIKit

protocol HomeTableViewCellDelegate: AnyObject {
    func homeTableViewCellDidTapCell(_ cell: HomeTableViewCell, viewModel: MovieDetailViewModel)
}

class HomeTableViewCell: UITableViewCell {
    
    // MARK: - Properties
    
    static let identifier = "HomeTableViewCell"
    
    weak var delegate: HomeTableViewCellDelegate?
    
    private var movies: [Movie] = [Movie]()
    
    private let collectionView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        layout.itemSize = CGSize(width: 100, height: 150)
        layout.scrollDirection = .horizontal
        layout.minimumInteritemSpacing = 8
        let collectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
        collectionView.register(MovieCollectionViewCell.self, forCellWithReuseIdentifier: MovieCollectionViewCell.reuseIdentifier)
        return collectionView
    }()
    
    // MARK: - Lifecycle
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        contentView.backgroundColor = .black
        configureUI()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        collectionView.frame = contentView.bounds
    }
    
    // MARK: - API
    
    // MARK: - Helper Methods
    
    func configureUI() {
        addSubview(collectionView)
        
        collectionView.backgroundColor = .black
        collectionView.delegate = self
        collectionView.dataSource = self
    }
    
    func configureCell(with movies: [Movie]) {
        self.movies = movies
        DispatchQueue.main.async { [weak self] in
            self?.collectionView.reloadData()
        }
    }
    
    private func downloadTitleAt(indexPath: IndexPath) {
        print("DEBUG: FILME \(movies[indexPath.row].originalTitle) ADICIONADO A MINHA LISTA.")
    }
    
    // MARK: - Selectors
    
}

// MARK: - Extensions

extension HomeTableViewCell: UICollectionViewDelegate, UICollectionViewDataSource {
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: MovieCollectionViewCell.reuseIdentifier, for: indexPath) as? MovieCollectionViewCell else {
            return UICollectionViewCell()
        }
        
        guard let model = movies[indexPath.row].posterPath else {
            return UICollectionViewCell()
        }
        cell.configureMoviesCell(with: model)
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        movies.count
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        collectionView.deselectItem(at: indexPath, animated: true)
        let movie = movies[indexPath.row]
        
        guard let movieID = movie.id else { return }
        MovieClient.shared.searchSelectedMovie(with: movieID) { [weak self] result in
            switch result {
            case .success(_):
                DispatchQueue.main.async {
                    let movie = self?.movies[indexPath.row]
                    guard let movieDescription = movie?.overview else { return }
                    guard let strongSelf = self else { return }
                    guard let posterPath = movie?.posterPath else { return }
                    let originCountry = movie?.originCountry
                    
                    guard let url = URL(string: Constants.ProductionServer.IMAGE_URL + posterPath) else { return }
                    
                    let viewModel = MovieDetailViewModel(imageURL: url, movieTitleText: ((movie?.originalName ?? movie?.name) ?? movie?.originalTitle) ?? "--", movieDescriptionText: movieDescription, releaseDateText: (movie?.releaseDate ?? movie?.firstAirDate) ?? "--", originCountryText: originCountry?.first ?? "--", originNameText: ((movie?.originalName ?? movie?.name) ?? movie?.originalTitle) ?? "--")
                    self?.delegate?.homeTableViewCellDidTapCell(strongSelf, viewModel: viewModel)
                }
                
            case .failure(let error):
                print(error.localizedDescription)
            }
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, contextMenuConfigurationForItemAt indexPath: IndexPath, point: CGPoint) -> UIContextMenuConfiguration? {
            
            let configureContextMenu = UIContextMenuConfiguration(
                identifier: nil,
                previewProvider: nil) {[weak self] _ in
                    let addMovieToListAction = UIAction(title: "Adicinar a minha lista", subtitle: nil, image: nil, identifier: nil, discoverabilityTitle: nil, state: .off) { _ in
                        self?.downloadTitleAt(indexPath: indexPath)
                    }
                    return UIMenu(title: "", image: nil, identifier: nil, options: .displayInline, children: [addMovieToListAction])
                }
            
            return configureContextMenu
        }
}
