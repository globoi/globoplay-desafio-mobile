//
//  Resource.swift
//  globoplay
//
//  Created by Marcos Curvello on 16/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation
import TinyNetworking

final class Resource<A>: ObservableObject {
    @Published var value: A? = nil
    let endpoint: Endpoint<A>
    private var firstLoad = true
    
    init(endpoint: Endpoint<A>) {
        self.endpoint = endpoint
        guard self.firstLoad else { return }
        self.firstLoad = false
        self.reload()
    }
    
    func reload() {
        URLSession.shared.load(endpoint) { result in
            print("Endpoint: \(self.endpoint.request)\nContent: \(result)")
            DispatchQueue.main.async {
                self.value = try? result.get()
            }
        }
    }

}
