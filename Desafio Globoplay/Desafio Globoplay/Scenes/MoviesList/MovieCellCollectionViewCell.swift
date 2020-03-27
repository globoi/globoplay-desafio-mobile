//
//  MovieCellCollectionViewCell.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 22/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import UIKit
import Kingfisher

protocol MovieCellProtocol: class {
    func display(withURL url: String);
}

class MovieCellCollectionViewCell: UICollectionViewCell {
    @IBOutlet weak var MoviePosterImageView: UIImageView! {
        didSet {
            self.MoviePosterImageView.contentMode = .scaleAspectFill
            self.MoviePosterImageView.clipsToBounds = true
        }
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        
        self.backgroundColor = .clear
    }
}

extension MovieCellCollectionViewCell: MovieCellProtocol {
    func display(withURL url: String) {
        if let url = URL(string: url) {
            self.MoviePosterImageView.kf.setImage(with: url)
        }
    }
}
