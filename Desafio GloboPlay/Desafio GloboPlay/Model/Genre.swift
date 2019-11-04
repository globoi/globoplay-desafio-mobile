//
//  Genre.swift
//  Desafio GloboPlay
//
//  Created by Filipo Negrao on 03/11/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import Foundation

public class Genre : NSObject {
    public var id : Int64 = 0
    public var name: String = ""
    
    public class func fromDict(dict: [String: Any]) -> Genre {
        let genre = Genre()
        if let id = dict["id"] as? Int64 {
            genre.id = id
        }
        if let name = dict["name"] as? String {
            genre.name = name
        }
        return genre
    }
}

