//
//  Card.swift
//  Desafio GloboPlay
//
//  Created by Filipo Negrao on 02/11/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import Foundation

public class Card : NSObject {
    public var id : Int64 = 0
    public var originalName: String = ""
    public var name: String = "";
    public var originalCountry : String = "";
    public var backdropPath : String = "";
    public var posterPath : String = "";
    public var overview : String = "";
    public var genres : [Genre] = [Genre]()
    
    public class func fromDict(dict: [String: Any]) -> Card {
        let card = Card()
        if let id = dict["id"] as? Int64 {
            card.id = id
        }
        if let posterPath = dict["poster_path"] as? String {
            card.posterPath = posterPath
        }
        return card
    }
    
    public func getPosterUrlForSize(size: Int) -> String {
        let url = "\(IMAGE_URL)\(size)\(self.posterPath)"
        return url
    }
}
