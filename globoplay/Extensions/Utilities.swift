//
//  Utilities.swift
//  globoplay
//
//  Created by Marcos Curvello on 04/05/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation
import UIKit

private let formatter: DateFormatter = {
    let formatter = DateFormatter()
    formatter.dateFormat = "yyyy-MM-dd"
    return formatter
}()

private let converter: DateFormatter = {
    let formatter = DateFormatter()
    formatter.dateFormat = "dd/MM/yyyy"
    return formatter
}()

func timeString(_ time: String) -> String {
    let date = formatter.date(from: time)!
    return converter.string(from: date)
}
