//
//  Resource.swift
//  globoplay
//
//  Created by Marcos Curvello on 17/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation
import Combine
import TinyNetworking

final class Resource<A>: ObservableObject {
    @Published var value: A? = nil
    var objectWillChange: AnyPublisher<A?, Never> = Publishers.Sequence<[A?], Never>(sequence: []).eraseToAnyPublisher()
    let endpoint: Endpoint<A>
    private var firstLoad = true
    
    init(endpoint: Endpoint<A>) {
        self.endpoint = endpoint
        self.objectWillChange = $value.handleEvents(receiveSubscription: { [weak self] sub in
            guard let s = self, s.firstLoad else { return }
            s.firstLoad = false
            s.reload()

        }).eraseToAnyPublisher()
    }
    
    func reload() {
        URLSession.shared.load(endpoint) { result in
            DispatchQueue.main.async {
                self.value = try? result.get()
            }
        }
    }
}
