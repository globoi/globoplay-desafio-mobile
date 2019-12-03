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
    
    public var mapGenres = [Int64: Genre]()
    
    public class var sharedInstance: ApplicationService {
        return data
    }
    
    // MARK: Métodos auxiliares e de controle
    
    override init() {
        super.init()
        self.getTvGenres { (genres: [Genre], error: String?) in
            print("generos carregados")
        }
        self.getMovieGenres { (genres: [Genre], error: String?) in
            print("generos de filmes carregados")
        }
    }
    
    func addFavorite(card: Card) {
        let dict = card.toDict()
        
        // Verifica se o array de favoritos ja existe
        if !PlistManager.sharedInstance.keyAlreadyExists(key: "favorites") {
            let favorites = [[String: Any]]()
            PlistManager.sharedInstance.addNewItemWithKey(key: "favorites", value: favorites as AnyObject)
        }
        var favorites = PlistManager.sharedInstance.getValueForKey(key: "favorites") as! [[String: Any]]
        if !ApplicationService.sharedInstance.checkAlreadyAdded(card: card) {
            favorites.append(dict)
            PlistManager.sharedInstance.saveValue(value: favorites, forKey: "favorites")
        }
    }
    
    func removeFavorite(card: Card) {
        // Verifica se o array de favoritos ja existe
        if !PlistManager.sharedInstance.keyAlreadyExists(key: "favorites") {
            return
        }
        let favorites = PlistManager.sharedInstance.getValueForKey(key: "favorites") as! [[String: Any]]
        var newFavorites = [[String: Any]]()
        for favorite in favorites {
            if favorite["id"] as! Int64 != card.id {
                newFavorites.append(favorite)
            }
        }
        
        PlistManager.sharedInstance.saveValue(value: newFavorites, forKey: "favorites")
    }
    
    func getFavorites() -> [Card] {
        var results = [Card]()
        if var favorites = PlistManager.sharedInstance.getValueForKey(key: "favorites") as? [[String: Any]] {
            for favorite in favorites {
                let card = Card.fromDict(dict: favorite)
                results.append(card)
            }
        }
        
        return results
    }
    
    func checkAlreadyAdded(card: Card) -> Bool {
        let id = card.id
        let favorites = self.getFavorites()
        for favorite in favorites {
            if favorite.id == id {
                return true
            }
        }
        return false
    }
    
    
    // MARK: Métodos de Requisição
    
    /** Método responsável por recuperar os generos da API */
    public func getTvGenres(callback: @escaping((_ genres: [Genre],_ error: String?)->())) {
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
                            self.mapGenres[genre.id] = genre
                            result.append(genre)
                        }
                    }
                    callback(result, nil)
                    
                } catch {
                    callback(result, ERROR_SERVER_MESSAGE)
                }
        }
    }
    
    /** Método responsável por recuperar os generos da API */
    public func getMovieGenres(callback: @escaping((_ genres: [Genre],_ error: String?)->())) {
        // Instancia url
        var url = "\(API_URL)/genre/movie/list"
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
                            self.mapGenres[genre.id] = genre
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
    public func getTVPopular(genres: [Int64], page: Int, search: String?, callback: @escaping((_ genres: [Card], _ pages: Int64, _ error: String?)->())) {
        // Instancia url
        var url = "\(API_URL)"
        if (search != nil) {
            url += "/search/tv"
        } else {
            url += "/tv/popular"
        }
        url += "?api_key=" + self.apiKey
        url += "&language=" + self.language
        // Verifica os filtros
        if genres.count > 0 {
            url += "&with_genres="
            var index = 0
            for genre in genres {
                if index != 0 {
                    url += ","
                }
                url += "\(genre)"
                index = index + 1
            }
        }
        // Pagina
        url += "&page=\(page+1)"
        // Busca
        if let search = search {
            url += "&query=\(search)"
        }
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
                            callback(result, 0, ERROR_NO_CONNECTION)
                        }
                    }
                    callback(result, 0, ERROR_SERVER_MESSAGE)
                    return
                }
                // Trata a resposta
                do {
                    guard let json = try JSONSerialization.jsonObject(with: data, options: .allowFragments) as? [String : Any] else {
                        callback(result, 0, ERROR_SERVER_MESSAGE)
                        return
                    }
                    if let cardsJson = json["results"] as? [[String: Any]] {
                        for cardJson in cardsJson {
                            let card = Card.fromDict(dict: cardJson)
                            result.append(card)
                        }
                    }
                    var pages : Int64 = 0
                    if let totalPages = json["total_pages"] as? Int64 {
                        pages = totalPages
                    }
                    callback(result, pages,  nil)
                    
                } catch {
                    callback(result, 0, ERROR_SERVER_MESSAGE)
                }
        }
    }
    
    /** Método responsável por recuperar os programas de tv mais bem votados da API */
    public func getTVTopRated(genres: [Int64], page: Int, search: String?, callback: @escaping((_ genres: [Card], _ pages: Int64, _ error: String?)->())) {
        // Instancia url
        var url = "\(API_URL)"
        if (search != nil) {
            url += "/search/tv"
        } else {
            url += "/tv/top_rated"
        }
        url += "?api_key=" + self.apiKey
        url += "&language=" + self.language
        // Verifica os filtros
        if genres.count > 0 {
            url += "&with_genres="
            var index = 0
            for genre in genres {
                if index != 0 {
                    url += ","
                }
                url += "\(genre)"
                index = index + 1
            }
        }
        // Pagina
        url += "&page=\(page+1)"
        // Busca
        if let search = search {
            url += "&query=\(search)"
        }
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
                            callback(result, 0, ERROR_NO_CONNECTION)
                        }
                    }
                    callback(result,0,  ERROR_SERVER_MESSAGE)
                    return
                }
                // Trata a resposta
                do {
                    guard let json = try JSONSerialization.jsonObject(with: data, options: .allowFragments) as? [String : Any] else {
                        callback(result, 0, ERROR_SERVER_MESSAGE)
                        return
                    }
                    if let cardsJson = json["results"] as? [[String: Any]] {
                        for cardJson in cardsJson {
                            let card = Card.fromDict(dict: cardJson)
                            result.append(card)
                        }
                    }
                    var pages : Int64 = 0
                    if let totalPages = json["total_pages"] as? Int64 {
                        pages = totalPages
                    }
                    callback(result, pages, nil)
                    
                } catch {
                    callback(result, 0, ERROR_SERVER_MESSAGE)
                }
        }
    }
    
    /** Método responsável por recuperar os filmes da API */
    public func getMovies(genres: [Int64], page: Int, search: String?, callback: @escaping((_ cards: [Card], _ totalPages: Int64,_ error: String?)->())) {
        // Instancia url
        var url = "\(API_URL)"
        if (search != nil) {
            url += "/search/movie"
        } else {
            url += "/movie/popular"
        }
        url += "?api_key=" + self.apiKey
        url += "&language=" + self.language
        // Verifica os filtros
        if genres.count > 0 {
            url += "&with_genres="
            var index = 0
            for genre in genres {
                if index != 0 {
                    url += ","
                }
                url += "\(genre)"
                index = index + 1
            }
        }
        // Pagina
        url += "&page=\(page+1)"
        // Busca
        if let search = search {
            url += "&query=\(search)"
        }
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
                            callback(result, 0, ERROR_NO_CONNECTION)
                        }
                    }
                    callback(result, 0, ERROR_SERVER_MESSAGE)
                    return
                }
                // Trata a resposta
                do {
                    guard let json = try JSONSerialization.jsonObject(with: data, options: .allowFragments) as? [String : Any] else {
                        callback(result, 0, ERROR_SERVER_MESSAGE)
                        return
                    }
                    if let cardsJson = json["results"] as? [[String: Any]] {
                        for cardJson in cardsJson {
                            let card = Card.fromDict(dict: cardJson, type: CardType.MOVIE)
                            card.movie = true
                            result.append(card)
                        }
                    }
                    var pages : Int64 = 0
                    if let totalPages = json["total_pages"] as? Int64 {
                        pages = totalPages
                    }
                    callback(result, pages, nil)
                    
                } catch {
                    callback(result, 0, ERROR_SERVER_MESSAGE)
                }
        }
    }
    
    /** Método responsável por recuperar os programas de tv similares a um especifico da API */
    public func getTVSimilars(card: Card, callback: @escaping((_ cards: [Card],_ error: String?)->())) {
        // Instancia url
        var url = "\(API_URL)/tv/\(card.id)/similar"
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
    
    /** Método responsável por recuperar os programas de tv similares a um especifico da API */
    public func getTVDetail(id: Int, callback: @escaping((_ card: Card,_ error: String?)->())) {
        // Instancia url
        var url = "\(API_URL)/tv/\(id)"
        url += "?api_key=" + self.apiKey
        url += "&language=" + self.language
        let escapedAddress = url.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed)
        
        // Instancia resultado
        var result = Card()
        
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
                    result = Card.fromDict(dict: json)
                    callback(result, nil)
                    
                } catch {
                    callback(result, ERROR_SERVER_MESSAGE)
                }
        }
    }
    
    /** Método responsável por recuperar os detalhes de um filme especifico da API */
    public func getMovieDetail(id: Int, callback: @escaping((_ card: Card,_ error: String?)->())) {
        // Instancia url
        var url = "\(API_URL)/movie/\(id)"
        url += "?api_key=" + self.apiKey
        url += "&language=" + self.language
        let escapedAddress = url.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed)
        
        // Instancia resultado
        var result = Card()
        
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
                    result = Card.fromDict(dict: json, type: CardType.MOVIE)
                    callback(result, nil)
                    
                } catch {
                    callback(result, ERROR_SERVER_MESSAGE)
                }
        }
    }
    
    
}
