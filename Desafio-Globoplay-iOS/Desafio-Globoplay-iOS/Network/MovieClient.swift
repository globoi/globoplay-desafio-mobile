//
//  MovieClient.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 23/02/22.
//

import Foundation

/// Service responsible for calling the API
final class MovieClient {
    
    private let topRatedMoviesURL = Constants.ProductionServer.BASE_URL + "/movie/top_rated?api_key=" + Constants.APIParameterKey.API_KEY + "&language=pt-BR"
    private let trendingMoviesURL = Constants.ProductionServer.BASE_URL + "/trending/movie/day?api_key=" + Constants.APIParameterKey.API_KEY + "&language=pt-BR"
    private let trendingTVURL = Constants.ProductionServer.BASE_URL + "/trending/tv/day?api_key=" + Constants.APIParameterKey.API_KEY + "&language=pt-BR"
    private let popularMoviesURL = Constants.ProductionServer.BASE_URL + "/movie/popular?api_key=" + Constants.APIParameterKey.API_KEY + "&language=pt-BR"
    private let upcomingMoviesURL = Constants.ProductionServer.BASE_URL + "/movie/upcoming?api_key=" + Constants.APIParameterKey.API_KEY + "&language=pt-BR"
    private lazy var moviesImagesURL = Constants.ProductionServer.IMAGE_URL
    
    private let discoverMoviesURL = Constants.ProductionServer.BASE_URL + "/discover/movie?api_key=" + Constants.APIParameterKey.API_KEY + "&language=pt-BR&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate"
    private let searchMoviesAndSeriesURL = Constants.ProductionServer.BASE_URL + "/search/movie?api_key=" + Constants.APIParameterKey.API_KEY + "&language=pt-BR" + "&query="
    
    private let serachTraillerMoviesURL = Constants.APIYouTubeKey.YOUTUBE_BASE_URL + "&key=" + Constants.APIYouTubeKey.API_YOUTUBE_KEY + "&language=pt-BR" + "&q="
    
    static let shared: MovieClient = MovieClient()
    
    // MARK:- Properties
    private enum NetworkError: LocalizedError {
        case urlError
        case notFound
        case badRequest
        case serverError
        case noDataError
        case unknownError
        
        var errorDescription: String {
            switch self {
            case .urlError:
                return "Can not create URL object from provided string"
            case .notFound:
                return "Not Found"
            case .badRequest:
                return "Bad request"
            case .serverError:
                return "Internal Server Error"
            case .noDataError:
                return "No data sent by the server"
            case .unknownError:
                return "Something went wrong."
            }
        }
    }
    
    private let urlSession: URLSession = URLSession(configuration: .default)
    
    func request(urlString: String, completion: @escaping (Result<Data, Error>) -> Void) {
        guard let url = URL(string: urlString) else {
            completion(.failure(NetworkError.urlError))
            return
        }
        
        urlSession.dataTask(with: url) { data, response, error in
            if let error = error {
                completion(.failure(error))
                return
            }
            
            if let response = response as? HTTPURLResponse, let error = self.handle(statusCode: response.statusCode) {
                completion(.failure(error))
            }
            
            if let data = data {
                completion(.success(data))
            }
            else {
                completion(.failure(NetworkError.noDataError))
            }
        }.resume()
    }
    
    func getTopRatedMovies(completion: @escaping (Result<[Movie]?, Error>) -> Void) {
        self.request(urlString: topRatedMoviesURL) { result in
            switch result {
            case .success(let data):
                do {
                    let response = try JSONDecoder().decode(MoviesAPIResponse.self, from: data)
                    completion(.success(response.results))
                }
                catch {
                    completion(.failure(NetworkError.urlError))
                }
            case .failure(let error):
                completion(.failure(error))
            }
        }
    }
    
    func getTrendingMovies(completion: @escaping (Result<[Movie]?, Error>) -> Void) {
        self.request(urlString: trendingMoviesURL) { result in
            switch result {
            case .success(let data):
                do {
                    let response = try JSONDecoder().decode(MoviesAPIResponse.self, from: data)
                    completion(.success(response.results))
                }
                catch {
                    completion(.failure(NetworkError.urlError))
                }
            case .failure(let error):
                completion(.failure(error))
            }
        }
    }
    
    func getTrendingTV(completion: @escaping (Result<[Movie]?, Error>) -> Void) {
        self.request(urlString: trendingTVURL) { result in
            switch result {
            case .success(let data):
                do {
                    let response = try JSONDecoder().decode(MoviesAPIResponse.self, from: data)
                    completion(.success(response.results))
                }
                catch {
                    completion(.failure(NetworkError.urlError))
                }
            case .failure(let error):
                completion(.failure(error))
            }
        }
    }
    
    func getPopularMovies(completion: @escaping (Result<[Movie]?, Error>) -> Void) {
        self.request(urlString: popularMoviesURL) { result in
            switch result {
            case .success(let data):
                do {
                    let response = try JSONDecoder().decode(MoviesAPIResponse.self, from: data)
                    completion(.success(response.results))
                }
                catch {
                    completion(.failure(NetworkError.urlError))
                }
            case .failure(let error):
                completion(.failure(error))
            }
        }
    }
    
    func getUpcomingMovies(completion: @escaping (Result<[Movie]?, Error>) -> Void) {
        self.request(urlString: upcomingMoviesURL) { result in
            switch result {
            case .success(let data):
                do {
                    let response = try JSONDecoder().decode(MoviesAPIResponse.self, from: data)
                    completion(.success(response.results))
                }
                catch {
                    completion(.failure(NetworkError.urlError))
                }
            case .failure(let error):
                completion(.failure(error))
            }
        }
    }
    
    func getDiscoverMovies(completion: @escaping (Result<[Movie]?, Error>) -> Void) {
        self.request(urlString: discoverMoviesURL) { result in
            switch result {
            case .success(let data):
                do {
                    let response = try JSONDecoder().decode(MoviesAPIResponse.self, from: data)
                    completion(.success(response.results))
                }
                catch {
                    completion(.failure(NetworkError.urlError))
                }
            case .failure(let error):
                completion(.failure(error))
            }
        }
    }
    
    func search(with query: String, completion: @escaping (Result<[Movie]?, Error>) -> Void) {
        
        guard let query = query.addingPercentEncoding(withAllowedCharacters: .urlHostAllowed) else { return }
        let searchURL = "\(searchMoviesAndSeriesURL)\(query)"
        
        self.request(urlString: searchURL) { result in
            switch result {
            case .success(let data):
                do {
                    let response = try JSONDecoder().decode(MoviesAPIResponse.self, from: data)
                    completion(.success(response.results))
                }
                catch {
                    completion(.failure(NetworkError.urlError))
                }
            case .failure(let error):
                completion(.failure(error))
            }
        }
    }
    
    func getMovieTrailler(with query: String, completion: @escaping (Result<YouTubeVideoItem?, Error>) -> Void) {
        guard let query = query.addingPercentEncoding(withAllowedCharacters: .urlHostAllowed) else { return }
        let searchURL = "\(serachTraillerMoviesURL)\(query)"
        
        self.request(urlString: searchURL) { result in
            switch result {
            case .success(let data):
                do {
                    let response = try JSONDecoder().decode(YouTubeSearchAPIResponse.self, from: data)
                    completion(.success(response.items?[0]))
                }
                catch {
                    completion(.failure(NetworkError.urlError))
                }
            case .failure(let error):
                completion(.failure(error))
            }
        }
    }
    
    func searchSelectedMovie(with movieID: Int?, completion: @escaping (Result<Movie?, Error>) -> Void) {
        
        guard let movieID = movieID else { return }
        let searchURL = "\(Constants.ProductionServer.BASE_URL)/movie/\(movieID)?api_key=8bd74769280804b48ea517b197e125c0&language=pt-BR"
        
        self.request(urlString: searchURL) { result in
            switch result {
            case .success(let data):
                do {
                    let response = try JSONDecoder().decode(MoviesAPIResponse.self, from: data)
                    completion(.success(response.results?[0]))
                }
                catch {
                    completion(.failure(NetworkError.urlError))
                }
            case .failure(let error):
                completion(.failure(error))
            }
        }
    }
    
    private func handle(statusCode: Int) -> NetworkError? {
        switch statusCode {
        case 200...299:
            return nil
        case 300...399:
            return nil
        case 400...499:
            return .badRequest
        case 404:
            return .notFound
        case 500...599:
            return .serverError
        default:
            return .unknownError
        }
    }
}