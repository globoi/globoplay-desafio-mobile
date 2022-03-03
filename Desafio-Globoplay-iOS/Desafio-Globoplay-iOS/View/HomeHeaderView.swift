//
//  HomeHeaderView.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 25/02/22.
//

import UIKit

class HomeHeaderView: UIView {
    
    // MARK: - Properties
    
    private let headerImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = .scaleAspectFill
        imageView.clipsToBounds = true
        imageView.image = UIImage(named: "films-banner-globoplay")
        return imageView
    }()
    
    private lazy var assinarButton: UIButton = {
        let button = UIButton(type: .system)
        button.setTitle("Assinar o plano", for: .normal)
        button.titleLabel?.font = UIFont.boldSystemFont(ofSize: 14)
        button.backgroundColor = .customWhite
        button.tintColor = .black
        button.layer.borderColor = UIColor.black.cgColor
        button.layer.borderWidth = 1
        button.setDimensions(width: 150, height: 32)
        button.layer.cornerRadius = 5
        button.addTarget(self, action: #selector(handleAssinarButton), for: .touchUpInside)
        return button
    }()
    
    // MARK: - Lifecycle
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        configureUI()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        headerImageView.frame = bounds
    }
    
    // MARK: - Helper Methods
    
    func configureUI() {
        addSubview(headerImageView)
        addGradient()
        addSubview(assinarButton)
        assinarButton.anchor(leading: leadingAnchor, bottom: bottomAnchor,
                             paddingLeading: (frame.width / 2) - 75, paddingBottom: 50)
        bringSubviewToFront(assinarButton)
    }
    
    private func addGradient() {
        let gradientLayer = CAGradientLayer()
        gradientLayer.colors = [UIColor.clear.cgColor, UIColor.black.cgColor]
        gradientLayer.frame = bounds
        layer.addSublayer(gradientLayer)
    }
        
    // MARK: - Selectors
    
    @objc func handleAssinarButton() {
        AlertUtils.showAlert(message: "Por favor, acesse o site do Globoplay para assinar o streaming.")
    }
    
}
