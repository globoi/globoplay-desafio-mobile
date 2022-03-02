//
//  MovieDetailController.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 23/02/22.
//

import UIKit

class MovieDetailController: UIViewController {
    
    // MARK: - Properties
    
    private var movies: [Movie] = [Movie]()
    
    private var movieIndexPath: IndexPath?
    
    private var movieImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = .scaleAspectFill
        imageView.clipsToBounds = true
        imageView.backgroundColor = .darkGray
        imageView.setDimensions(width: 120, height: 170)
        return imageView
    }()
    
    private lazy var movieBackGroundBlurred: UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = .scaleAspectFill
        imageView.clipsToBounds = true
        imageView.backgroundColor = .homeBlack
        imageView.addBlurredBackground(style: .systemUltraThinMaterialDark)
        return imageView
    }()
    
    private let movieTitleLabel: UILabel = {
        let label = UILabel()
        label.text = "Erro ao carregar o vídeo."
        label.textColor = .customWhite
        label.font = UIFont.boldSystemFont(ofSize: 28)
        return label
    }()
    
    private let descriptionLabel: UILabel = {
        let label = UILabel()
        label.numberOfLines = 5
        label.text = "..."
        label.textColor = .customWhite
        label.font = UIFont.systemFont(ofSize: 16)
        return label
    }()
    
    private let myListButton: UIButton = {
        let button = UIButton(type: .system)
        button.backgroundColor = .black
        button.setTitle("★ Adicionar a minha lista", for: .normal)
        button.titleLabel?.font = UIFont.boldSystemFont(ofSize: 14)
        button.tintColor = .customWhite
        button.layer.borderColor = UIColor.customWhite.cgColor
        button.layer.borderWidth = 1
        button.setDimensions(width: 180, height: 45)
        button.layer.cornerRadius = 5
        button.addTarget(self, action: #selector(handleMyListButton), for: .touchUpInside)
        return button
    }()
    
    private let watchButton: UIButton = {
        let button = UIButton(type: .system)
        button.backgroundColor = .customWhite
        button.setTitle("▶︎ Assista ao trailer", for: .normal)
        button.titleLabel?.font = UIFont.boldSystemFont(ofSize: 14)
        button.tintColor = .black
        button.layer.borderColor = UIColor.black.cgColor
        button.layer.borderWidth = 1
        button.setDimensions(width: 180, height: 45)
        button.layer.cornerRadius = 5
        button.addTarget(self, action: #selector(handleWatchButton), for: .touchUpInside)
        return button
    }()
    
    private let originCountryLabel: UILabel = {
        let label = UILabel()
        label.numberOfLines = 0
        label.lineBreakMode = .byWordWrapping
        label.text = "-"
        label.textColor = .customWhite
        label.font = UIFont.systemFont(ofSize: 16)
        return label
    }()
    
    private let originalNameLabel: UILabel = {
        let label = UILabel()
        label.numberOfLines = 0
        label.lineBreakMode = .byWordWrapping
        label.text = "-"
        label.textColor = .customWhite
        label.font = UIFont.systemFont(ofSize: 16)
        return label
    }()
    
    private let releaseDateLabel: UILabel = {
        let label = UILabel()
        label.numberOfLines = 0
        label.lineBreakMode = .byWordWrapping
        label.text = "-"
        label.textColor = .customWhite
        label.font = UIFont.systemFont(ofSize: 16)
        return label
    }()
    
    private let activityView: UIView = {
        let view = UIView()
        view.backgroundColor = .black
        view.isHidden = true
        return view
    }()
    
    // MARK: - Lifecycle
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
    }
    
    // MARK: - API
    
    // MARK: - Helper Methods
    
    func configureUI() {
        view.stopActivityView()
        view.addSubview(activityView)
        
        activityView.anchor(top: view.topAnchor, leading: view.leadingAnchor, bottom: view.bottomAnchor, trailling: view.trailingAnchor)
        
        view.backgroundColor = .black
        
        /// Configure the movie header UI.
        view.addSubview(movieBackGroundBlurred)
        movieBackGroundBlurred.anchor(top: view.topAnchor,
                                      leading: view.leadingAnchor,
                                      trailling: view.trailingAnchor,
                                      height: 400)
        
        /// BUGFIX: Gradient does not work.
        //movieBackGroundBlurred.addDarkGradient(frame: movieBackGroundBlurred.frame, colors: [.white, .black])
        
        let stack = UIStackView(arrangedSubviews: [movieImageView, movieTitleLabel, descriptionLabel])
        stack.axis = .vertical
        stack.spacing = 8
        stack.alignment = .center
        stack.distribution = .fill
        
        view.addSubview(stack)
        stack.anchor(top: view.topAnchor, leading: view.leadingAnchor, trailling: view.trailingAnchor,
                     paddingTop: 40,
                     paddingLeading: 12, paddingTrailling: 12, height: 400)
        stack.centerY(inView: movieBackGroundBlurred)
        
        let buttonStack = UIStackView(arrangedSubviews: [watchButton, myListButton])
        buttonStack.spacing = 8
        buttonStack.distribution = .fillEqually
        buttonStack.alignment = .center
        
        view.addSubview(buttonStack)
        buttonStack.anchor(top: movieBackGroundBlurred.bottomAnchor,
                           leading: view.leadingAnchor, trailling: view.trailingAnchor,
                           paddingTop: 12, paddingLeading: 12, paddingTrailling: 12)
        
        let moreInfoStack = UIStackView(arrangedSubviews: [originalNameLabel, originCountryLabel, releaseDateLabel])
        moreInfoStack.spacing = 8
        moreInfoStack.distribution = .fillEqually
        moreInfoStack.axis = .vertical
        moreInfoStack.alignment = .leading
        
        view.addSubview(moreInfoStack)
        moreInfoStack.anchor(top: buttonStack.bottomAnchor,
                             leading: view.leadingAnchor, trailling: view.trailingAnchor,
                             paddingTop: 12, paddingLeading: 12, paddingTrailling: 12)
    }
    
    func configureDetail(with viewModel: MovieDetailViewModel){
        
        guard let releaseDate = viewModel.releaseDateText else { return }
        guard let originalName = viewModel.originNameText else { return }
        guard let originCountry = viewModel.originCountryText else { return }
        
        movieTitleLabel.text = viewModel.movieTitleText
        descriptionLabel.text = viewModel.movieDescriptionText
        releaseDateLabel.text = "Data de Lançamento: \(releaseDate)"
        originalNameLabel.text = "Título Original: \(originalName)"
        originCountryLabel.text = "País: \(originCountry)"
        movieIndexPath = viewModel.movieIndexPath
        
        movieImageView.kf.setImage(with: viewModel.imageURL)
        movieBackGroundBlurred.kf.setImage(with: viewModel.imageURL)
    }
    
    func configureMovies(with movies: [Movie]) {
        self.movies = movies
    }
    
    private func addMovieAt(indexPath: IndexPath) {
        DataPersistenceManager.shared.addMovieToMyList(viewModel: movies[indexPath.row]) { result in
            switch result {
            case .success():
                NotificationCenter.default.post(name: NSNotification.Name("adicionadoALista"), object: nil)
                print("DEBUG: FILME ADIONADO À LISTA.")
            case .failure(let error):
                print(error.localizedDescription)
            }
        }
    }
    
    // MARK: - Selectors
    
    @objc func handleMyListButton() {
        addMovieAt(indexPath: movieIndexPath!)
        AlertUtils.showAlert(message: "Conteúdo adicionado a sua lista de filmes.")
    }
    
    @objc func handleWatchButton() {
        //AlertUtils.showAlert(message: "Carregando filme para assistir.")
        activityView.isHidden = false
        activityView.startActivityView(forView: activityView)
        view.bringSubviewToFront(activityView)
        
        guard let movieName = movieTitleLabel.text ?? originalNameLabel.text else  { return }
        guard let movieDescription = descriptionLabel.text else { return }

        MovieClient.shared.getMovieTrailler(with: movieName) { [weak self] result in
            switch result {
            case .success(let youtubeElement):
                DispatchQueue.main.async {
                    self?.activityView.stopActivityView()
                    self?.activityView.isHidden = true
                    let viewController = MoviePreviewController()
                    viewController.configurePreview(with: MoviePreviewViewModel(movieTitleText: movieName,
                                                                                youtubeView: youtubeElement!,
                                                                                movieDescriptionText: movieDescription ?? "--"))
                    self?.navigationController?.pushViewController(viewController, animated: true)
                }
            case .failure(let error):
                self?.activityView.stopActivityView()
                self?.activityView.isHidden = true
                print(error.localizedDescription)
            }
        }
    }
}
