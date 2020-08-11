//
//  MyListRouter.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

@objc protocol MyListRoutingLogic {
    
}

protocol MyListDataPassing {
    var dataStore: MyListDataStore? { get set }
}

class MyListRouter: NSObject, MyListRoutingLogic, MyListDataPassing {
    
    weak var viewController: MyListViewController?
    var dataStore: MyListDataStore?
    
}
