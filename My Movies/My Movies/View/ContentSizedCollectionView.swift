//
//  ContentSizedCollectionView.swift
//  My Movies
//
//  Created by Rafael Valer on 13/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation
import UIKit

final class ContentSizedCollectionView: UICollectionView {
    override var contentSize:CGSize {
        didSet {
            invalidateIntrinsicContentSize()
        }
    }

    override var intrinsicContentSize: CGSize {
        layoutIfNeeded()
        return CGSize(width: UIView.noIntrinsicMetric, height: contentSize.height)
    }
}
