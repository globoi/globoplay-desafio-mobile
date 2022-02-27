//
//  Constants.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 23/02/22.
//

import UIKit

struct Constants {
    
    // MARK: - Network Constants
    struct ProductionServer {
        static let BASE_URL = "https://api.themoviedb.org/3"
        static let IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }
    struct APIParameterKey {
        static let API_KEY = "8bd74769280804b48ea517b197e125c0"
    }
    struct APIYouTubeKey {
        static let YOUTUBE_BASE_URL = "https://youtube.googleapis.com/youtube/v3/search?"
        static let API_YOUTUBE_KEY =  "AIzaSyAPLfsDY2EoOnBJB9X737xdNu-nt_eBVjc"
    }
}
