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
    var isMinhaLista: Bool?
    
    @IBOutlet weak var segmentedControlDetails  : UISegmentedControl!
    @IBOutlet weak var trailerButton            : CustomButton!
    @IBOutlet weak var myListButton             : CustomButton!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        let colorNormal = [NSAttributedString.Key.foregroundColor: UIColor.white]
        let colorSelected = [NSAttributedString.Key.foregroundColor: UIColor.black]
        segmentedControlDetails.setTitleTextAttributes(colorNormal, for: .normal)
        segmentedControlDetails.setTitleTextAttributes(colorSelected, for: .selected)
        
    }
    override func layoutSubviews() {
        setupButton()
    }

    func setupButton(){
        if (self.isMinhaLista == true){
            myListButton.setTitle("Adicionado", for: .normal)
            myListButton.setImage(UIImage(named: "check_black"), for: .normal)
        }
        else if (self.isMinhaLista == false){
            myListButton.setTitle("Minha Lista", for: .normal)
            myListButton.setImage(UIImage(named: "star_rate"), for: .normal)
        }
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
