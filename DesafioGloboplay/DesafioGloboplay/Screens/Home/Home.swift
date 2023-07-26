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
            
            VStack{
                ScrollView(.horizontal){
                    HStack{
                        if let movieList = viewModel.movieList?.results{
                            ForEach(movieList, id: \.id){movie in
                                AsyncImage(url: URL.init(string: "\(posterURL)\(movie.posterPath ?? "")")){image in
                                    image.resizable().aspectRatio(contentMode: .fit)
                                } placeholder: {
                                    ProgressView()
                                }.frame(width: 150, height: 300)
                            }
                        }else{
                            Text("Erro ao carregar lista")
                        }
                    }
                }.padding(20)
            }.toolbar(content: {
                ToolbarItem(placement: .principal) {
                    VStack{
                        Image(assets.images.logo.rawValue, bundle: .main).resizable().aspectRatio(contentMode: .fit)
                    }.padding(10)
                }
            })
            
        }.onAppear(perform: {
            viewModel.getMovieList()
        })
    }
}

#Preview {
    Home()
}
