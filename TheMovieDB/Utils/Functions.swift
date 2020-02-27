//
//  Functions.swift
//  TheMovieDB
//
//  Created by Edwy Lugo on 26/02/20.
//  Copyright © 2020 Edwy Lugo. All rights reserved.
//

import Foundation
import UIKit

class Functions {
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
        
        /*** Apresenta um alerta **/
        class func showChoiceCallbackAlert(controller: UIViewController, title: String!, message: String!, callback: @escaping (()->())) {
            let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
            alert.addAction(UIAlertAction(title: "Sim", style: .default, handler: { (action) in
                callback()
            }))
            alert.addAction(UIAlertAction(title: "Não", style: .cancel, handler: { (action) in
                alert.dismiss(animated: true, completion: nil)
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
            self.vSpinner?.removeFromSuperview()
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
            let itemWidth = Functions.getItemWidth()
            return itemWidth*1.5
        }
        
        class func getBluredImage(image: UIImage) -> UIImage {
            let context = CIContext(options: nil)

            let currentFilter = CIFilter(name: "CIGaussianBlur")
            let beginImage = CIImage(image: image)
            currentFilter!.setValue(beginImage, forKey: kCIInputImageKey)
            currentFilter!.setValue(10, forKey: kCIInputRadiusKey)

            let cropFilter = CIFilter(name: "CICrop")
            cropFilter!.setValue(currentFilter!.outputImage, forKey: kCIInputImageKey)
            cropFilter!.setValue(CIVector(cgRect: beginImage!.extent), forKey: "inputRectangle")

            let output = cropFilter!.outputImage
            let cgimg = context.createCGImage(output!, from: output!.extent)
            let processedImage = UIImage(cgImage: cgimg!)

            return processedImage
        }
        
        class func heightForView(text: String, font: UIFont, width: CGFloat) -> CGFloat {
            let label = UILabel(frame: CGRect(x: 0, y: 0, width: width, height: screenWidth))
            label.font = font
            label.text = text
            label.numberOfLines = 0
            
            label.sizeToFit()
            return label.frame.height + 44
        }
    }


