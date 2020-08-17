//
//  MovieDetailsView.swift
//  My Movies
//
//  Created by Rafael Valer on 12/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation
import UIKit

protocol DisplayableMovieDetails {
    var originalTitle: String? { get set }
    var genres: String? { get set }
    var releaseDate: String? { get set }
    var score: String? { get set }
    var productionCountries: String? { get set }
    var productionCompanies: String? { get set }
}

class MovieDetailsView: UIView {
    
    @IBOutlet var containerView: UIView!
    @IBOutlet weak var stackView: UIStackView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var originalTitleLabel: UILabel!
    @IBOutlet weak var genresLabel: UILabel!
    @IBOutlet weak var productionCountriesLabel: UILabel!
    @IBOutlet weak var productionCompaniesLabel: UILabel!
    @IBOutlet weak var releaseDateLabel: UILabel!
    @IBOutlet weak var scoreLabel: UILabel!
    
    private let identifier: String = "MovieDetailsView"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        initSubViews()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        initSubViews()
    }
    
    private func initSubViews() {
        let nib = UINib(nibName: identifier, bundle: Bundle(identifier: identifier))
        nib.instantiate(withOwner: self, options: nil)
        containerView.translatesAutoresizingMaskIntoConstraints = false
        addSubview(containerView)
        self.addConstraints()
    }
    
    private func addConstraints() {
        NSLayoutConstraint.activate([
            self.topAnchor.constraint(equalTo: containerView.topAnchor),
            self.trailingAnchor.constraint(equalTo: containerView.trailingAnchor),
            self.leadingAnchor.constraint(equalTo: containerView.leadingAnchor),
            self.bottomAnchor.constraint(equalTo: containerView.bottomAnchor)
        ])
    }
    
    func setMovieDetails(_ displayableMovie: DisplayableMovieDetails) {
        DispatchQueue.main.async { [weak self] in
            
            self?.originalTitleLabel.text = displayableMovie.originalTitle
            self?.genresLabel.text = displayableMovie.genres
            self?.releaseDateLabel.text = displayableMovie.releaseDate
            self?.scoreLabel.text = displayableMovie.score
            self?.productionCountriesLabel.text = displayableMovie.productionCountries
            self?.productionCompaniesLabel.text = displayableMovie.productionCompanies

            self?.originalTitleLabel.isHidden = displayableMovie.originalTitle == nil
            self?.genresLabel.isHidden = displayableMovie.genres == nil
            self?.releaseDateLabel.isHidden = displayableMovie.releaseDate == nil
            self?.scoreLabel.isHidden = displayableMovie.score == nil
            self?.productionCountriesLabel.isHidden = displayableMovie.productionCountries == nil
            self?.productionCompaniesLabel.isHidden = displayableMovie.productionCompanies == nil
        }
    }
}
