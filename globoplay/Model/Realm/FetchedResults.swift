//
//  FetchedResults.swift
//  globoplay
//
//  Created by Marcos Curvello on 20/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation
import RealmSwift
import Combine

public final class FetchedResults<T: Persistable> {
    typealias Result = Results<T.ManagedObject>
    @Published var value: Result? = nil
    
    let container = try! Container()
    var objectWillChange: AnyPublisher<Result?, Never> = Publishers.Sequence<[Result?], Never>(sequence: []).eraseToAnyPublisher()
    
    init(results: Results<T.ManagedObject>) {
        self.value = results
        self.objectWillChange = $value.handleEvents(receiveSubscription: { [weak self] sub in
            guard let s = self else { return }
            s.fetch()
        }).eraseToAnyPublisher()
    }
    
    func fetch() {
        self.value = container.values(T.self).value
    }
    
}
