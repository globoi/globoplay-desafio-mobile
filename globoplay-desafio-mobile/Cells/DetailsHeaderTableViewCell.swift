//
//  HeaderDetailsTableViewCell.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 05/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import UIKit

class DetailsHeaderTableViewCell: UITableViewCell {
    
    //UITableViewHeaderFooterView
    
    var segmentedControlValue = 0
    
    @IBOutlet weak var segmentedControlDetails: UISegmentedControl!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    @IBAction func segmentedControlChanged(_ sender: Any) {
        self.segmentedControlValue = segmentedControlDetails.selectedSegmentIndex
    }
}
