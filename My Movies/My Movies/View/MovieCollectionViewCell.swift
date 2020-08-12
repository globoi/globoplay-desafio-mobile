//
//  MovieCollectionViewCell.swift
//  My Movies
//
//  Created by Rafael Valer on 11/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import UIKit
import Kingfisher

class MovieCollectionViewCell: UICollectionViewCell {
    
    static let identifier: String = "MovieCollectionViewCell"

    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    func render(withViewModel viewModel: HomeModels.FetchMovies.ViewModel.DisplayedMovie) {
        titleLabel.text = viewModel.title
        
        if let posterPath = viewModel.posterPath, let url = URL(string: posterPath) {
            imageView.kf.setImage(with: url)
        }
    }
    
    func render(withDisplayableMovie displayableMovie: DisplayableMovie) {
        titleLabel.text = displayableMovie.title
        
        if let posterPath = displayableMovie.posterPath, let url = URL(string: posterPath) {
            imageView.kf.setImage(with: url)
        }
    }
}
