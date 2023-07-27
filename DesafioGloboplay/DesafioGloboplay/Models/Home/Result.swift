//
//  Result.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import Foundation

enum MediaType{
    case movie
    case tvShow
}

protocol Result{
    var posterPath: String? { get set }
    var id: Int? { get set }
    var overview: String? {get set}
    
    func getTitle() -> String?
    
    func getMediaType() -> MediaType
}
