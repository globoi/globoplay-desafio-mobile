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
}

class MovieDetailsView: UIView {
    
    @IBOutlet var containerView: UIView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var originalTitleLabel: UILabel!
    @IBOutlet weak var genresLabel: UILabel!
    @IBOutlet weak var productionCountriesLabel: UILabel!
    @IBOutlet weak var releaseDateLabel: UILabel!
    @IBOutlet weak var scoreLabel: UILabel!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        initSubViews()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        initSubViews()
    }
    
    private func initSubViews() {
        let nib = UINib(nibName: String(describing: type(of: self)), bundle: Bundle(for: type(of: self)))
        nib.instantiate(withOwner: self, options: nil)
        containerView.translatesAutoresizingMaskIntoConstraints = false
//        containerView.backgroundColor = .clear
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
        }
    }
}
