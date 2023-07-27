//
//  DetailsTabView.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import SwiftUI

struct DetailsTabView: View {
    
    //https://api.themoviedb.org/3/movie/{movie_id}
    //Detalhes do filme
    
    //https://api.themoviedb.org/3/movie/{movie_id}/credits
    //Elenco do filme
    
    //https://api.themoviedb.org/3/tv/{series_id}
    //detalhes da série
    
    //https://api.themoviedb.org/3/tv/{series_id}/credits
    //elenco da série
    
    
    var item: Result
    
    var body: some View {
        VStack(alignment: .leading){
            Text("Ficha Técnica").font(.system(size: 22, weight: .bold))
            Spacer()
            Text("Título original: \(item.getTitle() ?? String())")
            Text("Gênero: <<>>") //Request p/detalhes
            Text("Episódios: <<>>") //Mesma request p/ detalhes
            Text("Ano de produção: <<>>>>")//TODO: pegar ano
            Text("País: <<>>")
            Text("Elenco: <<>>") //Request p/elenco
            Text("Disponível até: <<>>") //last-air-date
            
            
        }
    }
}

#Preview {
    DetailsTabView(item: movieMock)
}
