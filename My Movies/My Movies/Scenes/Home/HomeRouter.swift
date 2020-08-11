//
//  HomeRouter.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

@objc protocol HomeRoutingLogic {
    
}

protocol HomeDataPassing {
    var dataStore: HomeDataStore? { get set }
}

class HomeRouter: NSObject, HomeRoutingLogic, HomeDataPassing {
    
    weak var viewController: HomeViewController?
    var dataStore: HomeDataStore?
    
}
