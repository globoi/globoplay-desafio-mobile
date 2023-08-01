//
//  TrailerResults.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 31/07/23.
//

import Foundation

// MARK: - TrailerResults
struct TrailerResults: Codable {
    var id: Int?
    var results: [TrailerResult]?
}

// MARK: - Result
struct TrailerResult: Codable {
    var key: String?
    var type: TypeEnum?

    enum CodingKeys: String, CodingKey {
        case key,type
    }
}

enum TypeEnum: String, Codable {
    case behindTheScenes = "Behind the Scenes"
    case clip = "Clip"
    case featurette = "Featurette"
    case teaser = "Teaser"
    case trailer = "Trailer"
}
