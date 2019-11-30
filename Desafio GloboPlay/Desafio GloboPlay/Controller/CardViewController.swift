//
//  CardViewController.swift
//  Desafio GloboPlay
//
//  Created by Filipo Negrao on 04/11/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import UIKit
import WebKit

/** CONTROLLER TOTALMENTE POR CODIGO */
class CardViewController: UIViewController, UIScrollViewDelegate {
    
    private var scrollView : UIScrollView!
    private var backImageView: UIImageView!
    private var imageView: UIImageView!
    private var titleLabel: UILabel!
    private var genreLabel: UILabel!
    private var descriptionLabel: UILabel!
    private var buttonWatch: UIButton!
    private var buttonFavorite: UIButton!
    
    private var cardContentViewController : CardContentViewController!
    
    private var recommendations = [Card]()
    private var card : Card!
    
    let margin : CGFloat = 20
    
    init(card: Card) {
        super.init(nibName: nil, bundle: nil)
        self.card = card
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.navigationController?.navigationBar.backgroundColor = UIColor.clear
        
        self.configLayout()
        self.loadRecomendationsData()
        self.loadDataDetails()
    }
    
    func loadRecomendationsData() {
        ApplicationService.sharedInstance.getTVSimilars(card: self.card) { (cards: [Card], error: String?) in
            self.recommendations = cards
            self.addContent()
        }
    }
    
    func loadDataDetails() {
        if self.card.type == CardType.MOVIE.rawValue {
            ApplicationService.sharedInstance.getMovieDetail(id: Int(self.card!.id)) { (card: Card, error: String?) in
               if card.id != 0 {
                   self.card = card
               }
               self.genreLabel.text = self.card.getGenresString()
            }
        } else {
            ApplicationService.sharedInstance.getTVDetail(id: Int(self.card!.id)) { (card: Card, error: String?) in
               if card.id != 0 {
                   self.card = card
               }
               self.genreLabel.text = self.card.getGenresString()
            }
        }

     }
    
    override func viewDidAppear(_ animated: Bool) {
        let barHeight = self.tabBarController!.tabBar.frame.height
        UIView.animate(withDuration: 0.2) {
            self.tabBarController?.tabBar.frame.origin.y += barHeight
        }
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        let barHeight = self.tabBarController!.tabBar.frame.height
        UIView.animate(withDuration: 0.2) {
            self.tabBarController?.tabBar.frame.origin.y -= barHeight
        }
    }
    
    func configLayout() {
        self.scrollView = UIScrollView(frame: CGRect(x: 0, y: 0, width: screenWidth, height: screenHeight-20))
        self.scrollView.backgroundColor = UIColor.black
        self.scrollView.showsVerticalScrollIndicator = false
        self.scrollView.delegate = self
        
        // Imagem de tras
        self.backImageView = UIImageView(frame: CGRect(x: 0, y: 0, width: screenWidth, height: screenWidth))
        self.backImageView.backgroundColor = UIColor.black
        self.backImageView.clipsToBounds = true
        let urlString = self.card.getBackdropUrlForSize(size: Int(screenWidth))
        if let url = URL.init(string: urlString) {
            self.backImageView.load.request(with: url)
        }
        self.backImageView.contentMode = .scaleAspectFill
        self.scrollView.addSubview(self.backImageView)
        // Imagem de capa
        let imageHeight = self.backImageView.frame.height * 0.7
        let imageWidth = imageHeight/1.5
        self.imageView = UIImageView(frame: CGRect(x: 0, y: 30, width: imageWidth, height: imageHeight))
        self.imageView.contentMode = .scaleAspectFill
        self.imageView.center.x = self.scrollView.center.x
        let imageUrlString = self.card.getPosterUrlForSize(size: Int(300))
        if let url = URL.init(string: imageUrlString) {
            self.imageView.load.request(with: url)
        }
        self.scrollView.addSubview(self.imageView)
        
        // label de titulo
        let font =  UIFont.boldSystemFont(ofSize: 30)
        let titleHeight : CGFloat = StaticFunctions.heightForView(text: self.card.name, font: font, width: screenWidth-margin*2)
        let titleOrigin = self.imageView.frame.origin.y + imageHeight
        self.titleLabel = UILabel.init(frame: CGRect.init(x: margin, y: titleOrigin, width: screenWidth-margin*2, height: titleHeight))
        self.titleLabel.backgroundColor = UIColor.clear
        self.titleLabel.text = self.card.name
        self.titleLabel.textColor = UIColor.white
        self.titleLabel.font = font
        self.titleLabel.textAlignment = .center
        self.titleLabel.numberOfLines = 2
        self.titleLabel.adjustsFontSizeToFitWidth = true
        self.titleLabel.minimumScaleFactor = 0.1
        self.scrollView.addSubview(self.titleLabel)
        
        self.addBlur()
        self.addGradient()
        
        // label de genero
        let genreHeight : CGFloat = 44
        let genreOrigin = self.titleLabel.frame.origin.y + titleHeight
        self.genreLabel = UILabel.init(frame: CGRect.init(x: margin, y: genreOrigin, width: screenWidth-margin*2, height: genreHeight))
        self.genreLabel.backgroundColor = UIColor.clear
        self.genreLabel.text = self.card.getGenresString()
        self.genreLabel.textColor = UIColor.lightText
        self.genreLabel.textAlignment = .center
        self.genreLabel.minimumScaleFactor = 0.1
        self.genreLabel.adjustsFontSizeToFitWidth = true
        self.genreLabel.numberOfLines = 2
        self.scrollView.addSubview(self.genreLabel)
        
        // label de descricao
        let fontDescription = UIFont.systemFont(ofSize: 16)
        let descriptionHeight : CGFloat = StaticFunctions.heightForView(text: self.card.overview, font: fontDescription, width: screenWidth-margin*2)
        let descriptionOrigin = self.genreLabel.frame.origin.y + genreHeight
        self.descriptionLabel = UILabel.init(frame: CGRect.init(x: margin, y: descriptionOrigin, width: screenWidth-margin*2, height: descriptionHeight))
        self.descriptionLabel.backgroundColor = UIColor.clear
        self.descriptionLabel.text = self.card.overview
        self.descriptionLabel.numberOfLines = 0
        self.descriptionLabel.textColor = UIColor.white
        self.descriptionLabel.font = fontDescription
        self.scrollView.addSubview(self.descriptionLabel)
        
        // botao de watch
        let buttonOrigin = self.descriptionLabel.frame.origin.y + self.descriptionLabel.frame.height
        let buttonWidth = screenWidth/2 - margin*2
        let buttonHeight = buttonWidth/2.2
        self.buttonWatch = UIButton(frame: CGRect(x: margin, y: buttonOrigin, width: buttonWidth, height: buttonHeight))
        self.buttonWatch.backgroundColor = UIColor.white
        self.buttonWatch.setTitle("Assistir", for: .normal)
        self.buttonWatch.setTitleColor(UIColor.black, for: .normal)
        self.buttonWatch.layer.cornerRadius = 4
        self.buttonWatch.setImage(UIImage.init(named: "play"), for: .normal)
        self.buttonWatch.addTarget(self, action: #selector(self.openSite), for: .touchUpInside)
        self.scrollView.addSubview(self.buttonWatch)
        // botao de favoritos
        self.buttonFavorite = UIButton(frame: CGRect(x: screenWidth/2+margin, y: buttonOrigin, width: buttonWidth, height: buttonHeight))
        self.buttonFavorite.backgroundColor = UIColor.white
        self.buttonFavorite.setTitle("Minha lista", for: .normal)
        self.buttonFavorite.addTarget(self, action: #selector(self.addFavorite), for: .touchUpInside)
        self.buttonFavorite.setTitleColor(UIColor.black, for: .normal)
        self.buttonFavorite.layer.cornerRadius = 4
        self.buttonFavorite.setImage(UIImage.init(named: "star"), for: .normal)
        self.scrollView.addSubview(self.buttonFavorite)
        self.checkAlreadyFavorited()
        
        self.scrollView.contentSize.height = buttonFavorite.frame.origin.y + buttonFavorite.frame.height + margin*2
        
        self.view.addSubview(self.scrollView)
    }
    
    func checkAlreadyFavorited() {
        // Se ja foi adicionado
        if (ApplicationService.sharedInstance.checkAlreadyAdded(card: self.card)) {
            self.buttonFavorite.backgroundColor = UIColor.black
            self.buttonFavorite.setTitleColor(UIColor.white, for: .normal)
            self.buttonFavorite.setTitle("Adicionado", for: .normal)
            let origImage = UIImage(named: "check")
            let tintedImage = origImage?.withRenderingMode(.alwaysTemplate)
            self.buttonFavorite.setImage(tintedImage, for: .normal)
            self.buttonFavorite.tintColor = UIColor.white
        } else {
            self.buttonFavorite.backgroundColor = UIColor.white
            self.buttonFavorite.setTitleColor(UIColor.black, for: .normal)
            self.buttonFavorite.setTitle("Minha lista", for: .normal)
            self.buttonFavorite.setImage(UIImage.init(named: "star"), for: .normal)
            self.buttonFavorite.tintColor = UIColor.white
        }
    }
    
    func addContent() {
        // Bara de recomendacoes e detalhes
        let cardOrigin = self.buttonFavorite.frame.origin.y + self.buttonFavorite.frame.height + margin
        self.cardContentViewController = CardContentViewController.init(card: self.card, recommendations: self.recommendations)
        self.cardContentViewController.view.frame = CGRect(x: 0, y: cardOrigin, width: screenWidth, height: screenWidth)
        self.cardContentViewController.view.reloadInputViews()
        let cardContentView = self.cardContentViewController.view.frame
        self.scrollView.addSubview(self.cardContentViewController.view)
        
        self.scrollView.contentSize.height = cardContentView.origin.y + cardContentView.height + 300
    }
    
    func addBlur() {
        let blurEffect = UIBlurEffect(style: UIBlurEffect.Style.dark)
        let blurEffectView = UIVisualEffectView(effect: blurEffect)
        blurEffectView.frame = backImageView.bounds
        blurEffectView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        blurEffectView.alpha = 0.7
        self.backImageView.addSubview(blurEffectView)
    }
    
    func addGradient() {
        let gradient: CAGradientLayer = CAGradientLayer()
        gradient.colors = [UIColor.black.cgColor, UIColor.clear.cgColor]
        gradient.locations = [0.0 , 1.0]
        gradient.startPoint = CGPoint(x: 1.0, y: 1.0)
        gradient.endPoint = CGPoint(x: 1.0, y: 0.0)
        gradient.frame = CGRect(x: 0.0, y: 0.0, width: self.backImageView.frame.size.width, height: self.backImageView.frame.size.height)
        self.backImageView.layer.insertSublayer(gradient, at: 0)
    }
    
    @objc func addFavorite() {
        if (ApplicationService.sharedInstance.checkAlreadyAdded(card: self.card)) {
            let message = "Tem certeza que deseja remover este conteúdo dos seus favoritos?"
            StaticFunctions.showChoiceCallbackAlert(controller: self, title: "Tem certeza?", message: message) {
                ApplicationService.sharedInstance.removeFavorite(card: self.card)
                self.checkAlreadyFavorited()
            }
        } else {
            ApplicationService.sharedInstance.addFavorite(card: self.card)
            self.checkAlreadyFavorited()
        }
    }
    
    @objc func openSite() {
        let urlString = self.card.homePage
        let controller = WebViewViewController(urlString: urlString)
        self.present(controller, animated: true) {
            
        }
    }
    
    // MARK: Scroll view delegates
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        let offset = scrollView.contentOffset.y
        if offset < 200 {
            let value = 1 - (offset/200)
            self.imageView.alpha = value
            self.backImageView.alpha = value
        }
    }
    
}

