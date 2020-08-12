//
//  MovieDetailsViewController.swift
//  My Movies
//
//  Created by Rafael Valer on 11/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import UIKit

protocol MovieDetailsDisplayLogic: class {
    func displayFetchedMovieDetails(viewModel: MovieDetailsModels.FetchMovieDetails.ViewModel)
    func displayFetchedMovieRecommendations(viewModel: MovieDetailsModels.FetchMovieRecommendations.ViewModel)
}

class MovieDetailsViewController: UIViewController {
    
    static let identifier: String = "MovieDetailsViewController"
    
    var movie: Movie
    var interactor: MovieDetailsBusinessLogic?
    var router: (NSObject & MovieDetailsRoutingLogic & MovieDetailsDataPassing)?

    // MARK: Object lifecycle
    
    init?(coder: NSCoder, selectedMovie: Movie) {
        self.movie = selectedMovie
        super.init(coder: coder)
        setup()
    }
    
    required init?(coder: NSCoder) {
        fatalError("You must create this view controller with a Movie")
    }
    
    // MARK: Setup
    
    private func setup() {
        let viewController = self
        let interactor = MovieDetailsInteractor()
        let presenter = MovieDetailsPresenter()
        let router = MovieDetailsRouter()
        viewController.interactor = interactor
        viewController.router = router
        interactor.presenter = presenter
        presenter.viewController = viewController
        router.viewController = viewController
        router.dataStore = interactor
    }
    
    // MARK: View lifecycle
    
    @IBOutlet weak var backgroundImageView: UIImageView!
    @IBOutlet weak var gradientView: UIView!
    @IBOutlet weak var posterImageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var subtitleLabel: UILabel!
    @IBOutlet weak var descriptionLabel: UILabel!
    @IBOutlet weak var watchButton: UIButton!
    @IBOutlet weak var contentStackView: UIStackView!
    @IBOutlet weak var favoriteButton: UIButton!
    @IBOutlet weak var moviesCollectionView: MoviesCollectionView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        fetchMovieDetails()
        fetchMovieRecommendations()
    }
    
    private func fetchMovieDetails() {
        guard let movieId = movie.id else { return }
        interactor?.fetchMovieDetails(movieId)
    }
    
    private func fetchMovieRecommendations() {
        guard let movieId = movie.id else { return }
        interactor?.fetchMovieRecommendations(movieId)
    }
}

// MARK: - MovieDetailsDisplayLogic
extension MovieDetailsViewController: MovieDetailsDisplayLogic {
    
    func displayFetchedMovieRecommendations(viewModel: MovieDetailsModels.FetchMovieRecommendations.ViewModel) {
        
        let displayedMovies = viewModel.displayedMovies
        moviesCollectionView.setMovies(displayedMovies)
    }
    
    func displayFetchedMovieDetails(viewModel: MovieDetailsModels.FetchMovieDetails.ViewModel) {
        
        let displayedMovie = viewModel.displayedMovie
        
        DispatchQueue.main.async { [weak self] in
            self?.titleLabel.text = displayedMovie.title
            self?.subtitleLabel.text = displayedMovie.type
            self?.descriptionLabel.text = displayedMovie.overview
            
            if let posterPath = displayedMovie.posterPath, let url = URL(string: posterPath) {
                self?.posterImageView.kf.setImage(with: url)
            }
            
            if let backdropPath = displayedMovie.backdropPath, let url = URL(string: backdropPath) {
                self?.backgroundImageView.kf.setImage(with: url)
            }
        }
    }
}
