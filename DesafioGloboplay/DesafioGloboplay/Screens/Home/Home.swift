//
//  ContentView.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import SwiftUI

struct Home: View {
    
    @StateObject var viewModel: HomeViewModel = HomeViewModel()
    
    @State var goToDetails = false
    @State var isMovie = true
    @State var selectedItem: Result = movieMock
    
    var body: some View {
        NavigationView{
            VStack(alignment: .leading){
                //https://api.themoviedb.org/3/trending/all/day
                //Fimes, séries e artistas populares
                Text("Nos Cinemas").font(.system(size: 24, weight: .bold))
                HorizontalList(results: viewModel.movieList?.results) { result in
                    goToDetails(result)
                }
                Text("Na TV").font(.system(size: 24, weight: .bold))
                HorizontalList(results: viewModel.tvShowsList?.results){ result in
                    goToDetails(result)
                }
                
                NavigationLink(destination: Details(item: selectedItem), isActive: $goToDetails) {}
                
            }.toolbar(content: {
                HomeToolbar()
            }).padding(20)
            
        }.onAppear(perform: {
            viewModel.getMovieList()
            viewModel.getTVShowsList()
        })
    }
    
    func goToDetails(_ item: Result){
        self.$selectedItem.wrappedValue = item
        self.goToDetails = true
    }
}

#Preview {
    Home()
}
