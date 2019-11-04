//
//  StaticFunctions.swift
//  Desafio GloboPlay
//
//  Created by Filipo Negrao on 03/11/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import Foundation
import UIKit

class StaticFunctions {
    
    static var vSpinner : UIView?
    
    // MARK: Alertas
    
    /*** Apresenta um alerta **/
    class func showSimpleCallbackAlert(controller: UIViewController, title: String!, message: String!, callback: @escaping (()->())) {
        
        let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: { (action) in
            callback()
        }))
        controller.present(alert, animated: true)
    }
    /*** Apresenta um alerta com callback **/
    class func showSimpleAlert(controller: UIViewController, title: String!, message: String!) {
        let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
        controller.present(alert, animated: true)
    }
    
    // MARK: Loading components
    
    class func showActivityIndicatorView(onView : UIView) {
        let spinnerView = UIView.init(frame: onView.bounds)
        spinnerView.backgroundColor = UIColor.init(red: 0, green: 0, blue: 0, alpha: 0.7)
        let ai = UIActivityIndicatorView.init(style: .whiteLarge)
        ai.startAnimating()
        ai.center = spinnerView.center
        
        DispatchQueue.main.async {
            spinnerView.addSubview(ai)
            onView.addSubview(spinnerView)
        }
        
        vSpinner = spinnerView
    }
    
    class func removeActivityIndicatorView() {
       DispatchQueue.main.async {
            vSpinner?.removeFromSuperview()
            vSpinner = nil
        }
    }
    
    class func getItemWidth() -> CGFloat {
        // Define a largura dos cards
         var itemWidth : CGFloat = 185
         if (screenWidth/3 > itemWidth) {
             itemWidth = screenWidth/3
         }
        return itemWidth
    }
    
    class func getItemHeight() -> CGFloat {
        // Define a largura dos cards
        var itemWidth = StaticFunctions.getItemWidth()
        return itemWidth*1.5
    }
}

