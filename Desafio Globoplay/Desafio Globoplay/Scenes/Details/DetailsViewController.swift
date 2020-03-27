//
//  DetailsViewController.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 27/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import UIKit

protocol DetailsViewProtocol: class {
    func display(movie: Movie)
    func bookmarked()
}

class DetailsViewController: UIViewController, DetailsViewProtocol {
    @IBOutlet weak var watchButton: UIButton!
    @IBOutlet weak var bookmarkButton: UIButton!
    @IBOutlet weak var descriptionTextView: UITextView!
    @IBOutlet weak var titleLabe: UILabel!
    @IBOutlet weak var thumbImageView: UIImageView!
    @IBOutlet weak var bgImageView: UIImageView!
    @IBOutlet weak var gradientView: UIView!
    
    var presenter: DetailsPresenterProtocol!
    private var movie: Movie!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setUpViews()
        self.presenter.getMovie()
    }
    
    func setUpViews() {
        self.watchButton.layer.cornerRadius = 5
        self.bookmarkButton.layer.borderColor = UIColor.white.cgColor
        self.bookmarkButton.layer.borderWidth = 2
        self.bookmarkButton.layer.cornerRadius = 5
        
        self.gradientView.gradient(colors: [UIColor.clear.cgColor, UIColor.black.cgColor])
        self.titleLabe.layer.zPosition = 1
        self.thumbImageView.layer.zPosition = 1
        self.gradientView.layer.zPosition = -1
        self.bgImageView.layer.zPosition = -2
        self.bgImageView.contentMode = .scaleAspectFill
        
        self.set(bookmarked: self.presenter.isMovieBookmarked())
    }
    
    @IBAction func bookmarkAction(_ sender: Any) {
        self.presenter.bookmark(movie: self.movie)
    }
    
    func set(bookmarked: Bool) {
        if bookmarked {
            self.bookmarkButton.setTitle("Adicionado", for: .normal)
            self.bookmarkButton.setImage(UIImage(named: "checkmark"), for: .normal)
            
        } else {
            self.bookmarkButton.setTitle("Minha Lista", for: .normal)
            self.bookmarkButton.setImage(UIImage(named: "star"), for: .normal)
            
        }
    }
    
    func bookmarked() {
        self.set(bookmarked: true)
    }
    
    func display(movie: Movie) {
        self.movie = movie
        
        if let url = URL(string: self.movie.smallPictureUrl) {
            self.thumbImageView.kf.setImage(with: url)
        }
        
        if let url = URL(string: self.movie.smallBackgroundUrl) {
            self.bgImageView.kf.setImage(with: url)
        }
        
        self.descriptionTextView.text = self.movie.overview
        self.titleLabe.text = self.movie.title
    }
}
