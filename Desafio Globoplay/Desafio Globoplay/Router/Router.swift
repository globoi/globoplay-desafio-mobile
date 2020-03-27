//
//  Router.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 26/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import Foundation

protocol Router: class {
    var children: [Router] { get set }
    func start()
}
