//
//  ErrorMessageView.swift
//  My Movies
//
//  Created by Rafael Valer on 14/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation
import UIKit

protocol CustomMessageViewDelegate: class {
    func tryAgainTouched()
}

class CustomMessageView: UIView {
    
    @IBOutlet var containerView: UIView!
    @IBOutlet weak var messageLabel: UILabel!
    @IBOutlet weak var tryAgainButton: UIButton!

    weak var delegate: CustomMessageViewDelegate?
    private var message: String
    private var buttonTitle: String?
    
    required init(message: String, buttonTitle: String? = nil) {
        self.message = message
        self.buttonTitle = buttonTitle
        
        super.init(frame: .zero)
        initSubViews()
        setupViews()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func setupViews() {
        messageLabel.text = message
        tryAgainButton.setTitle(buttonTitle, for: .normal)
        tryAgainButton.isHidden = buttonTitle == nil
    }
    
    private func initSubViews() {
        let nib = UINib(nibName: String(describing: type(of: self)), bundle: Bundle(for: type(of: self)))
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
    
    // MARK: - Actions
    
    @IBAction func tryAgainTouched(_ sender: Any) {
        delegate?.tryAgainTouched()
    }
}
