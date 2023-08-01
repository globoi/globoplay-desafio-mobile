//
//  RealmManager.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 31/07/23.
//

import Foundation
import RealmSwift

class RealmManager{
    
    func removeItem(id: Int?){
        do{
            try Realm().write({
                if let objectToDelete = getObject(id: id){
                    try Realm().delete(objectToDelete)
                }
            })
        }catch{
            print(error)
        }
    }
    
    func contains(id: Int?) -> Bool{
        return getObject(id: id) != nil
    }
    
    func addItem(_ item: MyListResult, onList list: ObservedResults<MyListResult>){
        list.append(item.asMyListResult())
    }
    
    private func getObject(id: Int?) -> MyListResult?{
        do{
            if let object = try Realm().object(ofType: MyListResult.self, forPrimaryKey: id){
                return object
            }
        }catch{
            print(error)
        }
        
        return nil
    }
    
}
