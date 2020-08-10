//
//  JSONDecoder+Extensions.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

extension JSONDecoder {
    
    /// Default JSON Decoder
    static let defaultDecoder: JSONDecoder = {
        let decoder = JSONDecoder()
        return decoder
    }()
}
