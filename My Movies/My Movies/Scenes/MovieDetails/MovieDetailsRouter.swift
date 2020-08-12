//
//  MovieDetailsRouter.swift
//  My Movies
//
//  Created by Rafael Valer on 11/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

@objc protocol MovieDetailsRoutingLogic {
    
}

protocol MovieDetailsDataPassing {
    var dataStore: MovieDetailsDataStore? { get set }
}

class MovieDetailsRouter: NSObject, MovieDetailsRoutingLogic, MovieDetailsDataPassing {
    
    weak var viewController: MovieDetailsViewController?
    var dataStore: MovieDetailsDataStore?
    
}
