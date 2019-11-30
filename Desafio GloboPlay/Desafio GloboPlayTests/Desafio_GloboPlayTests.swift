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
    
    func testGetMovies() {
        let genres = [Int64]()
        Desafio_GloboPlay.ApplicationService.sharedInstance.getMovies(genres: genres, page: 0, search: nil) { (cards: [Card], error: String?) in
            XCTAssertNil(error)
            XCTAssertTrue(cards.count != 0)
        }
    }
    
    func testGetPopular() {
        let genres = [Int64]()
        Desafio_GloboPlay.ApplicationService.sharedInstance.getTVPopular(genres: genres, page: 0, search: nil) { (cards: [Card], error: String?) in
            XCTAssertNil(error)
            XCTAssertTrue(cards.count != 0)
        }
    }
    
    func testGetTop() {
        let genres = [Int64]()
        Desafio_GloboPlay.ApplicationService.sharedInstance.getTVTopRated(genres: genres, page: 0, search: nil) { (cards: [Card], error: String?) in
            XCTAssertNil(error)
            XCTAssertTrue(cards.count != 0)
        }
    }
    
    // MARK: Testes de interacao
    
    func testFavorite() {
        let genres = [Int64]()
        Desafio_GloboPlay.ApplicationService.sharedInstance.getMovies(genres: genres, page: 0, search: nil) { (cards: [Card], error: String?) in
            XCTAssertNil(error)
            XCTAssertTrue(cards.count != 0)
            let card = cards[0]
            var favorites = ApplicationService.sharedInstance.getFavorites()
            // Garante que este card nao exista
            ApplicationService.sharedInstance.removeFavorite(card: card)
            // Armazena o numero atual de favoritos
            let favoritesCount = favorites.count
            ApplicationService.sharedInstance.addFavorite(card: card)
            favorites = ApplicationService.sharedInstance.getFavorites()
            // Compara com acrescimo que foi feito
            XCTAssertTrue(favorites.count == favoritesCount + 1)
        }
    }
    
    func testUnfavorite() {
        let genres = [Int64]()
        Desafio_GloboPlay.ApplicationService.sharedInstance.getMovies(genres: genres, page: 0, search: nil) { (cards: [Card], error: String?) in
            XCTAssertNil(error)
            XCTAssertTrue(cards.count != 0)
            let card = cards[0]
            // Insere para que exista pelo menos um favorito
            ApplicationService.sharedInstance.addFavorite(card: card)
            var favorites = ApplicationService.sharedInstance.getFavorites()
            let favoritesCount = favorites.count
            // Remove
            ApplicationService.sharedInstance.removeFavorite(card: card)
            favorites = ApplicationService.sharedInstance.getFavorites()
            // Verifica se foi removido
            if favoritesCount == 0 {
                XCTAssertTrue(favorites.count == 0)
            } else {
                XCTAssertTrue(favorites.count == favoritesCount - 1)
            }
        }
    }

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
