//
//  CardDetailViewController.swift
//  TheMovieDB
//
//  Created by Edwy Lugo on 26/02/20.
//  Copyright © 2020 Edwy Lugo. All rights reserved.
//

import UIKit

class CardDetailViewController: UIViewController {

      @IBOutlet weak var titleLabel: UILabel!
       @IBOutlet weak var genreLabel: UILabel!
       @IBOutlet weak var epiLabel: UILabel!
       @IBOutlet weak var productionLabel: UILabel!
       @IBOutlet weak var directionLabel: UILabel!
       @IBOutlet weak var teamLabel: UILabel!
       
       private var card : Card!
       
       init(card: Card) {
           super.init(nibName: "CardDetailViewController", bundle: nil)
           self.card = card
       }
       
       required init?(coder: NSCoder) {
           fatalError("init(coder:) has not been implemented")
       }
       
       @IBOutlet weak var countryLabel: UILabel!
       override func viewDidLoad() {
           super.viewDidLoad()

           self.view.backgroundColor = gray
           // Do any additional setup after loading the view.
           
           self.loadData()
       }
       
       func loadData() {
           Functions.showActivityIndicatorView(onView: self.view)
           TheMovieDBAPI.sharedInstance.getTVDetail(id: Int(self.card!.id)) { (card: Card, error: String?) in
               Functions.removeActivityIndicatorView()
               self.card = card
               self.config()
           }
       }
       
       func config() {
           self.titleLabel.text = "Título original: \(self.card.originalName)"
           self.titleLabel.minimumScaleFactor = 0.1
           self.titleLabel.adjustsFontSizeToFitWidth = true
           // Genero
           var genreString = "Gênero: "
           var i = 0;
           for genre in self.card.genres {
               if (i != 0) {
                   genreString += ", "
               }
               genreString += genre.name
               i += 1
           }
           self.genreLabel.text = self.card.getGenresString()
           self.genreLabel.numberOfLines = 2
           self.genreLabel.minimumScaleFactor = 0.1
           self.genreLabel.adjustsFontSizeToFitWidth = true
           
           self.epiLabel.text = "Episódeos: \(self.card.numberOfEpisodeos)"
           self.epiLabel.minimumScaleFactor = 0.1
           self.epiLabel.adjustsFontSizeToFitWidth = true
           
           self.productionLabel.text = "Ano de produção: \(self.card.fisrtAirDate)"
           self.productionLabel.minimumScaleFactor = 0.1
           self.productionLabel.adjustsFontSizeToFitWidth = true
           
           self.countryLabel.text = "País: \(self.card.country)"
           self.countryLabel.minimumScaleFactor = 0.1
           self.countryLabel.adjustsFontSizeToFitWidth = true
           
           self.directionLabel.text = "Direção: \(self.card.directors)"
           self.directionLabel.numberOfLines = 2
           self.directionLabel.minimumScaleFactor = 0.1
           self.directionLabel.adjustsFontSizeToFitWidth = true
       }

}
