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
    
    var movie: Movie?
    
    static let reuseIdentifier = "MovieCell"
    
    private var movieImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = .scaleAspectFill
        imageView.clipsToBounds = true
        imageView.setDimensions(width: 100, height: 150)
        imageView.backgroundColor = .darkGray
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
    
    override func layoutSubviews() {
        super.layoutSubviews()
        movieImageView.frame = contentView.bounds
    }
    
    // MARK: - Helper Methods
    
    func configureUI() {
        addSubview(movieImageView)
    }
    
    /// Function that set the image with the correct poster path.
    /// - Parameter model: <#model description#>
    public func configureMoviesCell(with posterPath: String) {
        guard let url = URL(string: Constants.ProductionServer.IMAGE_URL + posterPath) else { return }
        movieImageView.kf.setImage(with: url)
    }
}
