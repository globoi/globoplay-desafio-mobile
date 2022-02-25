//
//  TopMoviesAPIResponseModel.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 23/02/22.
//

import Foundation

// MARK: - APIResponse
struct MoviesAPIResponse: Codable {
    let page: Int?
    let results: [Movie]?
    let totalPages, totalResults: Int?

    enum CodingKeys: String, CodingKey {
        case page, results
        case totalPages = "total_pages"
        case totalResults = "total_results"
    }
}

enum OriginalLanguage: String, Codable {
    case en = "en"
    case hi = "hi"
    case ja = "ja"
    case ko = "ko"
}

enum MediaType: String, Codable {
    case movie = "movie"
    case tv = "tv"
}
