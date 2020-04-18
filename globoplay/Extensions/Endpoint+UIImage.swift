//
//  Endpoint+UIImage.swift
//  globoplay
//
//  Created by Marcos Curvello on 18/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import UIKit
import TinyNetworking

extension Endpoint where A == UIImage {
    init(imageURL url: URL) {
        self.init(.get, url: url, expectedStatusCode: expected200to300) { data,response in
            guard let d = data, let i = UIImage(data: d) else {
                return .failure(ImageError())
            }
            return .success(i)
        }
    }
}

