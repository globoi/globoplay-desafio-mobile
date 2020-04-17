//
//  Data.swift
//  globoplay
//
//  Created by Marcos Curvello on 17/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation

fileprivate let movieResults: Discover<MovieResult> = load("movies")
fileprivate let showResults: Discover<ShowResult> = load("shows")

let sampleMovies = movieResults.results
let sampleShows = showResults.results

func load<A: Codable>(_ name: String) -> A {
    let url = Bundle.main.url(forResource: name, withExtension: "json")!
    let data = try! Data(contentsOf: url)
    return try! JSONDecoder().decode(A.self, from: data)
}
