//
//  DataPersistenceManager.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 02/03/22.
//

import Foundation
import UIKit
import CoreData

class DataPersistenceManager {
    
    // MARK: - Properties
    
    enum DataBaseError: Error {
        case failToSaveLocalMovie
        case failToFetchLocalMovies
        case failedToDeleteData
        
        var errorDescription: String {
            switch self {
            case .failToSaveLocalMovie:
                return "Falha ao adicionar item à lista do usuário."
            case .failToFetchLocalMovies:
                return "Falha ao carregar os itens da lista do usuário."
            case .failedToDeleteData:
                return "Falha ao deletar o item da lista do usuário."
            }
        }
    }
    
    static let shared = DataPersistenceManager()
    
    
    // MARK: - Helper Methods
    
    func addMovieToMyList(viewModel: Movie, completion: @escaping(Result<Void, Error>) -> Void ) {
        guard let appDelegate = UIApplication.shared.delegate as? AppDelegate else { return }
        
        let context = appDelegate.persistentContainer.viewContext
        
        let movie = MovieModel(context: context)
        
        movie.originCountry = viewModel.originCountry?.first
        movie.originalTitle = viewModel.originalTitle
        movie.overview = viewModel.overview
        movie.originalName = viewModel.originalName
        movie.id = Int64(viewModel.id ?? 0)
        movie.posterPath = viewModel.posterPath
        movie.firstAirDate = viewModel.firstAirDate
        movie.urlImage = viewModel.urlImage
        movie.name = viewModel.name
        movie.title = viewModel.title
        
        do {
            try context.save()
            completion(.success(()))
        }
        catch {
            completion(.failure(DataBaseError.failToSaveLocalMovie))
            AlertUtils.showAlert(message: "Falha ao adicionar item à lista. Por favor, tente novamente.")
            print(error.localizedDescription)
        }
    }
    
    func fetchMoviesFromDataBaseLocal(completion: @escaping(Result<[MovieModel], Error>) -> Void){
        guard let appDelegate = UIApplication.shared.delegate as? AppDelegate else { return }
        
        let context = appDelegate.persistentContainer.viewContext
        
        let request: NSFetchRequest<MovieModel>
        
        request = MovieModel.fetchRequest()
        
        do {
            let moviesResponse = try context.fetch(request)
            completion(.success(moviesResponse))
        }
        catch {
            completion(.failure(DataBaseError.failToFetchLocalMovies))
            AlertUtils.showAlert(message: "Falha ao carregar a lista. Por favor, tente novamente.")

        }
    }
    
    func deleteTitleWith(model: MovieModel, completion: @escaping (Result<Void, Error>)-> Void) {
        
        guard let appDelegate = UIApplication.shared.delegate as? AppDelegate else {
            return
        }
        
        let context = appDelegate.persistentContainer.viewContext
        
        
        context.delete(model)
        
        do {
            try context.save()
            completion(.success(()))
        } catch {
            completion(.failure(DataBaseError.failedToDeleteData))
        }
        
    }
}
