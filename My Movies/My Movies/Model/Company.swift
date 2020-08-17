//
//  Company.swift
//  My Movies
//
//  Created by Rafael Valer on 17/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

struct Company {
    let id: Int
    let name: String
}

extension Company: Decodable {
    enum CodingKeys: String, CodingKey {
        case id, name
    }
    
    init?(data: Data) {
        guard let company = try? JSONDecoder.defaultDecoder.decode(Company.self, from: data) else {
            return nil
        }
        self = company
    }
}
