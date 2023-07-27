//
//  DetailsTabView.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import SwiftUI

struct DetailsTabView: View {
    
    @ObservedObject var viewModel: DetailsTabViewModel
    
    var item: Result
    
    var body: some View {
        ScrollView{
            VStack(alignment: .leading){
                Text("Ficha Técnica").font(.system(size: 22, weight: .bold))
                Spacer()
                Text("Título original: \(item.getTitle() ?? String())")
                Text("Gênero: \(self.getGenres() ?? "")") //Request p/detalhes
                if item.getMediaType() == .tvShow{
                    Text("Episódios: <<>>") //Mesma request p/ detalhes
                }
                Text("Ano de produção: <<>>>>")//TODO: pegar ano
                Text("País: <<>>")
                Text("Elenco: \(viewModel.getCasting() ?? "")") //Request p/elenco
                if item.getMediaType() == .tvShow{
                    Text("Disponível até: <<>>") //last-air-date
                }
            }
        }
    }
    
    func getGenres() -> String?{
        if item.getMediaType() == .movie{
            return viewModel.getMovieGenres()
        }else{
            return viewModel.getTVShowGenres()
        }
    }
    
}

