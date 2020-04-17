//
//  Store.swift
//  globoplay
//
//  Created by Marcos Curvello on 16/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation
import Combine
import TinyNetworking

final class Store: ObservableObject {
    private let movieResultsResource = Resource(endpoint: api.movieResults(for: "18410"))
    @Published var movies = sampleMovies
    
    private let showResultsResource = Resource(endpoint: api.showResults(for: "18410"))
    @Published var shows = sampleShows
    
    let loaded = true
}

struct api {
    static let base = URL(string: "https://api.themoviedb.org/3/")!
    static let languageQuery = URLQueryItem(name: "language", value: "pt-BR")
    static let token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYTk3ZTlkNWIyOTg3NDU0MDkzMjNjYTBhMDkxODVlOSIsInN1YiI6IjVlOTYzMWU3NzcwNzAwMDAxZWExZTcxZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.f6Icgxmkps0sMry11sONx6Zegh3BOGQ7q9EOdYKfYzo"
    static var headers: [String: String] {
        ["Authorization": "Bearer " + token]
    }
    
    static func movieResults(for companyId: String) -> Endpoint<Discover<MovieResult>> {
        print("Locale: \(Locale.current.languageCode ?? "N/A")")

        let url = api.base.appendingPathComponent("company/\(companyId)/movies").query([languageQuery])
        return Endpoint<Discover<MovieResult>>(json: .get, url: url, headers: headers)
    }
    
    static func showResults(for companyId: String) -> Endpoint<Discover<ShowResult>> {
        let companyQuery = URLQueryItem(name: "with_company", value: companyId)
        let url = api.base.appendingPathComponent("discover/tv").query([companyQuery, languageQuery])
        return Endpoint<Discover<ShowResult>>(json: .get, url: url, headers: headers)
    }
}

