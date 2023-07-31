//
//  TextConstants.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 30/07/23.
//

import Foundation


enum screenTexts{
    
    enum errors: String{
        case networkErrorText = "Ocorreu um erro de conexão. Verifique sua internet e tente novamente"
        case listLoadingError = "Erro ao carregar lista"
    }
    
    enum home: String{
        case atTheatersLabel = "Nos Cinemas"
        case onAirLabel = "Na TV"
    }
    
    enum myList: String{
        case myListIsEmpty = "Sua lista está vazia. Você pode adicionar filmes e séries aqui através do botão \"Minha Lista\", nos detalhes do filme/série"
        case myListTitle = "Minha Lista"
    }
    
    enum details: String{
        case itemAddedToList = "Adicionado"
        case myListButtonInitialState = "Minha Lista"

        case recommendationsTabTitle = "Assista Também"
        case detailsTabTitle = "Detalhes"
    }
    
    enum homeTabs: String{
        case home = "Início"
        case myList = "Minha Lista"
    }
    
    enum general: String{
        case loading = "Carregando..."
    }
    
    enum detailsTab: String{
        
        case title = "Ficha Técnica"
        case originalTitle = "Título original: "
        case gender = "Gênero: "
        case episodes = "Episódios: "
        case yearOfProduction = "Ano de produção: "
        case country = "País: "
        case cast = "Elenco: "
        case availability = "Disponível até: "
        
    }
    
}

