//
//  CustomButton.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 06/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import UIKit

@IBDesignable
class CustomButton: UIButton {
    
//    @IBOutlet var imgView: UIImageView!
    //@IBOutlet var titulo: UILabel!
    
    // MARK: - Properties
    
//    @IBInspectable
//    var label: String {
//        get {
//            return titulo.text ?? "Titulo"
//        }
//        set {
//            titulo.text = newValue
//        }
//    }

    @IBInspectable
    var cornerRadius: CGFloat {
        get {
            return layer.cornerRadius
        }
        set {
            layer.cornerRadius = newValue
        }
    }
    
    @IBInspectable
    var borderWidth: CGFloat {
        get {
            return layer.borderWidth
        }
        set {
            layer.borderWidth = newValue
        }
    }
    
    @IBInspectable
    var borderColor: UIColor? {
        get {
            if let color = layer.borderColor {
                return UIColor(cgColor: color)
            }
            return nil
        }
        set {
            if let color = newValue {
                layer.borderColor = color.cgColor
            } else {
                layer.borderColor = nil
            }
        }
    }
    
    
    // MARK: - Initializers
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        configure()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        
        configure()
    }

    override var isSelected: Bool {
        didSet {
            layer.backgroundColor = backgroundColor?.cgColor
        }
    }

    private var backgroundColorChange: UIColor {
        return isSelected ? .clear : .white
    }
    
    private func configure() {
        
        let selectedColor = UIColor.clear
        let bundle = Bundle(for: CustomButton.self)
        let checkIcon = UIImage(named: "arrow_black", in: bundle, compatibleWith: nil)
        setImage(checkIcon, for: .selected)
        setTitleColor(.white, for: .selected)

        
        addGestureRecognizer(
            UITapGestureRecognizer(target: self, action: #selector(didTap(recognizer:)))
        )
    }
    
    // MARK: - Actions
    
    @objc
    private func didTap(recognizer: UITapGestureRecognizer) {
        let location = recognizer.location(in: self)
    }
    
}
