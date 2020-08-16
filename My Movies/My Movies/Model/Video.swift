//
//  Video.swift
//  My Movies
//
//  Created by Rafael Valer on 16/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

struct Video {
    let key: String
    let site: String
    let type: String
}

extension Video: Decodable {
    
    enum CodingKeys: String, CodingKey {
        case key, site, type
    }
    
    init?(data: Data) {
        guard let video = try? JSONDecoder.defaultDecoder.decode(Video.self, from: data) else {
            return nil
        }
        self = video
    }
}
