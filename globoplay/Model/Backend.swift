//
//  api.swift
//  globoplay
//
//  Created by Marcos Curvello on 20/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation
import TinyNetworking

protocol Mapper {
    associatedtype A: Codable
    var base: URL { get }
    var full: URL { get }
    var image: URL { get }
    var endpoint: Endpoint<A> { get }
    var headers: [String: String] { get }
}

struct Backend {
    enum API<A: Codable>: Mapper {
        case companyMovies(String, [QueryType])
        case movieDetail(String, [QueryType])
        case discover([QueryType])
        case image(String, String)
        
        var headers: [String: String] { ["Authorization": "Bearer " + token.value] }
        var base: URL { URL(string: "https://api.themoviedb.org/3/")! }
        var image: URL { URL(string: "https://image.tmdb.org/t/p/")! }
        var full: URL {
            switch self {
            case .companyMovies(let id, let queries):
                return base.appendingPathComponent("company/\(id)/movies").query(queries.compactMap{ $0.value })
            case .movieDetail(let id, let queries):
                return base.appendingPathComponent("movie/\(id)").query(queries.compactMap{ $0.value })
            case .discover(let queries):
                return base.appendingPathComponent("discover/tv").query(queries.compactMap{ $0.value })
            case .image(let size, let path):
                return image.appendingPathComponent("\(size)\(path)")
            }
        }
        
        var endpoint: Endpoint<A> {
            Endpoint<A>(json: .get, url: full, headers: headers)
        }
    }

    enum QueryType {
        case page(String)
        case company(String)
        case language(String)
        case keyword(String)
        case tv
        
        var value: URLQueryItem {
            switch self {
            case .page(let number):
                return URLQueryItem(name: "page", value: number)
            case .company(let id):
                return URLQueryItem(name: "with_companies", value: id)
            case .language(let value):
                return URLQueryItem(name: "language", value: value)
            case .keyword(let words):
                return URLQueryItem(name: "with_keywords", value: words)
            case .tv:
                return URLQueryItem(name: "tv", value: nil)
            }
        }
    }
        
    static func endpoint<A>(_ type: API<A>) -> Endpoint<A> {
        return type.endpoint
    }
    
    static func image(for path: String, size: String = "original") -> URL {
        return URL(string: "https://image.tmdb.org/t/p/\(size)\(path)")!
    }
}
