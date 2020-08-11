//
//  MyListViewController.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import UIKit

protocol MyListDisplayLogic: class {
    
}

class MyListViewController: UIViewController {
    
    static let identifier: String = "MyListViewController"
    
    var interactor: MyListBusinessLogic?
    var router: (NSObject & MyListRoutingLogic & MyListDataPassing)?
    
    // MARK: Object lifecycle
    
    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
        setup()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setup()
    }
    
    // MARK: Setup
    
    private func setup() {
        let viewController = self
        let interactor = MyListInteractor()
        let presenter = MyListPresenter()
        let router = MyListRouter()
        viewController.interactor = interactor
        viewController.router = router
        interactor.presenter = presenter
        presenter.viewController = viewController
        router.viewController = viewController
        router.dataStore = interactor
    }
    
    // MARK: View lifecycle

    override func viewDidLoad() {
        super.viewDidLoad()
    }
}

// MARK: - MyListViewController
extension MyListViewController: MyListDisplayLogic {
    
}
