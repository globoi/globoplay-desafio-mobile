//
//  Home.swift
//  globoplay
//
//  Created by Marcos Curvello on 28/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Combine
import TinyNetworking

final class Home: ObservableObject {
    
    var objectWillChange: AnyPublisher<(), Never> = Publishers.Sequence<[HomeCollection], Never>(sequence: []).map { _ in () }.eraseToAnyPublisher()
    var homeCollections: [HomeCollection] = []
    var colletions: [HomeCollection] { homeCollections }
    
    var loaded: Bool { genreResource.value != nil && genreResourceOne.value != nil && genreResourceTwo.value != nil && genreResourceThree.value != nil }
        
    let genres: [Genre] = Genre.random(sequence: 4)
    
    var genreResource: MovieDiscover
    var genreResourceOne: MovieDiscover
    var genreResourceTwo: MovieDiscover
    var genreResourceThree: MovieDiscover
    
    init() {
        let request = Request(.discover(.movie), queries: [Query(name: .genre, value: genres[0].id)])
        let requestOne = Request(.discover(.movie), queries: [Query(name: .genre, value: genres[1].id)])
        let requestTwo = Request(.discover(.movie), queries: [Query(name: .genre, value: genres[2].id)])
        let requestThree = Request(.discover(.movie), queries: [Query(name: .genre, value: genres[3].id)])

        genreResource = MovieDiscover(endpoint: Endpoint(json: .get, url: request.url!, headers: request.auth))
        genreResourceOne = MovieDiscover(endpoint: Endpoint(json: .get, url: requestOne.url!, headers: requestOne.auth))
        genreResourceTwo = MovieDiscover(endpoint: Endpoint(json: .get, url: requestTwo.url!, headers: requestTwo.auth))
        genreResourceThree = MovieDiscover(endpoint: Endpoint(json: .get, url: requestThree.url!, headers: requestThree.auth))

        objectWillChange = genreResource.objectWillChange.zip(
            genreResourceOne.objectWillChange,
            genreResourceTwo.objectWillChange,
            genreResourceThree.objectWillChange
            ).map { _ in () }.eraseToAnyPublisher()

        homeCollections.append(contentsOf: [
            HomeCollection(genre: genres[0], resource: genreResource),
            HomeCollection(genre: genres[1], resource: genreResourceOne),
            HomeCollection(genre: genres[2], resource: genreResourceTwo),
            HomeCollection(genre: genres[3], resource: genreResourceThree)
        ])
    }
}

typealias MovieDiscover = Resource<Discover<MovieList>>
struct HomeCollection {
    var genre: Genre
    var resource: MovieDiscover
}
