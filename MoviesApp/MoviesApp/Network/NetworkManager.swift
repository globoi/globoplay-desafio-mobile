//
//  NetworkManager.swift
//  MoviesApp
//
//  Created by Gustavo Tiecker on 22/02/22.
//

import Foundation

enum HttpMethod: String {
    case get = "GET"
    case post = "POST"
    case put = "PUT"
    case delete = "DELETE"
}

enum EndPoint {
    case listMovies(Int)
    
    func getPath() -> String {
        switch self {
        case .listMovies(let id):
            return "/list/\(id)"
        }
    }
    
    func getHttpMethod() -> HttpMethod {
        switch self {
        case .listMovies(_):
            return .get
        }
    }
}

enum APIError: String, Error {
    case invalidRequest = "Invalid request. Please try again."
    case unableToComplete = "Unable to complete your request. Please check your internet connection."
    case invalidResponse = "Invalid response from the server. Please try again."
    case invalidData = "The data receveid from the server was invalid. Please try again."
}

class NetworkManager {
    
    static let shared = NetworkManager()
    
    private let apiKey = "030b11596288485007a672e552a72ecc"
    private let baseURL = "https://api.themoviedb.org/3"
    
    private init() {}
    
    func request(body: String = "", endPoint: EndPoint, completion: @escaping (Result<Data, APIError>) -> Void) {
        
        guard let url = URL(string: baseURL + endPoint.getPath() + "?api_key=" + apiKey) else {
            completion(.failure(.invalidRequest))
            return
        }
        
        let task = URLSession.shared.dataTask(with: url) { data, response, error in
            
            if let _ = error {
                completion(.failure(.unableToComplete))
                return
            }
            
            guard let response = response as? HTTPURLResponse, response.statusCode == 200 else {
                completion(.failure(.invalidResponse))
                return
            }
            
            guard let data = data else {
                completion(.failure(.invalidData))
                return
            }
            
            completion(.success(data))
        }
        task.resume()
    }
}
