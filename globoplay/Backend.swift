//
//  Backend.swift
//  globoplay
//
//  Created by Marcos Curvello on 20/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation

enum GenreType: Int, CaseIterable {
    case action = 28
    case adventure = 12
    case animation = 16
    case comedy = 35
    case crime = 80
    case documentary = 99
    case drama = 18
    case family = 10751
    case fantasy = 14
    case history = 36
    case horror = 27
    case music = 10402
    case mystery = 9648
    case romance = 10749
    case scifi = 878
    case tvmovie = 10770
    case thriller = 53
    case war = 10752
    case western = 37
    
    var title: String {
        switch self {
        case .action: return "Action"
        case .adventure: return "Adventure"
        case .animation: return "Animation"
        case .comedy: return "Comedy"
        case .crime: return "Crime"
        case .documentary: return "Documentary"
        case .drama: return "Drama"
        case .family: return "Family"
        case .fantasy: return "Fantasy"
        case .history: return "History"
        case .horror: return "Horror"
        case .music: return "Music"
        case .mystery: return "Mystery"
        case .romance: return "Romance"
        case .scifi: return "Science Fiction"
        case .tvmovie: return "TV Movie"
        case .thriller: return "Thriller"
        case .war: return "War"
        case .western: return "Western"
        }
    }
    
    var genre: Genre {
        Genre(id: self.rawValue, name: title)
    }
}

protocol Order {
    var asc: String { get }
    var desc: String { get }
}

enum Sorting: String {
    case popularity = "popularity"
    case voteAverage = "vote_average"
    case voteCount = "vote_count"
}

extension Sorting: Order {
    var asc: String { String(format: "%@%@", self.rawValue, ".asc") }
    var desc: String { String(format: "%@%@", self.rawValue, ".desc") }
}

enum QueryType: String {
    case page = "page"
    case company = "with_companies"
    case language = "language"
    case keyword = "with_keywords"
    case genre = "with_genres"
    case sort = "sort_by"
}

struct Query {
    let name: QueryType
    let value: String
}

extension Query {
    init(name: QueryType, value: Int) {
        self.init(name: name, value: String(value))
    }
    
    var item: URLQueryItem {
        return URLQueryItem(name: name.rawValue, value: String(value))
    }
}

public struct Request {
    enum `Type`: String {
        case movie
        case tv
    }
    
    enum Path {
        case discover(Type)
        case detail(Type, Int)
        case image(String, String)
        
        var value: String {
            switch self {
            case let .discover(type): return String(format: "%@%@", "/3/discover/", type.rawValue)
            case let .detail(type, id): return String(format: "%@%@/%u", "/3/", type.rawValue, id)
            case let .image(size, path): return String(format: "%@%@%@", "/t/p/", size, path)
            }
        }
    }

    let path: Path
    var queryItems: [URLQueryItem]?
}

extension Request {
    init(_ path: Path, queries: [Query]? = defaultQuery) {
        self.init(path: path, queryItems: queries?.compactMap { $0.item })
    }
}

extension Request {
    static var defaultQuery: [Query] { [Query(name: .language, value: "pt-BR")] }
    var auth: [String: String] { ["Authorization": "Bearer " + token.value] }
    var url: URL? {
        var components = URLComponents()
        components.scheme = "https"
        components.host = "api.themoviedb.org"
        components.path = path.value
        components.queryItems = queryItems
        return components.url
    }
    var imageUrl: URL? {
        var components = URLComponents()
        components.scheme = "https"
        components.host = "image.tmdb.org"
        components.path = path.value
        return components.url
    }
}
