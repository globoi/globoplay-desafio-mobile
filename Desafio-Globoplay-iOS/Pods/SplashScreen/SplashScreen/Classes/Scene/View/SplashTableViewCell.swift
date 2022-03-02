//
//  SplashTableViewCell.swift
//  SmartMic
//
//  Created by Marcel Mendes Filho on 09/08/19.
//  Copyright © 2019 Marcel Mendes Filho. All rights reserved.
//

import UIKit

class SplashTableViewCell: UITableViewCell {

    @IBOutlet weak var icon: UIImageView!
    @IBOutlet weak var title: UILabel!
    @IBOutlet weak var detail: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
