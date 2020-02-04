//
//  CoordinatorProtocol.swift
//  TheMovieDB
//
//  Created by Edwy Lugo on 03/02/20.
//  Copyright © 2020 Edwy Lugo. All rights reserved.
//

import UIKit

protocol RootViewControllerProvider: AnyObject {
    // The coordinators 'rootViewController'. It helps to think of this as the view
    // controller that can be used to dismiss the coordinator from the view hierarchy.
    var rootViewController: UINavigationController { get }
}

/// A Coordinator type that provides a root UIViewController
typealias RootViewCoordinator = Coordinator & RootViewControllerProvider

/// The Coordinator protocol
protocol Coordinator: AnyObject {
    /// The array containing any child Coordinators
    var childCoordinators: [Coordinator] { get set }

    /// Start func
    func start()
}

extension Coordinator {
    /// Add a child coordinator to the parent
    func addChildCoordinator(_ childCoordinator: Coordinator) {
        self.childCoordinators.append(childCoordinator)
    }

    /// Remove a child coordinator from the parent
    func removeChildCoordinator(_ childCoordinator: Coordinator) {
        self.childCoordinators = self.childCoordinators.filter {
            $0 !== childCoordinator
        }
    }
}
