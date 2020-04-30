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
    
    var genre: Genre { Genre(id: self.rawValue, name: title) }
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
    var item: URLQueryItem {
        return URLQueryItem(name: name.rawValue, value: value)
    }
}

public struct Request {
    let path: String
    var queryItems: [URLQueryItem]?
}

extension Request {
    var url: URL? {
        var components = URLComponents()
        components.scheme = "https"
        components.host = "api.themoviedb.org"
        components.path = path
        components.queryItems = queryItems
        return components.url
    }
    var imageUrl: URL? {
        var components = URLComponents()
        components.scheme = "https"
        components.host = "image.tmdb.org"
        components.path = path
        return components.url
    }
}

extension Request {
    static func discover(movie queries: [Query] = []) -> Request {
        return Request(
            path: "/3/discover/movie",
            queryItems: queries.isEmpty ? nil : queries.map{ $0.item }
        )
    }
    
    static func discover(tv queries: [Query] = []) -> Request {
        return Request(
            path: "/3/discover/tv",
            queryItems: queries.isEmpty ? nil : queries.map{ $0.item }
        )
    }
    
    static func movie(detail id: String, queries: [Query] = []) -> Request {
        return Request(
            path: String(format: "%@%@", "/3/movie/", id),
            queryItems: queries.isEmpty ? nil : queries.map{ $0.item }
        )
    }
    
    static func image(size spec: String = "original", path: String) -> Request {
        return Request(
            path: String(format: "%@%@%@", "/t/p/", spec, path)
        )
    }
    
    static func company(movies id: String, queries: [Query] = []) -> Request {
        return Request(
            path: String(format: "%@%@%@", "/3/company/", id, "/movies"),
            queryItems: queries.isEmpty ? nil : queries.map{ $0.item }
        )
    }
    
    static func genres(movie queries: [Query] = []) -> Request {
        return Request(
            path: "/3/genre/movie/list",
            queryItems: queries.isEmpty ? nil : queries.map{ $0.item }
        )
    }
}

extension Request {
    var auth: [String: String] { ["Authorization": "Bearer " + token.value] }
}
