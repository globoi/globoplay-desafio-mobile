//
//  DataLoadingViewController.swift
//  MoviesApp
//
//  Created by Gustavo Tiecker on 01/03/22.
//

import UIKit

class DataLoadingViewController: UIViewController {
    
    var containerView: UIView!

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    func showLoadingView() {
        containerView = UIView(frame: view.bounds)
        view.addSubview(containerView)
        
        containerView.backgroundColor = .systemBackground
        containerView.alpha = 0
        
        UIView.animate(withDuration: 0.25) { self.containerView.alpha = 0.8 }
        
        let activityIndicator = UIActivityIndicatorView(style: .large)
        containerView.addSubview(activityIndicator)
        
        activityIndicator.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            activityIndicator.centerYAnchor.constraint(equalTo: containerView.centerYAnchor),
            activityIndicator.centerXAnchor.constraint(equalTo: containerView.centerXAnchor)
        ])
        
        activityIndicator.startAnimating()
    }
    
    func dismissLoadingView() {
        DispatchQueue.main.async {
            self.containerView.removeFromSuperview()
            self.containerView = nil
        }
    }
    
    func presentSuccessAlert(message: String) {
        DispatchQueue.main.async {
            let alertController = UIAlertController(title: "Sucesso", message: message, preferredStyle: .alert)
                    
            let OKAction = UIAlertAction(title: "OK", style: .default) { (action:UIAlertAction!) in
                alertController.dismiss(animated: true, completion: nil)
            }
            
            alertController.addAction(OKAction)
            self.present(alertController, animated: true, completion:nil)
        }
    }
}
