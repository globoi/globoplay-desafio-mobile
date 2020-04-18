//
//  String+Data.swift
//  globoplay
//
//  Created by Marcos Curvello on 18/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation

extension String {
    var base64Decoded: String? {
        guard let base64 = Data(base64Encoded: self) else { return nil }
        return String(data: base64, encoding: .utf8)
    }
    var base64Encoded: String {
        Data(self.utf8).base64EncodedString()
    }
}
