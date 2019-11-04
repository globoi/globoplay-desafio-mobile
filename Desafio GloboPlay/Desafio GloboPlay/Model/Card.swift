//
//  Card.swift
//  Desafio GloboPlay
//
//  Created by Filipo Negrao on 02/11/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import Foundation

public class Card : NSObject {
    public var id : Int64 = 0
    public var originalName: String = ""
    public var name: String = "";
    public var originalCountry : String = "";
    public var backdropPath : String = "";
    public var posterPath : String = "";
    public var overview : String = "";
    public var genres : [Genre] = [Genre]()
    public var numberOfEpisodeos : Int64 = 0;
    public var fisrtAirDate : String = ""
    public var country : String = ""
    public var directors : String = ""
    public var homePage : String = ""
    public var movie: Bool = false
    
    public class func fromDict(dict: [String: Any]) -> Card {
        let card = Card()
        if let id = dict["id"] as? Int64 {
            card.id = id
        }
        if let posterPath = dict["poster_path"] as? String {
            card.posterPath = posterPath
        }
        if let backdropPath = dict["backdrop_path"] as? String {
            card.backdropPath = backdropPath
        }
        if let name = dict["name"] as? String {
            card.name = name
        }
        if let overview = dict["overview"] as? String {
            card.overview = overview
        }
        if let original = dict["original_name"] as? String {
            card.originalName = original
        }
        if let genresJson = dict["genres"] as? [[String:Any]] {
            for genreJson in genresJson {
                if let id = genreJson["id"] as? Int64 {
                    if let genre = ApplicationService.sharedInstance.mapGenres[id] {
                        card.genres.append(genre)
                    }
                }
            }
        }
        if let number = dict["number_of_episodes"] as? Int64 {
            card.numberOfEpisodeos = number
        }
        if let firstAir = dict["first_air_date"] as? String {
            card.fisrtAirDate = firstAir
        }
        if let originalCountry = dict["origin_country"] as? [String] {
            if originalCountry.count > 0 {
                card.country = originalCountry[0]
            }
        }
        if let directionsJson = dict["created_by"] as? [[String: Any]] {
            var names = ""
            var i = 0;
            for directionJson in directionsJson {
                if let name = directionJson["name"] as? String {
                    if i > 0 {
                        names += ","
                    }
                    names += name
                    i += 1
                }
            }
            card.directors = names
        }
        if let homepage = dict["homepage"] as? String {
            card.homePage = homepage
        }
        return card
    }
    
    public func getPosterUrlForSize(size: Int) -> String {
        let url = "\(IMAGE_URL)\(200)\(self.posterPath)"
        return url
    }
    
    public func getBackdropUrlForSize(size: Int) -> String {
        let url = "\(IMAGE_URL)\(500)\(self.backdropPath)"
        return url
    }
    
    public func getGenresString () -> String {
        var genreString = "Gênero: "
        var i = 0;
        for genre in self.genres {
            if (i != 0) {
                genreString += ", "
            }
            genreString += genre.name
            i += 1
        }
        return genreString
    }
    
    public func toDict() -> [String: Any] {
        var dict = [String: Any]()
        dict["id"] = self.id
        dict["poster_path"] = self.posterPath
        dict["backdrop_path"] = self.posterPath
        dict["name"] = self.name
        dict["overview"] = self.overview
        
        return dict
    }
}
