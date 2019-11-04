//
//  ApplicationService.swift
//  Desafio GloboPlay
//
//  Created by Filipo Negrao on 03/11/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import Foundation
import Alamofire

private var data = ApplicationService()

public class ApplicationService : NSObject {
    
    private var apiKey = "25776c3ca92054f579cdfdaf6dedd23b"
    private var language = "pt-BR"
    
    public class var sharedInstance: ApplicationService {
        return data
    }
    
    // MARK: Métodos auxiliares e de controle
    
    
    // MARK: Métodos de Requisição
    
    /** Método responsável por recuperar os generos da API */
    public func getGenres(callback: @escaping((_ genres: [Genre],_ error: String?)->())) {
        // Instancia url
        var url = "\(API_URL)/genre/tv/list"
        url += "?api_key=" + self.apiKey
        url += "&language=" + self.language
        let escapedAddress = url.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed)
        
        // Instancia resultado
        var result = [Genre]()
        
        AF.request(
            escapedAddress!,
            method: .get,
            parameters: nil,
            encoding: JSONEncoding.default,
            headers: nil, interceptor: nil).response { (response: AFDataResponse<Data?>) in
                // Trata o erro
                guard let data = response.data else {
                    let error = response.error
                    if let errorString = error?.localizedDescription {
                        if errorString.contains("Internet connection appears to be offline") {
                            callback(result, ERROR_NO_CONNECTION)
                        }
                    }
                    callback(result, ERROR_SERVER_MESSAGE)
                    return
                }
                // Trata a resposta
                do {
                    guard let json = try JSONSerialization.jsonObject(with: data, options: .allowFragments) as? [String : Any] else {
                        callback(result, ERROR_SERVER_MESSAGE)
                        return
                    }
                    if let genresJson = json["genres"] as? [[String: Any]] {
                        for genreJson in genresJson {
                            let genre = Genre.fromDict(dict: genreJson)
                            result.append(genre)
                        }
                    }
                    callback(result, nil)
                    
                } catch {
                    callback(result, ERROR_SERVER_MESSAGE)
                }
        }
    }
    
    /** Método responsável por recuperar os programas de tv populares da API */
    public func getTVPopular(callback: @escaping((_ genres: [Card],_ error: String?)->())) {
        // Instancia url
        var url = "\(API_URL)/tv/popular"
        url += "?api_key=" + self.apiKey
        url += "&language=" + self.language
        let escapedAddress = url.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed)
        
        // Instancia resultado
        var result = [Card]()
        
        AF.request(
            escapedAddress!,
            method: .get,
            parameters: nil,
            encoding: JSONEncoding.default,
            headers: nil, interceptor: nil).response { (response: AFDataResponse<Data?>) in
                // Trata o erro
                guard let data = response.data else {
                    let error = response.error
                    if let errorString = error?.localizedDescription {
                        if errorString.contains("Internet connection appears to be offline") {
                            callback(result, ERROR_NO_CONNECTION)
                        }
                    }
                    callback(result, ERROR_SERVER_MESSAGE)
                    return
                }
                // Trata a resposta
                do {
                    guard let json = try JSONSerialization.jsonObject(with: data, options: .allowFragments) as? [String : Any] else {
                        callback(result, ERROR_SERVER_MESSAGE)
                        return
                    }
                    if let cardsJson = json["results"] as? [[String: Any]] {
                        for cardJson in cardsJson {
                            let card = Card.fromDict(dict: cardJson)
                            result.append(card)
                        }
                    }
                    callback(result, nil)
                    
                } catch {
                    callback(result, ERROR_SERVER_MESSAGE)
                }
        }
    }
    
    /** Método responsável por recuperar os programas de tv mais bem votados da API */
    public func getTVTopRated(callback: @escaping((_ genres: [Card],_ error: String?)->())) {
        // Instancia url
        var url = "\(API_URL)/tv/top_rated"
        url += "?api_key=" + self.apiKey
        url += "&language=" + self.language
        let escapedAddress = url.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed)
        
        // Instancia resultado
        var result = [Card]()
        
        AF.request(
            escapedAddress!,
            method: .get,
            parameters: nil,
            encoding: JSONEncoding.default,
            headers: nil, interceptor: nil).response { (response: AFDataResponse<Data?>) in
                // Trata o erro
                guard let data = response.data else {
                    let error = response.error
                    if let errorString = error?.localizedDescription {
                        if errorString.contains("Internet connection appears to be offline") {
                            callback(result, ERROR_NO_CONNECTION)
                        }
                    }
                    callback(result, ERROR_SERVER_MESSAGE)
                    return
                }
                // Trata a resposta
                do {
                    guard let json = try JSONSerialization.jsonObject(with: data, options: .allowFragments) as? [String : Any] else {
                        callback(result, ERROR_SERVER_MESSAGE)
                        return
                    }
                    if let cardsJson = json["results"] as? [[String: Any]] {
                        for cardJson in cardsJson {
                            let card = Card.fromDict(dict: cardJson)
                            result.append(card)
                        }
                    }
                    callback(result, nil)
                    
                } catch {
                    callback(result, ERROR_SERVER_MESSAGE)
                }
        }
    }
    
    /** Método responsável por recuperar os filmes da API */
    public func getMovies(callback: @escaping((_ genres: [Card],_ error: String?)->())) {
        // Instancia url
        var url = "\(API_URL)/movie/popular"
        url += "?api_key=" + self.apiKey
        url += "&language=" + self.language
        let escapedAddress = url.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed)
        
        // Instancia resultado
        var result = [Card]()
        
        AF.request(
            escapedAddress!,
            method: .get,
            parameters: nil,
            encoding: JSONEncoding.default,
            headers: nil, interceptor: nil).response { (response: AFDataResponse<Data?>) in
                // Trata o erro
                guard let data = response.data else {
                    let error = response.error
                    if let errorString = error?.localizedDescription {
                        if errorString.contains("Internet connection appears to be offline") {
                            callback(result, ERROR_NO_CONNECTION)
                        }
                    }
                    callback(result, ERROR_SERVER_MESSAGE)
                    return
                }
                // Trata a resposta
                do {
                    guard let json = try JSONSerialization.jsonObject(with: data, options: .allowFragments) as? [String : Any] else {
                        callback(result, ERROR_SERVER_MESSAGE)
                        return
                    }
                    if let cardsJson = json["results"] as? [[String: Any]] {
                        for cardJson in cardsJson {
                            let card = Card.fromDict(dict: cardJson)
                            result.append(card)
                        }
                    }
                    callback(result, nil)
                    
                } catch {
                    callback(result, ERROR_SERVER_MESSAGE)
                }
        }
    }
    
    
    
}
