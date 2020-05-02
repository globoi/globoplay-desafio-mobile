//
//  Store.swift
//  globoplay
//
//  Created by Marcos Curvello on 16/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Combine
import RealmSwift

final class Store: ObservableObject {
    @Published var favoriteMovies: FetchedResults<Movie>? = nil
    let container = try? Container()
    var favorites: [MovieList] { favoriteMovies?.results.map {  MovieList(movieObject: $0) } ?? [] }
    var notificationToken: NotificationToken?

    init() {
        self.favoriteMovies = container?.values(Movie.self)
        self.notificationToken = favoriteMovies?.results.observe { [weak self] _ in
            guard let s = self else { return }
            s.objectWillChange.send()
        }
    }
    
    public func toggleFavorite(movie: Movie) {
        isFavorite(movie: movie.id) ? _delete(movie) : _persist(movie)
    }
    
    public func isFavorite(movie id: Int) -> Bool {
        return !favorites.filter { $0.id == id }.isEmpty
    }
    
    public func persist(movie: Movie?) {
        guard let movie = movie else { return }
        _persist(movie)
    }
    
    public func delete(movie: Movie?) {
        guard let movie = movie else { return }
        _delete(movie)
    }
    
    private func _persist(_ movie: Movie) {
        try! container?.write { transaction in
            transaction.add(movie, update: true)
        }
    }
    
    private func _delete(_ movie: Movie) {
        try! container?.write { transaction in
            transaction.delete(movie)
        }
    }
    
    deinit {
        notificationToken?.invalidate()
    }
}
