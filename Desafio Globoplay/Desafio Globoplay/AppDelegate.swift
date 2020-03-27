//
//  AppDelegate.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 17/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    var router: TabBarRouter?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        
        self.router = TabBarRouter(window: UIWindow(frame: UIScreen.main.bounds))
        self.router?.start()
        
        return true
    }

}

