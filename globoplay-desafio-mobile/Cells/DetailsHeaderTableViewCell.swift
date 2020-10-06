//
//  HeaderDetailsTableViewCell.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 05/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import UIKit

class DetailsHeaderTableViewCell: UITableViewCell {
    var segmentedControlValue = 0
    var isMinhaLista = false
    
    @IBOutlet weak var segmentedControlDetails  : UISegmentedControl!
    @IBOutlet weak var trailerButton            : CustomButton!
    @IBOutlet weak var myListButton             : CustomButton!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    @IBAction func segmentedControlChanged(_ sender: Any) {
        self.segmentedControlValue = segmentedControlDetails.selectedSegmentIndex
    }
    @IBAction func playTrailer(_ sender: Any) {
        print("[DEBUG] - CLICOU NO PLAY")
    }
    
    @IBAction func addToMinhaLista(_ sender: Any) {
        print("[DEBUG] - CLICOU NA MINHA LISTA")
        if (self.isMinhaLista == false){
            myListButton.setTitle("Adicionado", for: .normal)
            myListButton.setImage(UIImage(named: "check_black"), for: .normal)
            isMinhaLista = true
        }
        else{
            myListButton.setTitle("Minha Lista", for: .normal)
            myListButton.setImage(UIImage(named: "star_rate"), for: .normal)
            isMinhaLista = false
        }
        
    }
    
}
