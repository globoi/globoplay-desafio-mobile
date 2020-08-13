//
//  Genre.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

struct Genre {
    let id: Int
    let name: String
}

extension Genre: Decodable {
    enum CodingKeys: String, CodingKey {
        case id, name
    }
    
    init?(data: Data) {
        guard let genre = try? JSONDecoder.defaultDecoder.decode(Genre.self, from: data) else {
            return nil
        }
        self = genre
    }
}
