//
//  Data.swift
//  globoplay
//
//  Created by Marcos Curvello on 17/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation

let sampleMovies: [MovieResult] = load("movies")
let sampleShows: [ShowResult] = load("shows")

func load<A: Codable>(_ name: String) -> A {
    let url = Bundle.main.url(forResource: name, withExtension: "json")!
    let data = try! Data(contentsOf: url)
    return try! JSONDecoder().decode(A.self, from: data)
}
