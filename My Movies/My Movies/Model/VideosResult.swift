//
//  VideosResult.swift
//  My Movies
//
//  Created by Rafael Valer on 16/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

struct VideosResults {
    let videos: [Video]
}

extension VideosResults: Decodable {
    enum CodingKeys: String, CodingKey {
        case videos = "results"
    }
    
    init?(data: Data) {
        guard let videos = try? JSONDecoder.defaultDecoder.decode(VideosResults.self, from: data) else {
            return nil
        }
        self = videos
    }
}
