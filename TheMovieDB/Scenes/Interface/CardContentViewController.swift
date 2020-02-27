//
//  CardContentViewController.swift
//  TheMovieDB
//
//  Created by Edwy Lugo on 26/02/20.
//  Copyright © 2020 Edwy Lugo. All rights reserved.
//

import UIKit
import SwipeMenuViewController

class CardContentViewController: UIViewController {

     private var swipeMenuView: SwipeMenuView!
        private var card: Card!
        private var recommendations: [Card]!
        
        init(card: Card, recommendations: [Card]) {
            super.init(nibName: nil, bundle: nil)
            self.card = card
            self.recommendations = recommendations
        }
        
        required init?(coder: NSCoder) {
            fatalError("init(coder:) has not been implemented")
        }
        
        override func viewDidLoad() {
            super.viewDidLoad()
            
            self.swipeMenuView = SwipeMenuView(frame: CGRect(x: 0, y: 0, width: self.view.frame.width, height: self.view.frame.height))
            self.swipeMenuView.dataSource = self
            self.swipeMenuView.delegate = self
    //        self.swipeMenuView.tabView?.tintColor = UIColor.white
            self.view.addSubview(self.swipeMenuView)

            var options: SwipeMenuViewOptions = SwipeMenuViewOptions()
            options.tabView.itemView.width = screenWidth/2
            options.tabView.itemView.margin = 20
            options.tabView.itemView.selectedTextColor = UIColor.white
            options.tabView.needsAdjustItemViewWidth = true
            options.tabView.style = .segmented
            options.tabView.additionView.underline.height = 2
            options.tabView.additionView.backgroundColor = UIColor.white
            self.swipeMenuView.reloadData(options: options)
        }


    }

    extension CardContentViewController: SwipeMenuViewDelegate {

        // MARK - SwipeMenuViewDelegate
        func swipeMenuView(_ swipeMenuView: SwipeMenuView, viewWillSetupAt currentIndex: Int) {
            // Codes

        }

        func swipeMenuView(_ swipeMenuView: SwipeMenuView, viewDidSetupAt currentIndex: Int) {
            // Codes
        }

        func swipeMenuView(_ swipeMenuView: SwipeMenuView, willChangeIndexFrom fromIndex: Int, to toIndex: Int) {
            // Codes
        }

        func swipeMenuView(_ swipeMenuView: SwipeMenuView, didChangeIndexFrom fromIndex: Int, to toIndex: Int) {
            // Codes
        }
    }

    extension CardContentViewController: SwipeMenuViewDataSource {

        //MARK - SwipeMenuViewDataSource
        func numberOfPages(in swipeMenuView: SwipeMenuView) -> Int {
            return 2
        }
            
        func swipeMenuView(_ swipeMenuView: SwipeMenuView, titleForPageAt index: Int) -> String {
            switch index {
            case 0:
                return "ASSISTA TAMBÉM"
            case 1:
                return "DETALHES"
            default:
                return ""
            }
        }
        
        func swipeMenuView(_ swipeMenuView: SwipeMenuView, viewControllerForPageAt index: Int) -> UIViewController {
            var controller : UIViewController!
            switch index {
            case 0:
                controller = RecommendationsViewController.init(controller: self, recommendations: self.recommendations)
            case 1:
                controller = CardDetailViewController.init(card: self.card)
            default:
                break
            }
            return controller
        }

}
