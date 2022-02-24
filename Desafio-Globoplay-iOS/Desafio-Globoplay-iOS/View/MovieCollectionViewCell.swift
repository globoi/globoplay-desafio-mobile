//
//  MovieCollectionViewCell.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 23/02/22.
//

import UIKit
import Kingfisher

class MovieCollectionViewCell: UICollectionViewCell {
    
    // MARK: - Properties
    
    var movie: Movie? {
        didSet {
            configureCell()
        }
    }
    
    private lazy var movieImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = .scaleAspectFill
        imageView.clipsToBounds = true
        imageView.setDimensions(width: 100, height: 150)
        imageView.backgroundColor = .black
        
        let tap = UITapGestureRecognizer(target: self, action: #selector(movieImagePressed))
        imageView.addGestureRecognizer(tap)
        imageView.isUserInteractionEnabled = true
        
        return imageView
    }()
    
    // MARK: - Lifecycle
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        configureUI()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: - Helper Methods
    
    func configureUI() {
        backgroundColor = .darkGray
        
        addSubview(movieImageView)
        movieImageView.anchor(top: topAnchor,
                              leading: leadingAnchor)
    }
    
    func configureCell() {
        guard let movie = movie else { return}

        let url = URL(string: movie.urlImage ?? "")
        movieImageView.kf.setImage(with: url)
    }
    
    // MARK: - Selectors
    
    @objc func movieImagePressed() {
        print("DEBUG: MOVIE IMAGE PRESSED.")
    }
}
