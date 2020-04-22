//
//  Data.swift
//  globoplay
//
//  Created by Marcos Curvello on 17/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation

struct Token: Codable {
    var value: String
}
let token: Token = load("token")
let movieResults: Discover<MovieList> = load("movies")
let sampleMovie: Movie = load("singleMovie")
let sampleMovies = movieResults.results

func load<A: Codable>(_ name: String) -> A {
    let url = Bundle.main.url(forResource: name, withExtension: "json")!
    let data = try! Data(contentsOf: url)
    return try! JSONDecoder().decode(A.self, from: data)
}
