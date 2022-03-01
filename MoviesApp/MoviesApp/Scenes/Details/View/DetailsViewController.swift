//
//  DetailsViewController.swift
//  MoviesApp
//
//  Created by Gustavo Tiecker on 01/03/22.
//

import UIKit

class DetailsViewController: UIViewController {
    
    // MARK: - Properties
    private let viewModel: DetailsBusinessLogic
    
    // MARK: - UI Elements
    private let scrollView = UIScrollView()
    private let contentView = UIView()
    
    private let contentStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.alignment = .fill
        stackView.distribution = .fill
        stackView.spacing = 8
        stackView.axis = .vertical
        stackView.translatesAutoresizingMaskIntoConstraints = false
        return stackView
    }()
    
    private let movieImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = .scaleAspectFit
        imageView.translatesAutoresizingMaskIntoConstraints = false
        return imageView
    }()
    
    private let titleLabel = TitleLabel(textAlignment: .center, fontSize: 24)
    private let categoryLabel = SubtitleLabel(textAlignment: .center, fontSize: 18)
    private let descriptionLabel = BodyLabel(textAlignment: .left)
    
    private let watchButton = PrimaryButton(backgroundColor: .white, textColor: .black, icon: UIImage(systemName: "play.fill"), title: "Assista")
    private let favoritesButton = PrimaryButton(backgroundColor: .black, textColor: .darkGray, icon: UIImage(systemName: "star.fill"), title: "Minha lista")
    
    init(viewModel: DetailsBusinessLogic) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupViewCodeElement()
        configureViewController()
    }
    
    private func configureViewController() {
        view.backgroundColor = .black
        
        let movie = viewModel.getMovie()
        
        viewModel.downloadImage(of: movie.posterPath) { image in
            DispatchQueue.main.async {
                self.movieImageView.image = image
            }
        }
        
        titleLabel.text = movie.title
        categoryLabel.text = movie.mediaType
        descriptionLabel.text = movie.overview

        favoritesButton.addTarget(self, action: #selector(favoritesButtonTapped), for: .touchUpInside)
    }
    
    @objc func favoritesButtonTapped() {
        let favorite = viewModel.getMovie()
        
        PersistenceManager.updateWith(favorite: favorite, actionType: .add) { error in
            guard let error = error else {
                print("success")
                return
            }
            
            debugPrint(error)
        }
    }
}

extension DetailsViewController: ViewCode {
    func setupComponents() {
        contentView.addSubviews(movieImageView, titleLabel, categoryLabel, descriptionLabel, watchButton, favoritesButton)
        
        view.addSubview(scrollView)
        scrollView.addSubview(contentView)
    }
    
    func setupConstraints() {
        scrollView.pinToEdges(of: view)
        contentView.pinToEdges(of: scrollView)
        
        NSLayoutConstraint.activate([
            contentView.widthAnchor.constraint(equalTo: scrollView.widthAnchor),
            
            movieImageView.topAnchor.constraint(equalTo: contentView.topAnchor),
            movieImageView.centerXAnchor.constraint(equalTo: contentView.centerXAnchor),
            movieImageView.heightAnchor.constraint(equalToConstant: 300),
            movieImageView.widthAnchor.constraint(equalToConstant: 200),
            
            titleLabel.topAnchor.constraint(equalTo: movieImageView.bottomAnchor, constant: 16),
            titleLabel.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 8),
            titleLabel.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -8),
            titleLabel.heightAnchor.constraint(equalToConstant: 64),
            
            categoryLabel.topAnchor.constraint(equalTo: titleLabel.bottomAnchor),
            categoryLabel.leadingAnchor.constraint(equalTo: titleLabel.leadingAnchor),
            categoryLabel.trailingAnchor.constraint(equalTo: titleLabel.trailingAnchor),
            categoryLabel.heightAnchor.constraint(equalToConstant: 28),
            
            descriptionLabel.topAnchor.constraint(equalTo: categoryLabel.bottomAnchor, constant: 16),
            descriptionLabel.leadingAnchor.constraint(equalTo: titleLabel.leadingAnchor),
            descriptionLabel.trailingAnchor.constraint(equalTo: titleLabel.trailingAnchor),
            
            watchButton.topAnchor.constraint(equalTo: descriptionLabel.bottomAnchor, constant: 16),
            watchButton.leadingAnchor.constraint(equalTo: titleLabel.leadingAnchor),
            watchButton.trailingAnchor.constraint(equalTo: contentView.centerXAnchor, constant: -4),
            watchButton.heightAnchor.constraint(equalToConstant: 50),
            
            favoritesButton.topAnchor.constraint(equalTo: descriptionLabel.bottomAnchor, constant: 16),
            favoritesButton.leadingAnchor.constraint(equalTo: contentView.centerXAnchor, constant: 4),
            favoritesButton.trailingAnchor.constraint(equalTo: titleLabel.trailingAnchor),
            favoritesButton.heightAnchor.constraint(equalToConstant: 50)
        ])
    }
}
