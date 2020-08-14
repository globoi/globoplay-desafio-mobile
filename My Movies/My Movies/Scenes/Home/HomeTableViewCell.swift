//
//  HomeTableViewCell.swift
//  My Movies
//
//  Created by Rafael Valer on 11/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import UIKit

protocol HomeTableViewCellDelegate: class {
    func didSelectMovieAtIndex(_ indexPath: IndexPath, forGenre genre: Int)
}

class HomeTableViewCell: UITableViewCell {
    
    static let identifier: String = "HomeTableViewCell"
    static let height: CGFloat = 280.0
    
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var collectionView: UICollectionView!
    weak var delegate: HomeTableViewCellDelegate?
    
    var genre: Int!
    var displayedMovies: [HomeModels.FetchMovies.ViewModel.DisplayedMovie] = []
    
    override func awakeFromNib() {
        super.awakeFromNib()
        setupCollection()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    private func setupCollection() {
        collectionView.dataSource = self
        collectionView.delegate = self
        collectionView.register(UINib(nibName: MovieCollectionViewCell.identifier, bundle: nil), forCellWithReuseIdentifier: MovieCollectionViewCell.identifier)
    }
    
    func render(displayedMovies: [HomeModels.FetchMovies.ViewModel.DisplayedMovie], withGenre genre: GenresList) {
        self.genre = genre.getId()
        self.titleLabel.text = genre.getTitle()
        self.displayedMovies = displayedMovies
        self.collectionView.reloadData()
    }
}

// MARK: - UICollectionView
extension HomeTableViewCell: UICollectionViewDataSource, UICollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return displayedMovies.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {

        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: MovieCollectionViewCell.identifier, for: indexPath) as? MovieCollectionViewCell else {
            return UICollectionViewCell()
        }
        
        cell.render(withViewModel: displayedMovies[indexPath.row])
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        delegate?.didSelectMovieAtIndex(indexPath, forGenre: genre)
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: 140, height: 240)
    }
}

