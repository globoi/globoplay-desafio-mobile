//
//  Persistance.swift
//  globoplay
//
//  Created by Marcos Curvello on 20/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Combine
import RealmSwift

final class Persistance: ObservableObject {
    let objectWillChange: AnyPublisher<(), Never>?
    let favoritesResult = try? Container().values(Movie.self)
    
    let container = try! Container()
    
    init() {
        objectWillChange = favoritesResult?.objectWillChange.map { _ in () }.eraseToAnyPublisher()
    }
    
    var favorites: [Movie] { favoritesResult?.value?.compactMap({ Movie(from: $0) }) ?? [] }
    
    public func persist(movie: Movie?) {
        guard let movie = movie else { return }
        self._persist(movie)
    }
    
    // MARK: - Internal
    
    private func _persist(_ movie: Movie) {
        try! container.write { transaction in
            transaction.add(movie, update: true)
        }
    }
}
