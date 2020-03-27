//
//  RequestHelper.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 25/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import Foundation

class RequestHelper {
    static func send(request: URLRequest, _ completion: @escaping (_ error: Error?, _ dict: [String : AnyObject]?) -> ()) {
        let session = URLSession.shared
        
        let task = session.dataTask(with: request) { (data, response, error) in
            if error != nil {
                completion(error, nil)
            }
            
            guard let data = data else { return }
            
            do {
                if let json = try JSONSerialization.jsonObject(with: data, options: .mutableContainers) as? [String : AnyObject] {
                    completion(nil, json)
                }
                
            } catch let error {
                completion(error, nil)
                
            }
            
        }
        
        task.resume()
    }
}
