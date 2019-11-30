//
//  Desafio_GloboPlayTests.swift
//  Desafio GloboPlayTests
//
//  Created by Filipo Negrao on 30/11/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import XCTest
@testable import Desafio_GloboPlay

class Desafio_GloboPlayTests: XCTestCase {
    

    override func setUp() {
    }
    
    // MARK: Teste de carregamento de informacoes
    
    /* Verifica se é possível recuperar os generos corretamente*/
    func testGetMovieGenres() {
        Desafio_GloboPlay.ApplicationService.sharedInstance.getMovieGenres { (genres: [Genre], error: String?) in
            XCTAssertNil(error)
            XCTAssertTrue(genres.count > 0)
        }
    }
    
    /* Verifica se é possível recuperar os generos corretamente*/
    func testGetGenres() {
        Desafio_GloboPlay.ApplicationService.sharedInstance.getTvGenres { (genres: [Genre], error: String?) in
            XCTAssertNil(error)
            XCTAssertTrue(genres.count > 0)
        }
    }
    
    /* Verifica se é possível recuperar os filmes corretamente*/
    func testGetMovies() {
        let genres = [Int64]()
        Desafio_GloboPlay.ApplicationService.sharedInstance.getMovies(genres: genres, page: 0, search: nil) { (cards: [Card], error: String?) in
            XCTAssertNil(error)
            XCTAssertTrue(cards.count > 0)
        }
    }
    /* Verifica se é possível recuperar as series corretamente*/
    func testGetPopular() {
        let genres = [Int64]()
        Desafio_GloboPlay.ApplicationService.sharedInstance.getTVPopular(genres: genres, page: 0, search: nil) { (cards: [Card], error: String?) in
            XCTAssertNil(error)
            XCTAssertTrue(cards.count > 0)
        }
    }
    
    /* Verifica se é possível recuperar as series corretamente*/
    func testGetTop() {
        let genres = [Int64]()
        Desafio_GloboPlay.ApplicationService.sharedInstance.getTVTopRated(genres: genres, page: 0, search: nil) { (cards: [Card], error: String?) in
            XCTAssertNil(error)
            XCTAssertTrue(cards.count > 0)
        }
    }
    
    /* Verifica se é possível recuperar filmes por genero corretamente*/
    func testGetMoviesByGenre() {
        Desafio_GloboPlay.ApplicationService.sharedInstance.getTvGenres { (genres: [Genre], error: String?) in
            XCTAssertNil(error)
            XCTAssertTrue(genres.count > 0)
            // Index aleatorio
            let index = Int.random(in: 0 ... (genres.count-1))
            let genre = genres[index]
            var selectedGenres = [Int64]()
            selectedGenres.append(genre.id)
            Desafio_GloboPlay.ApplicationService.sharedInstance.getMovies(genres: selectedGenres, page: 0, search: nil) { (cards: [Card], error: String?) in
                XCTAssertNil(error)
                XCTAssertTrue(cards.count > 0)
            }
        }
    }
    
    /* Verifica se é possível recuperar filmes buscando com um texto corretamente*/
    func testGetMoviesBySearch() {
        let genres = [Int64]()
        let searchText = "Amor"
        Desafio_GloboPlay.ApplicationService.sharedInstance.getMovies(genres: genres, page: 0, search: searchText) { (cards: [Card], error: String?) in
            XCTAssertNil(error)
            XCTAssertTrue(cards.count > 0)
        }
    }
    
    /* Verifica se é possível recuperar filmes utilizando a paginacao corretamente corretamente*/
    func testGetMoviesPagination() {
        let genres = [Int64]()
        Desafio_GloboPlay.ApplicationService.sharedInstance.getMovies(genres: genres, page: 1, search: nil) { (cards: [Card], error: String?) in
            XCTAssertNil(error)
            XCTAssertTrue(cards.count > 0)
        }
    }
    
    // MARK: Testes de interacao
    
    /* Verifica se é possível favoritar um card */
    func testFavorite() {
        let genres = [Int64]()
        Desafio_GloboPlay.ApplicationService.sharedInstance.getMovies(genres: genres, page: 0, search: nil) { (cards: [Card], error: String?) in
            XCTAssertNil(error)
            XCTAssertTrue(cards.count > 0)
            // Index aleatorio
            let index = Int.random(in: 0 ... (cards.count-1))
            let card = cards[index]
            // Garante que este card nao exista
            Desafio_GloboPlay.ApplicationService.sharedInstance.removeFavorite(card: card)
            // Armazena o numero atual de favoritos
            var favorites = Desafio_GloboPlay.ApplicationService.sharedInstance.getFavorites()
            let favoritesCount = favorites.count
            Desafio_GloboPlay.ApplicationService.sharedInstance.addFavorite(card: card)
            favorites = Desafio_GloboPlay.ApplicationService.sharedInstance.getFavorites()
            // Compara com acrescimo que foi feito
            XCTAssertTrue(favorites.count == favoritesCount + 1)
        }
    }
    
    /* Verifica se é possível desfavoritar um card */
    func testUnfavorite() {
        let genres = [Int64]()
        Desafio_GloboPlay.ApplicationService.sharedInstance.getMovies(genres: genres, page: 0, search: nil) { (cards: [Card], error: String?) in
            XCTAssertNil(error)
            XCTAssertTrue(cards.count != 0)
            // Index aleatorio
            let index = Int.random(in: 0 ... (cards.count-1))
            let card = cards[index]
            // Insere para que exista pelo menos um favorito
            Desafio_GloboPlay.ApplicationService.sharedInstance.addFavorite(card: card)
            var favorites = Desafio_GloboPlay.ApplicationService.sharedInstance.getFavorites()
            let favoritesCount = favorites.count
            // Remove
            Desafio_GloboPlay.ApplicationService.sharedInstance.removeFavorite(card: card)
            favorites = Desafio_GloboPlay.ApplicationService.sharedInstance.getFavorites()
            // Verifica se foi removido
            if favoritesCount == 0 {
                XCTAssertTrue(favorites.count == 0)
            } else {
                XCTAssertTrue(favorites.count == favoritesCount - 1)
            }
        }
    }
    
//    /* Verifica se é possível favoritar todos os cards */
//    func testFavoriteAll() {
//        let genres = [Int64]()
//        Desafio_GloboPlay.ApplicationService.sharedInstance.getMovies(genres: genres, page: 0, search: nil) { (cards: [Card], error: String?) in
//            XCTAssertNil(error)
//            XCTAssertTrue(cards.count > 0)
//            for card in cards {
//                ApplicationService.sharedInstance.addFavorite(card: card)
//            }
//            let favorites = ApplicationService.sharedInstance.getFavorites()
//            // Veririca se o numero de favoritos é igual ao numero total de cards
//            XCTAssertEqual(favorites.count, cards.count)
//        }
//    }
    
   

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testExample() {
        // This is an example of a functional test case.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
    }

    func testPerformanceExample() {
        // This is an example of a performance test case.
        measure {
            // Put the code you want to measure the time of here.
        }
    }

}
