//
//  DateFormatter+Extensions.swift
//  My Movies
//
//  Created by Rafael Valer on 13/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

extension DateFormatter {
    
    /// Default Date Formatter
    static let defaultFormatter: DateFormatter = {
        let dateFormatter = DateFormatter()
        return dateFormatter
    }()
}
