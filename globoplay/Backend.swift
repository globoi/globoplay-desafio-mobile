//
//  api.swift
//  globoplay
//
//  Created by Marcos Curvello on 20/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation
import TinyNetworking

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
    var asc: String {
        String(format: "%@%@", self.rawValue, ".asc")
    }
    var desc: String {
        String(format: "%@%@", self.rawValue, ".desc")
    }
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

public struct EndpointUrl {
    let path: String
    var queryItems: [URLQueryItem]?
}

extension EndpointUrl {
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

extension EndpointUrl {
    static func discover(movie queries: [Query] = []) -> EndpointUrl {
        return EndpointUrl(
            path: "/3/discover/movie",
            queryItems: queries.isEmpty ? nil : queries.map{ $0.item }
        )
    }
    
    static func discover(tv queries: [Query] = []) -> EndpointUrl {
        return EndpointUrl(
            path: "/3/discover/tv",
            queryItems: queries.isEmpty ? nil : queries.map{ $0.item }
        )
    }
    
    static func movie(detail id: String, queries: [Query] = []) -> EndpointUrl {
        return EndpointUrl(
            path: String(format: "%@%@", "/3/movie/", id),
            queryItems: queries.isEmpty ? nil : queries.map{ $0.item }
        )
    }
    
    static func image(for size: String = "original", path: String) -> EndpointUrl {
        return EndpointUrl(
            path: String(format: "%@%@%@", "/t/p/", size, path)
        )
    }
    
    static func company(movies id: String, queries: [Query] = []) -> EndpointUrl {
        return EndpointUrl(
            path: String(format: "%@%@%@", "/3/company/", id, "/movies"),
            queryItems: queries.isEmpty ? nil : queries.map{ $0.item }
        )
    }
    
    static func genres(movie queries: [Query] = []) -> EndpointUrl {
        return EndpointUrl(
            path: "/3/genre/movie/list",
            queryItems: queries.isEmpty ? nil : queries.map{ $0.item }
        )
    }
}

extension EndpointUrl {
    var auth: [String: String] { ["Authorization": "Bearer " + token.value] }
}
