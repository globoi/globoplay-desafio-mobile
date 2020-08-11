//
//  MyListInteractor.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

protocol MyListBusinessLogic {

}

protocol MyListDataStore {
    
}

class MyListInteractor: MyListBusinessLogic, MyListDataStore {

    var presenter: MyListPresentationLogic?
    var worker: MyListWorker = MyListWorker()
    
}
