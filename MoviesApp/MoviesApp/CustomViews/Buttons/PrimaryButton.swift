//
//  PrimaryButton.swift
//  MoviesApp
//
//  Created by Gustavo Tiecker on 01/03/22.
//

import UIKit

class PrimaryButton: UIButton {
    
    // MARK: - Initilizers
    override init(frame: CGRect) {
        super.init(frame: frame)
        configure()
    }
     
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
     
    convenience init(backgroundColor: UIColor, textColor: UIColor, icon: UIImage?, title: String) {
        self.init(frame: .zero)
        
        self.tintColor = textColor
        self.backgroundColor = backgroundColor
        self.setTitleColor(textColor, for: .normal)
        self.layer.borderWidth = 2
        self.layer.borderColor = textColor.cgColor
        
        var configuration = UIButton.Configuration.plain()
        configuration.title = title
        configuration.image = icon
        configuration.titlePadding = 10.0
        configuration.imagePadding = 10.0
        self.configuration = configuration
    }
     
    // MARK: - Private Methods
    private func configure() {
        layer.cornerRadius = 8
        titleLabel?.font = UIFont.preferredFont(forTextStyle: .headline)
        translatesAutoresizingMaskIntoConstraints = false
    }
    
    // MARK: - Public Methods
    func setAlreadyFavorited() {
        if configuration != nil {
            configuration!.title = "Adicionado"
            configuration!.image = UIImage(systemName: "checkmark")
        }
    }
    
    func setNotFavorited() {
        if configuration != nil {
            configuration!.title = "Minha lista"
            configuration!.image = UIImage(systemName: "star.fill")
        }
    }
}
