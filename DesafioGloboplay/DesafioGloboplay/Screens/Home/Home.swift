//
//  ContentView.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import SwiftUI

struct Home: View {
    
    @StateObject var viewModel: HomeViewModel = HomeViewModel()
    
    var body: some View {
        NavigationView{
            VStack(alignment: .leading){
                //https://api.themoviedb.org/3/trending/all/day
                //Fimes, séries e artistas populares
                Text("Nos Cinemas").font(.system(size: 24, weight: .bold))
                HorizontalList(results: viewModel.movieList?.results)
                Text("Na TV").font(.system(size: 24, weight: .bold))
                HorizontalList(results: viewModel.tvShowsList?.results)
            }.toolbar(content: {
                HomeToolbar()
            }).padding(20)
            
        }.onAppear(perform: {
            viewModel.getMovieList()
            viewModel.getTVShowsList()
        })
    }
}

#Preview {
    Home()
}
