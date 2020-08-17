//
//  MoviesCollectionView.swift
//  My Movies
//
//  Created by Rafael Valer on 12/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation
import UIKit

protocol DisplayableMovie {
    var title: String { get set }
    var posterPath: String? { get set }
}

protocol MoviesCollectionViewDelegate: class {
    func didSelectItem(at indexPath: IndexPath)
}

class MoviesCollectionView: UIView {
    
    @IBOutlet weak var containerView: UIView!
    @IBOutlet weak var collectionView: ContentSizedCollectionView!
    
    private let identifier: String = "MoviesCollectionView"
    
    weak var delegate: MoviesCollectionViewDelegate?
    var displayableMovies: [DisplayableMovie] = []
    var emptyResultMessage: String = "Nenhum filme foi encontrado."

    override init(frame: CGRect) {
        super.init(frame: frame)
        initSubViews()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        initSubViews()
    }
    
    func setMovies(_ displayableMovies: [DisplayableMovie]) {
        self.displayableMovies = displayableMovies
        
        DispatchQueue.main.async { [weak self] in
            guard let self = self else { return }
            self.collectionView.backgroundView = displayableMovies.count == 0 ? CustomMessageView(message: self.emptyResultMessage) : nil
            self.collectionView.reloadData()
        }
    }
    
    private func initSubViews() {
        let nib = UINib(nibName: identifier, bundle: Bundle(identifier: identifier))
        nib.instantiate(withOwner: self, options: nil)
        containerView.translatesAutoresizingMaskIntoConstraints = false
        addSubview(containerView)
        self.addConstraints()

        collectionView.dataSource = self
        collectionView.delegate = self
        collectionView.register(UINib(nibName: MovieCollectionViewCell.identifier, bundle: nil), forCellWithReuseIdentifier: MovieCollectionViewCell.identifier)
    }
        
    private func addConstraints() {
        NSLayoutConstraint.activate([
            self.heightAnchor.constraint(greaterThanOrEqualToConstant: 40),
            self.topAnchor.constraint(equalTo: containerView.topAnchor),
            self.trailingAnchor.constraint(equalTo: containerView.trailingAnchor),
            self.leadingAnchor.constraint(equalTo: containerView.leadingAnchor),
            self.bottomAnchor.constraint(equalTo: containerView.bottomAnchor)
        ])
    }
}

extension MoviesCollectionView: UICollectionViewDataSource, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout {

    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return displayableMovies.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: MovieCollectionViewCell.identifier, for: indexPath) as? MovieCollectionViewCell else {
            return UICollectionViewCell()
        }
        cell.render(withDisplayableMovie: displayableMovies[indexPath.row])
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let viewWidth = self.frame.width
        let cellWidth = viewWidth / 3 - 8
        let cellHeight = cellWidth * 1.71
        return CGSize(width: cellWidth, height: cellHeight)
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        delegate?.didSelectItem(at: indexPath)
    }
}
