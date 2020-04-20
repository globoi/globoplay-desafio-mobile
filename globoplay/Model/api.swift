//
//  api.swift
//  globoplay
//
//  Created by Marcos Curvello on 20/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation
import TinyNetworking

struct api {
    static let base = URL(string: "https://api.themoviedb.org/3/")!
    static let base_image = URL(string: "https://image.tmdb.org/")!
    static let token = ProcessInfo.processInfo.environment["TMDB_API_TOKEN"]!
    
    static let company = URLQueryItem(name: "with_company", value: "18410")
    static let language = URLQueryItem(name: "language", value: "pt-BR")
    
    
    static var headers: [String: String] {
        ["Authorization": "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYTk3ZTlkNWIyOTg3NDU0MDkzMjNjYTBhMDkxODVlOSIsInN1YiI6IjVlOTYzMWU3NzcwNzAwMDAxZWExZTcxZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.f6Icgxmkps0sMry11sONx6Zegh3BOGQ7q9EOdYKfYzo"]
    }
    
    static func movieResults(for companyId: String, page: Int = 1) -> Endpoint<Discover<MovieResult>> {
        let queries = [language]
        let url = api.base.appendingPathComponent("company/\(companyId)/movies").query(queries)
        return Endpoint<Discover<MovieResult>>(json: .get, url: url, headers: headers)
    }
    
    static func showResults(for companyId: String?) -> Endpoint<Discover<ShowResult>> {
        let queries = [company, language]
        let url = api.base.appendingPathComponent("discover/tv").query(queries)
        return Endpoint<Discover<ShowResult>>(json: .get, url: url, headers: headers)
    }
    
    static func image(for path: String, size: String = "original") -> URL {
        let url = api.base_image.appendingPathComponent("/t/p/\(size)\(path)")
        return url
    }
    
    static func movie(details id: Int) -> Endpoint<Movie> {
        let queries = [language]
        let url = api.base.appendingPathComponent("movie/\(id)").query(queries)
        return Endpoint<Movie>(json: .get, url: url, headers: headers)
    }
}
