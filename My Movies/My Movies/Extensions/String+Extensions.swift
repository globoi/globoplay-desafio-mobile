//
//  String+Extensions.swift
//  My Movies
//
//  Created by Rafael Valer on 13/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

extension String {
    
    /// Converts a date string to another format
    /// - Parameters:
    ///   - from: String date format
    ///   - to: String date format
    /// - Returns: The final string converted to another date format
    func convert(fromDateFormat from: String, toDateFormat to: String) -> String? {
        
        let dateFormatter = DateFormatter.defaultFormatter
        dateFormatter.dateFormat = from
        guard let fromDate = dateFormatter.date(from: self) else { return nil }

        dateFormatter.dateFormat = to
        let toDateString = dateFormatter.string(from: fromDate)
        return toDateString
    }
}
