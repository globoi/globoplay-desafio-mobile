//
//  UserPreferences.swift
//  My Movies
//
//  Created by Rafael Valer on 13/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

enum UserDefaultsKeys: String {
    case favoriteMovies = "favorite_movies"
}

class UserPreferences {
    
    static let shared = UserPreferences()
    private let userDefaults: UserDefaults
    
    private init() {
        userDefaults = UserDefaults.standard
    }
    
    func getFavoriteMovies() -> [String: [String]]? {
        guard let favMovies = userDefaults.object(forKey: UserDefaultsKeys.favoriteMovies.rawValue) as? [String: [String]] else {
            return nil
        }
        return favMovies
    }
    
    func addFavoriteMovie(movieId: String, title: String?, posterPath: String?) {
        
        var favoriteMovies: [String: [String]] = [:]
        if let existingFavMovies = getFavoriteMovies() {
            favoriteMovies = existingFavMovies
        }
    
        favoriteMovies[movieId] = [title ?? "", posterPath ?? ""]
        userDefaults.set(favoriteMovies, forKey: UserDefaultsKeys.favoriteMovies.rawValue)
    }
    
    func removeFavoriteMovie(movieId: String) {
        if var existingFavMovies = getFavoriteMovies() {
            existingFavMovies.removeValue(forKey: movieId)
            userDefaults.set(existingFavMovies, forKey: UserDefaultsKeys.favoriteMovies.rawValue)
        }
    }
    
    func isMovieOnFavorites(_ movieId: String) -> Bool {
        guard let favoriteMovies = getFavoriteMovies() else { return false }
        
        return favoriteMovies[movieId] != nil
    }
}
