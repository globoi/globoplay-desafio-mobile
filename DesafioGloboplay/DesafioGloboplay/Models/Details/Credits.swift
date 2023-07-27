//
//  Credits.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 27/07/23.
//

import Foundation

// MARK: - Credits
struct Credits: Codable {
    var cast, crew: [Cast]?
    var id: Int?
}

// MARK: - Cast
struct Cast: Codable {
    var adult: Bool?
    var gender, id: Int?
    var knownForDepartment: String?
    var name, originalName: String?
    var popularity: Double?
    var profilePath: String?
    var character, creditID: String?
    var order: Int?
    var department: String?
    var job: String?
    var castID: Int?
    
    enum CodingKeys: String, CodingKey {
        case adult, gender, id
        case knownForDepartment = "known_for_department"
        case name
        case originalName = "original_name"
        case popularity
        case profilePath = "profile_path"
        case character
        case castID = "cast_id"
        case creditID = "credit_id"
        case order, department, job
    }
}
