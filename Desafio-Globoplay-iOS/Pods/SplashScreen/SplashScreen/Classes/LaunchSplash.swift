//
//  LaunchPad.swift
//  SplashScreen
//
//  Created by Marcel Mendes Filho on 05/09/19.
//

import Foundation

public class LaunchSplash {
    
    public static func showSplashScreen(caller: UIViewController,
                                        app: String,
                                        version: String,
                                        key: String,
                                        localization: String,
                                        hostName: String,
                                        splashEndpoint: String,
                                        httpHeader: String,
                                        imageHostName: String,
                                        imagePath: String){
        
        let podBundle = Bundle(for: SplashViewController.self)
        let bundleURL = podBundle.url(forResource: "SplashScreen", withExtension: "bundle")
        let bundle = Bundle(url: bundleURL!)!
        let storyboard = UIStoryboard(name: "Main", bundle: bundle)
        
        let vc = storyboard.instantiateViewController(withIdentifier: "SplashViewController") as! SplashViewController
        vc.app = app
        vc.version = version
        vc.key = key
        vc.localization = localization
        vc.hostName = hostName
        vc.retrieveSplashEndpoint = splashEndpoint
        vc.serverHTTPHeader = httpHeader
        vc.imageHostName = imageHostName
        vc.imagePath = imagePath
        
        if #available(iOS 13, *){
            vc.modalPresentationStyle = .fullScreen
        }
        
        caller.present(vc, animated: true, completion: nil)
    }
}
