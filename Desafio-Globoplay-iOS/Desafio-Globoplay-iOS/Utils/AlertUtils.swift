//
//  AlertUtils.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 23/02/22.
//

import UIKit

class AlertUtils {
    class func showAlert(message: String, description: String? = nil, attributedDescription: NSMutableAttributedString? = nil, viewController: UIViewController? = nil, completionHandler: (() -> ())? = nil) {
        let alert = UIAlertController(title: message, message: description, preferredStyle: .alert)
        
        if attributedDescription != nil {
            alert.setValue(attributedDescription, forKey: "attributedMessage")
        }
        
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: { (_) in
            if completionHandler == nil {
                return
            }
            completionHandler!()
        }))
        
        if viewController != nil {
            viewController?.present(alert, animated: true, completion: nil)
        }
        else {
            UIApplication.topViewController()!.present(alert, animated: true, completion: nil)
        }
    }
}
