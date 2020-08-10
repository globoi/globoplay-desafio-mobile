//
//  AppError.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

protocol AppError: Error {
    var description: String { get }
}

enum ApplicationError: AppError {
    
    case urlNotFound, missingData, commonError, parseError
    
    var description: String {
        switch self {
        case .urlNotFound: return "Url Not Found"
        case .missingData: return "No data Returned"
        case .commonError: return "Common Error"
        case .parseError: return "Parse Error"
        }
    }
}
