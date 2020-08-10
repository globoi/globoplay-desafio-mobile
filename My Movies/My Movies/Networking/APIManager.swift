//
//  APIManager.swift
//  My Movies
//
//  Created by Rafael Valer on 10/08/20.
//  Copyright © 2020 Rafael Valer. All rights reserved.
//

import Foundation

enum APIResponse {
    case sucess(Data)
    case error(AppError)
}

class APIManager {
    
    static let shared = APIManager()
    
    // API key (v3 Auth)
    private let apiKey = "2ace1d7948c3cddc1d038627c7a5b94f"
    //    // Base API Url
    private let baseURL = "https://api.themoviedb.org/3"
    
    private init() {}
    
    /// Method to perform requests to the server api
    /// - Parameters:
    ///   - body: Request body content
    ///   - method: HTTP method
    ///   - endPoint: Operation to perform
    ///   - completion: APIResponse with the completion status of the operation
    func request(body: String = "",
                 method: HttpMethod,
                 endPoint: EndPoint,
                 completion: @escaping ((APIResponse) -> Void)) {
        
        guard let request = asURLRequest(body: body, method: method, endPoint: endPoint) else {
            completion(.error(ApplicationError.urlNotFound))
            return
        }
        
        let task = URLSession.shared.dataTask(with: request) { (data, urlResponse, error) in
            
            guard error == nil else {
                debugPrint("Error during Request - \(error?.localizedDescription ?? "Error")")
                completion(.error(ApplicationError.commonError))
                return
            }
            
            guard let data = data else {
                debugPrint("Error during Request - MissingData")
                completion(.error(ApplicationError.missingData))
                return
            }
            
            completion(.sucess(data))
        }
        task.resume()
    }
    
    private func asURLRequest(body: String, method: HttpMethod,
                              endPoint: EndPoint) -> URLRequest? {
        
        // failed to create URL from string
        guard let url = URL(string: endPoint.getPath()) else { return nil }
        
        var request = URLRequest(url: url)
        request.httpBody = body.data(using: .utf8)
        request.httpMethod = endPoint.getHttpMethod().rawValue
        request.addValue("application/x-www-form-urlencoded", forHTTPHeaderField: "Content-Type")
        
        return request
    }
}
