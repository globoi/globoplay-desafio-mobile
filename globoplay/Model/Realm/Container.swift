//
//  Container.swift
//  globoplay
//
//  Created by Marcos Curvello on 20/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation
import RealmSwift

public final class Container {
    private let realm: Realm
    
    public convenience init() throws {
        try self.init(realm: Realm())
    }
    
    internal init(realm: Realm) {
        self.realm = realm
    }
    
    public func write(_ block: (WriteTransaction) throws -> ()) throws {
        let transaction = WriteTransaction(realm: realm)
        try realm.write {
            try block(transaction)
        }
    }
    
    public func values<T: Persistable>(_ type: T.Type) -> FetchedResults<T> {
        let results = realm.objects(T.ManagedObject.self)
        return FetchedResults(results: results)
    }
}
