//
//  Extensions.swift
//  globoplay
//
//  Created by Marcos Curvello on 16/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation

public extension String {
    var base64Decoded: String? {
        guard let base64 = Data(base64Encoded: self) else { return nil }
        return String(data: base64, encoding: .utf8)
    }
    var base64Encoded: String {
        Data(self.utf8).base64EncodedString()
    }
}

public extension URL {
    func query(_ items: [URLQueryItem]) -> URL {
        guard var urlComponents = URLComponents(string: absoluteString) else { return absoluteURL }
        var queryItems: [URLQueryItem] = urlComponents.queryItems ?? []
        
        items.forEach({ queryItems.append($0) })
        urlComponents.queryItems = queryItems
        return urlComponents.url!
    }
}
