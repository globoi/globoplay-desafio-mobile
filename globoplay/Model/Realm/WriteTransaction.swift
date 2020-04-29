//
//  WriteTransaction.swift
//  globoplay
//
//  Created by Marcos Curvello on 20/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation
import RealmSwift

public final class WriteTransaction {
    private let realm: Realm
    
    internal init(realm: Realm) {
        self.realm = realm
    }
    
    public func add<T: Persistable>(_ value: T, update: Bool) {
        realm.add(value.managedObject(), update: update ? .modified : .all)
    }
    
    public func delete<T: Persistable>(_ value: T) {
        guard let object = realm.object(ofType: T.ManagedObject.self, forPrimaryKey: value.persistanceId) else {
            return
        }
        realm.delete(object)
    }
}
