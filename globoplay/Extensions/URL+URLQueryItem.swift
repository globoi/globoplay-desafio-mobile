//
//  URL+URLQueryItem.swift
//  globoplay
//
//  Created by Marcos Curvello on 18/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation

extension URL {
    func query(_ items: [URLQueryItem]) -> URL {
        guard var urlComponents = URLComponents(string: absoluteString) else { return absoluteURL }
        var queryItems: [URLQueryItem] = urlComponents.queryItems ?? []
        
        items.forEach({ queryItems.append($0) })
        urlComponents.queryItems = queryItems
        return urlComponents.url!
    }
}
