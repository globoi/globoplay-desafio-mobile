//
//  TheMovieDBClass.swift
//  TheMovieDB
//
//  Created by Edwy Lugo on 03/02/20.
//  Copyright © 2020 Edwy Lugo. All rights reserved.
//

import Foundation
import ObjectMapper

class MoviesInfo: Mappable {
    required init?(map: Map) {
        
    }
    
    var page: Int = 0
    var total_results: Int = 0
    var total_pages: Int = 0
    var results: [Movies] = []
    
    func mapping(map: Map){
        self.page <- map["page"]
        self.total_results <- map["total_results"]
        self.total_pages <- map["total_pages"]
        self.results <- map["results"]
    }
    
}

class Movies: Mappable {
    required init?(map: Map) {
        
    }
    
    var vote_count: Int = 0
    var id: Int = 0
    var video: Bool = false
    var vote_average: Double = 0
    var title: String = ""
    var popularity: Double = 0 //pode ser Double
    var poster_path: String = ""
    var original_language: String = ""
    var original_title: String = ""
    var genre_ids: [Int] = []
    var backdrop_path: String = ""
    var adult: Bool = false
    var overview: String = ""
    var release_date: String = ""
    
    func mapping(map: Map) {
        self.vote_count <- map["vote_count"]
        self.id <- map["id"]
        self.video <- map["video"]
        self.vote_average <- map["vote_average"]
        self.title <- map["title"]
        self.popularity <- map["popularity"]
        self.poster_path <- map["poster_path"]
        self.original_language <- map["original_language"]
        self.original_title <- map["original_title"]
        self.genre_ids <- map["genre_ids"]
        self.backdrop_path <- map["backdrop_path"]
        self.adult <- map["adult"]
        self.overview <- map["overview"]
        self.release_date <- map["release_date"]
    }
}


class MoviesDetail: Mappable {
    required init?(map: Map) {
        
}
    
    var genres: [MoviesGenres] = []

    
    func mapping(map: Map) {
        
        self.genres <- map["genres"]
        
    }
    
}

class MoviesGenres: Mappable {
    required init?(map: Map) {
        
    }
    
    var id: Int = 0
    var name: String = ""
    
    
    func mapping(map: Map) {
        
        self.id <- map["id"]
        self.name <- map["name"]
    }
    
}
