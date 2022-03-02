//
//  Splash.swift
//  SmartMic
//
//  Created by Marcel Mendes Filho on 08/08/19.
//  Copyright © 2019 Marcel Mendes Filho. All rights reserved.
//

import Foundation

struct Splash: Codable{
    var main_title: String?
    var footnote_icon: String?
    var footnote_text: String?
    var splashItems: [SplashItem?]
    
    enum CodingKeys: String, CodingKey{
        case footnote_icon, footnote_text, main_title, splashItems
    }
}

struct SplashItem: Codable, Equatable{
    var title: String?
    var icon: String?
    var text: String?
    
    enum CodingKeys: String, CodingKey{
        case title, icon, text
    }
}

class SplashHelper{
    static func encode(splash: Splash?) -> String?{
        
        if let splash = splash{
            do{
                let encoded = try JSONEncoder().encode(splash)
                let string = String(data: encoded, encoding: .utf8)
                return string
            } catch{
                print(error.localizedDescription)
                return nil
            }
        } else {
            return nil
        }
    }
    
    static func decode(jsonData: Data?) -> Splash?{
        if let jsonData = jsonData{
            do{
                let decoded = try JSONDecoder().decode(Splash.self, from: jsonData)
                return decoded
            } catch {
                print(error.localizedDescription)
                return nil
            }
        } else {
            return nil
        }
    }
    
    static func decode(jsonString: String?) -> Splash?{
        if let jsonString = jsonString, let data = jsonString.data(using: .utf8){
            do {
                let decoded = try JSONDecoder().decode(Splash.self, from: data)
                return decoded
            } catch {
                print(error.localizedDescription)
                return nil
            }
        } else {
            return nil
        }
    }
}
