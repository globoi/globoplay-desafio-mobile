//
//  VideoCollectionViewCell.swift
//  Movs
//
//  Created by gmmoraes on 25/12/19.
//  Copyright © 2019 gmmoraes. All rights reserved.
//

import Foundation
import UIKit

struct VideoThumbnailRequestInfo {
    let section: Int
    let id: Int
    let url: URL
}

class VideoCollectionViewCell: UICollectionViewCell {
    
    @IBOutlet weak var imageView: UIImageView!
    var title = UILabel()
    var overview: String?
    
    var thumbnailRequestInfo: VideoThumbnailRequestInfo? {
        didSet {
            guard let requestInfo = thumbnailRequestInfo
            else { return }
            
            let currentRequestId = requestInfo.id
            ImageCache.shared.loadVideoThumbnail(thumbnailRequestInfo: requestInfo){ image in
                if (currentRequestId == (self.thumbnailRequestInfo?.id ?? -1)) {
                    DispatchQueue.main.async {
                        self.imageView.image = image
                    }
                }
            }
        }
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        configureImages()
        title.alpha = 0.0

    }
    
    func configureImages(){
        imageView.contentMode = .scaleAspectFill
        self.contentView.addSubview(imageView)
    }
}
