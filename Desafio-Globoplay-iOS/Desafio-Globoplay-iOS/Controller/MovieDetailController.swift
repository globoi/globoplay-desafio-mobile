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
        stack.anchor(leading: view.leadingAnchor, trailling: view.trailingAnchor,
                     paddingLeading: 12, paddingTrailling: 12, height: 362)
        stack.centerY(inView: movieBackGroundBlurred)
        
        view.addSubview(watchButton)
        watchButton.anchor(top: movieBackGroundBlurred.bottomAnchor,
                           leading: view.leadingAnchor,
                           paddingTop: 12, paddingLeading: 12, width: 190, height: 45)
        view.addSubview(myListButton)
        myListButton.anchor(top: movieBackGroundBlurred.bottomAnchor,
                           leading: watchButton.trailingAnchor, trailling: view.trailingAnchor,
                           paddingTop: 12, paddingLeading: 12, paddingTrailling: 12, height: 45)
        
        
        let moreInfoStack = UIStackView(arrangedSubviews: [originalNameLabel, originCountryLabel, releaseDateLabel])
        moreInfoStack.spacing = 8
        moreInfoStack.distribution = .fill
        moreInfoStack.axis = .vertical
        moreInfoStack.alignment = .leading
        
        view.addSubview(moreInfoStack)
        moreInfoStack.anchor(top: watchButton.bottomAnchor,
                             leading: view.leadingAnchor, trailling: view.trailingAnchor,
                             paddingTop: 12, paddingLeading: 12, paddingTrailling: 12)
    }
    
    /// Configure the detailView with the updated values from the ViewModel.
    /// - Parameter model: `MovieDetailViewModel`.
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
    
    /// Function that update de local `movies` with de correspondent Array of `Movie.
    /// - Parameter movies: `Movie` Array.
    func configureMovies(with movies: [Movie]) {
        self.movies = movies
    }
    
    // MARK: - CoreData Functions
    
    private func addMovieAt(indexPath: IndexPath) {
        DataPersistenceManager.shared.addMovieToMyList(viewModel: movies[indexPath.row]) { result in
            switch result {
            case .success():
                NotificationCenter.default.post(name: NSNotification.Name("adicionadoALista"), object: nil)
                AlertUtils.showAlert(message: "Conteúdo adicionado a sua lista de filmes.")
            case .failure(let error):
                AlertUtils.showAlert(message: "ERRO ao adicionar conteúdo a sua lista de filmes. Por favor, tente novamente.")
                print(error.localizedDescription)
            }
        }
    }
    
    // MARK: - Selectors
    
    @objc func handleMyListButton() {
        addMovieAt(indexPath: movieIndexPath!)
    }
    
    @objc func handleWatchButton() {
        activityView.isHidden = false
        activityView.startActivityView(forView: activityView)
        view.bringSubviewToFront(activityView)
        
        guard let movieName = movieTitleLabel.text ?? originalNameLabel.text else  { return }
        guard let movieDescription = descriptionLabel.text else { return }
        
        MovieAPIService.shared.getMovieTrailer(with: movieName) { [weak self] result in
            switch result {
            case .success(let youtubeElement):
                DispatchQueue.main.async {
                    self?.activityView.stopActivityView()
                    self?.activityView.isHidden = true
                    let viewController = MoviePreviewController()
                    viewController.configurePreview(with: MoviePreviewViewModel(movieTitleText: movieName,
                                                                                youtubeView: youtubeElement!,
                                                                                movieDescriptionText: movieDescription))
                    viewController.configureMovies(with: self?.movies ?? [], at: (self?.movieIndexPath)!)
                    self?.navigationController?.pushViewController(viewController, animated: true)
                }
            case .failure(let error):
                DispatchQueue.main.async {
                    self?.activityView.stopActivityView()
                    self?.activityView.isHidden = true
                    AlertUtils.showAlert(message: error.localizedDescription)
                }
                print(error.localizedDescription)
            }
        }
    }
}
