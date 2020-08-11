//
//  HomeInteractor.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

protocol HomeBusinessLogic {

}

protocol HomeDataStore {
    
}

class HomeInteractor: HomeBusinessLogic, HomeDataStore {

    var presenter: HomePresentationLogic?
    var worker: HomeWorker = HomeWorker()
    
}
