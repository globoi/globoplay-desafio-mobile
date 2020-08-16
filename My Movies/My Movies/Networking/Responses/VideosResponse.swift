//
//  VideosResponse.swift
//  My Movies
//
//  Created by Rafael Valer on 16/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

/// JSON parsing when dealing with Movies responses
enum VideosResponse {
    case success(videos: [Video])
    case failed(error: AppError)
    
    /// Parses the data from API response
    /// - Parameter jsonData: JSON Data
    /// - Returns: parsed VideosResponse
    static func parse(_ jsonData: Data) -> VideosResponse {
        guard let results = VideosResults(data: jsonData) else {
            debugPrint("Decoding Error: VideosResponse")
            return .failed(error: ApplicationError.parseError)
        }
        
        return .success(videos: results.videos)
    }
}
