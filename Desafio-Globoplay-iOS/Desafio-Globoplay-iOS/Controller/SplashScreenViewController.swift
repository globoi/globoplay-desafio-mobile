//
//  SplashScreenViewController.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 02/03/22.
//

import UIKit

class SplashScreenViewController: UIViewController {
    
    private let globoLogo: UIImageView = {
        let image = UIImageView(frame: CGRect(x: 0, y: 0, width: 240, height: 128))
        image.image = UIImage(named: "globoplay-text-logo")
        image.contentMode = .scaleAspectFit
        image.clipsToBounds = true
        image.backgroundColor = .black
        return image
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        globoLogo.center = view.center
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
            self.animate()
        }
    }
    
    func configureUI(){
        view.backgroundColor = .black
        view.addSubview(globoLogo)
    }
    
    private func animate(){
        UIView.animate(withDuration: 1.5, animations: {
            let size = self.view.frame.size.width * 8
            let diffX = size - self.view.frame.size.width
            let diffY = self.view.frame.size.height - size
            
            self.globoLogo.frame = CGRect(
                x: -(diffX / 2), y: diffY / 2,
                width: size, height: size)
        })
        
        UIView.animate(withDuration: 1.5, animations: {
            self.globoLogo.alpha = 0
        }) { done in
            if done {
                let viewController = MainTabController()
                viewController.modalTransitionStyle = .crossDissolve
                viewController.modalPresentationStyle = .fullScreen
                self.present(viewController, animated: true)
            }
        }
    }

}
