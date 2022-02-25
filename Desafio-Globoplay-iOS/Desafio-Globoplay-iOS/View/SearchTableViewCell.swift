//
//  SearchTableViewCell.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 25/02/22.
//

import UIKit

class SearchTableViewCell: UITableViewCell {
    
    // MARK: - Properties
    
    static let reuseIdentifier = "SearchCell"
    
    private let movieImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = .scaleAspectFill
        imageView.translatesAutoresizingMaskIntoConstraints = false
        imageView.clipsToBounds = true
        imageView.setDimensions(width: 100, height: 140)
        return imageView
    }()
    
    private let playButton: UIButton = {
        let button = UIButton()
        let image = UIImage(systemName: "play.circle.fill", withConfiguration: UIImage.SymbolConfiguration(pointSize: 40))
        button.setImage(image, for: .normal)
        button.translatesAutoresizingMaskIntoConstraints = false
        button.tintColor = .white
        return button
    }()
    
    private let movieTitleLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.boldSystemFont(ofSize: 18)
        label.translatesAutoresizingMaskIntoConstraints = false
        label.setDimensions(width: 150, height: 40)
        return label
    }()
    
    // MARK: - Lifecycle
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        configureUI()
        
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: - Helper Methods
    
    func configureUI() {
        let stack = UIStackView(arrangedSubviews: [movieImageView, movieTitleLabel, playButton])
        stack.alignment = .center
        stack.spacing = 20
        stack.distribution = .fillProportionally
        
        addSubview(stack)
        
        stack.anchor(leading: leadingAnchor, trailling: trailingAnchor,
        paddingLeading: 16, paddingTrailling: 16)
        stack.centerY(inView: self)
    }
    
    public func configure(with model: SearchViewModel) {
        guard let url = URL(string: Constants.ProductionServer.IMAGE_URL + model.posterURL) else { return }
        movieImageView.kf.setImage(with: url)
        movieTitleLabel.text = model.movieName
    }
    
}
