//
//  NetworkClient.swift
//  MoviesApp
//
//  Created by Gustavo Tiecker on 22/02/22.
//

import Foundation

final class NetworkClient {

    func getMoviesList(id: Int, completion: (@escaping (Result<[Movie], APIError>) -> Void)) {
        NetworkManager.shared.request(endPoint: .listMovies(id)) { response in
            switch response {
            case .success(let data):
                do {
                    let decoder = JSONDecoder()
                    let movies = try decoder.decode([Movie].self, from: data)
                    completion(.success(movies))
                } catch {
                    completion(.failure(.invalidData))
                }
            case .failure(_):
                completion(.failure(.unableToComplete))
            }
        }
    }
}
