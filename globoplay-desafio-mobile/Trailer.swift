//
//  Trailer.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 09/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import Foundation

struct VideoRoot : Decodable {
    
   let results      : [Trailer]
    
}

struct Trailer:  Decodable {
    
    var key         : String
}
