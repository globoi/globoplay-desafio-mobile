//
//  UINavigationController+MinimalBackAppearance.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 27/07/23.
//

import Foundation
import UIKit

extension UINavigationController {

  open override func viewWillLayoutSubviews() {
    super.viewWillLayoutSubviews()
    navigationBar.topItem?.backButtonDisplayMode = .minimal
  }

}
