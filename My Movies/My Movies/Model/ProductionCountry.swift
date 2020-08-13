//
//  ProductionCountry.swift
//  My Movies
//
//  Created by Rafael Valer on 13/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

struct ProductionCountry {
    let name: String
}

extension ProductionCountry: Decodable {
    enum CodingKeys: String, CodingKey {
        case name
    }
    
    init?(data: Data) {
        guard let productionCountry = try? JSONDecoder.defaultDecoder.decode(ProductionCountry.self, from: data) else {
            return nil
        }
        self = productionCountry
    }
}
