//
//  Array.swift
//  globoplay
//
//  Created by Marcos Curvello on 22/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation

extension Array {
    func chunked(_ size: Int) -> [[Element]] {
        var transform = [[Element]]()
        for index in 0...self.count {
            if index % size == 0 && index != 0 {
                transform.append(
                    Array(self[(index - size)..<index])
                )
            }
            else if index == self.count {
                transform.append(
                    Array(self[(index - index % size)..<index])
                )
            }
        }
        return transform
    }
}
