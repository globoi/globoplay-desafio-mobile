//
//  MovieCollectionViewCell.swift
//  MoviesApp
//
//  Created by Gustavo Tiecker on 23/02/22.
//

protocol ViewCode {
    func setupViewCodeElement()
    func setupComponents()
    func setupConstraints()
    func setupExtraConfiguration()
}

extension ViewCode {
    func setupViewCodeElement() {
        setupComponents()
        setupConstraints()
        setupExtraConfiguration()
    }

    func setupExtraConfiguration() {}
}

import UIKit

class MovieCollectionViewCell: UICollectionViewCell {
    
    static let reuseID = "MovieCollectionViewCell"
    
    private let movieImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = .scaleAspectFit
        imageView.translatesAutoresizingMaskIntoConstraints = false
        return imageView
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        backgroundColor = .black
        setupViewCodeElement()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func setup(with image: UIImage?) {
        movieImageView.image = image
    }
}

extension MovieCollectionViewCell: ViewCode {
    func setupComponents() {
        contentView.addSubview(movieImageView)
    }
    
    func setupConstraints() {
        NSLayoutConstraint.activate([
            movieImageView.topAnchor.constraint(equalTo: contentView.topAnchor),
            movieImageView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            movieImageView.trailingAnchor.constraint(equalTo: contentView.trailingAnchor),
            movieImageView.bottomAnchor.constraint(equalTo: contentView.bottomAnchor),
        ])
    }
}
