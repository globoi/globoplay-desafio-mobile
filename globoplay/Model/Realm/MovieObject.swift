//
//  MovieObject.swift
//  globoplay
//
//  Created by Marcos Curvello on 20/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation
import RealmSwift

public protocol Persistable {
    associatedtype ManagedObject: RealmSwift.Object

    init(managedObject: ManagedObject)
    func managedObject() -> ManagedObject
}

@objcMembers
final class MovieObject: Object {
    dynamic var adult: Bool = false
    dynamic var backdropPath: String?
    dynamic var collection: MovieCollection?
    dynamic var budget: Int?
    dynamic var genres: [Genre]?
    dynamic var homepage: String?
    dynamic var id: String = ""
    dynamic var imdbId: String?
    dynamic var originalLanguage: String = ""
    dynamic var originalTitle: String = ""
    dynamic var overview: String?
    dynamic var popularity: Double = 0.0
    dynamic var posterPath: String?
    dynamic var productionCompanies: [Company]?
    dynamic var productionCountries: [Country]?
    dynamic var releaseDate: String = ""
    dynamic var revenue: Int = 0
    dynamic var runtime: Int?
    dynamic var spokenLanguages: [Language]?
    dynamic var status: String = ""
    dynamic var tagline: String?
    dynamic var title: String = ""
    dynamic var video: Bool = false
    dynamic var voteAverage: Double = 0.0
    dynamic var voteCount: Int = 0
    
    override static func primaryKey() -> String? {
        return "id"
    }
}

extension Movie: Persistable {
    public init(managedObject: MovieObject) {
        adult = managedObject.adult
        backdropPath = managedObject.backdropPath
        collection = managedObject.collection
        budget = managedObject.budget
        genres = managedObject.genres ?? []
        homepage = managedObject.homepage
        id = Int(managedObject.id) ?? 0
        imdbId = managedObject.imdbId
        originalLanguage = managedObject.originalLanguage
        originalTitle = managedObject.originalTitle
        overview = managedObject.overview
        popularity = managedObject.popularity
        posterPath = managedObject.posterPath
        productionCompanies = managedObject.productionCompanies ?? []
        productionCountries = managedObject.productionCountries ?? []
        releaseDate = managedObject.releaseDate
        revenue = managedObject.revenue
        runtime = managedObject.runtime
        spokenLanguages = managedObject.spokenLanguages ?? []
        status = managedObject.status
        tagline = managedObject.tagline
        title = managedObject.title
        video = managedObject.video
        voteAverage = managedObject.voteAverage
        voteCount = managedObject.voteCount
    }
    
    public func managedObject() -> MovieObject {
        let movie = MovieObject()
        movie.adult = adult
        movie.backdropPath = backdropPath
        movie.collection = collection
        movie.budget = budget
        movie.genres = genres
        movie.homepage = homepage
        movie.id = String(format: "%U", id)
        movie.imdbId = imdbId
        movie.originalLanguage = originalLanguage
        movie.originalTitle = originalTitle
        movie.overview = overview
        movie.popularity = popularity
        movie.posterPath = posterPath
        movie.productionCompanies = productionCompanies
        movie.productionCountries = productionCountries
        movie.releaseDate = releaseDate
        movie.revenue = revenue
        movie.runtime = runtime
        movie.spokenLanguages = spokenLanguages
        movie.status = status
        movie.tagline = tagline
        movie.title = title
        movie.video = video
        movie.voteAverage = voteAverage
        movie.voteCount = voteCount
        return movie
    }
}
