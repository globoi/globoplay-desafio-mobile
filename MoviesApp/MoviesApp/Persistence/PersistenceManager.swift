//
//  PersistenceManager.swift
//  MoviesApp
//
//  Created by Gustavo Tiecker on 01/03/22.
//

import Foundation

enum PersistenceActionType {
    case add, remove
}

enum PersistenceError: String, Error {
    case unableToSave = "Unable to save favorite"
    case unableToRetrieve = "Unable to retrieve favorites list"
    case alreadyInFavorites = "Movie already in favorites"
}

enum PersistenceManager {
    
    static private let defaults = UserDefaults.standard
    enum Keys { static let favorites = "favorites" }
    
    static func updateWith(favorite: Movie, actionType: PersistenceActionType, completed: @escaping (PersistenceError?) -> Void) {
            retrieveFavorites { result in
                switch result {
                case.success(var favorites):
                    
                    switch actionType {
                    case .add:
                        guard !favorites.contains(favorite) else {
                            completed(.alreadyInFavorites)
                            return
                        }
                        
                        favorites.append(favorite)
                        
                    case .remove:
                        favorites.removeAll { $0.id == favorite.id}
                    }
                    
                    completed(save(favorites: favorites))
                    
                case.failure(let error):
                    completed(error)
                }
            }
        }
    
    static func save(favorites: [Movie]) -> PersistenceError? {
        do {
            let encoder = JSONEncoder()
            let encodedFavorites = try encoder.encode(favorites)
            defaults.setValue(encodedFavorites, forKey: Keys.favorites)
            return nil
        } catch {
            return .unableToSave
        }
    }
    
    static func retrieveFavorites(completed: @escaping (Result<[Movie], PersistenceError>) -> Void) {
        guard let favoritesData = defaults.object(forKey: Keys.favorites) as? Data else {
            completed(.success([]))
            return
        }
        
        do {
            let decoder = JSONDecoder()
            let favorites = try decoder.decode([Movie].self, from: favoritesData)
            completed(.success(favorites))
        } catch {
            completed(.failure(.unableToRetrieve))
        }
    }
}
