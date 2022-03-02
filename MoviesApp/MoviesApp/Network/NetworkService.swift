//
//  NetworkService.swift
//  MoviesApp
//
//  Created by Gustavo Tiecker on 22/02/22.
//

import UIKit

protocol NetworkServiceProtocol {
    func getMoviesList(id: Int, completion: (@escaping (Result<MovieList, APIError>) -> Void))
    func downloadImage(from urlString: String, completion: @escaping (UIImage?) -> Void)
}

final class NetworkService: NetworkServiceProtocol {

    func getMoviesList(id: Int, completion: (@escaping (Result<MovieList, APIError>) -> Void)) {
        NetworkManager.shared.request(endPoint: .listMovies(id)) { response in
            switch response {
            case .success(let data):
                do {
                    let decoder = JSONDecoder()
                    let movies = try decoder.decode(MovieList.self, from: data)
                    completion(.success(movies))
                } catch {
                    completion(.failure(.invalidData))
                }
            case .failure(_):
                completion(.failure(.unableToComplete))
            }
        }
    }
    
    func downloadImage(from urlString: String, completion: @escaping (UIImage?) -> Void) {
        let fullPath = "https://image.tmdb.org/t/p/w500\(urlString)"
        
        guard let url = URL(string: fullPath) else {
            completion(nil)
            return
        }
        
        let task = URLSession.shared.dataTask(with: url) { data, response, error in
            guard error == nil,
                let response = response as? HTTPURLResponse, response.statusCode == 200,
                let data = data,
                let image = UIImage(data: data) else {
                    completion(nil)
                    return
                }
            completion(image)
        }
        
        task.resume()
    }
}
