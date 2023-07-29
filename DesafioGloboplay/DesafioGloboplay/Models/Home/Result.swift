//
//  Result.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import Foundation

enum MediaType: String{
    case movie = "Filme"
    case tvShow = "Série"
}

protocol Result{
    var posterPath: String? { get set }
    var id: Int? { get set }
    var overview: String? {get set}
    
    func getTitle() -> String?
    
    func getMediaType() -> MediaType
}

extension Result{
    
    func asMyListResult() -> MyListResult{
        
        let result = MyListResult()
        result.posterPath = self.posterPath
        result.overview = self.overview
        result.id = self.id
        result.mediaType = self.getMediaType().rawValue
        
        return result
        
    }
    
}
