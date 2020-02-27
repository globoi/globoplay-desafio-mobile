//
//  CardCollectionViewCell.swift
//  TheMovieDB
//
//  Created by Edwy Lugo on 26/02/20.
//  Copyright © 2020 Edwy Lugo. All rights reserved.
//

import UIKit

class CardCollectionViewCell: UICollectionViewCell {
    
    private var imageView : UIImageView!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        let margin : CGFloat = 20
        let rect = CGRect(x: margin/2, y: margin, width: frame.width-margin, height: frame.height - margin*2)
        self.imageView = UIImageView(frame: rect)
        self.imageView.clipsToBounds = true
        self.imageView.contentMode = .scaleAspectFill
        self.imageView.backgroundColor = gray
        self.addSubview(self.imageView)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func configCell(card: Card) {
        let size = Int(self.frame.width)
        let urlString = card.getPosterUrlForSize(size: size)
        if let url = URL.init(string: urlString) {
            self.imageView.load.request(with: url)
        }
    }
    
}
