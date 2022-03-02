//
//  MoviePreviewController.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 27/02/22.
//

import UIKit
import WebKit

class MoviePreviewController: UIViewController {
    
    // MARK: - Properties
        
    private let webView: WKWebView = {
        let webView =  WKWebView()
        return webView
    }()
    
    private let movieTitleLabel: UILabel = {
        let label = UILabel()
        label.text = "Erro ao carregar o vídeo. Por favor, tente novamente."
        label.textColor = .customWhite
        label.font = UIFont.boldSystemFont(ofSize: 20)
        return label
    }()
    
    private let descriptionLabel: UILabel = {
        let label = UILabel()
        label.numberOfLines = 0
        label.lineBreakMode = .byWordWrapping
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
    
    // MARK: - Lifecycle
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
    }
    
    // MARK: - API
    
    // MARK: - Helper Methods
    
    func configureUI() {
        view.stopActivityView()
        view.addSubview(webView)
        webView.anchor(top: view.topAnchor, leading: view.leadingAnchor, trailling: view.trailingAnchor,
                       paddingTop: 12, paddingLeading: 12, paddingTrailling: 12, height: 300)
        
        let stack = UIStackView(arrangedSubviews: [movieTitleLabel, descriptionLabel, myListButton])
        stack.alignment = .leading
        stack.axis = .vertical
        stack.distribution = .fillProportionally
        stack.spacing = 8
        
        view.addSubview(stack)
        stack.anchor(top: webView.bottomAnchor, leading: view.layoutMarginsGuide.leadingAnchor, trailling: view.layoutMarginsGuide.trailingAnchor,
                     paddingTop: 12, paddingLeading: 12, paddingBottom: 12, paddingTrailling: 12)
    }
    
    func configurePreview(with model: MoviePreviewViewModel){
        guard let videoID = model.youtubeView.id?.videoID else { return }
        guard let videoUrl = URL(string: "https://www.youtube.com/embed/\(videoID)") else { return }
        movieTitleLabel.text = model.movieTitleText
        descriptionLabel.text = model.movieDescriptionText
        webView.load(URLRequest(url: videoUrl))
    }
    
    // MARK: - Selectors
    
    @objc func handleMyListButton() {
        AlertUtils.showAlert(message: "Conteúdo adicionado a sua lista de filmes.")
    }
}
