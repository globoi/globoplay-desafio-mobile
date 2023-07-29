//
//  MyListResult.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 29/07/23.
//

import Foundation
import RealmSwift

class MyListResult: Object, Result, Identifiable{
    
    @Persisted var posterPath: String?
    @Persisted(primaryKey: true) var id: Int?
    @Persisted var overview: String?
    @Persisted var title: String?
    @Persisted var mediaType: String?
    
    func getTitle() -> String? {
        return self.title
    }
    
    func getMediaType() -> MediaType {
        return MediaType(rawValue: self.mediaType ?? String()) ?? .movie
    }
}
