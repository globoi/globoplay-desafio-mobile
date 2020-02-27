//
//  Genre.swift
//  TheMovieDB
//
//  Created by Edwy Lugo on 26/02/20.
//  Copyright © 2020 Edwy Lugo. All rights reserved.
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
