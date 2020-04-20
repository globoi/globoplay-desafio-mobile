//
//  DetailTab.swift
//  globoplay
//
//  Created by Marcos Curvello on 19/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI

struct InfoView: View {
    @State private var visibleTab = false
    var movie: MovieResult
    var height: CGFloat = 300
    
    var body: some View {
        GeometryReader { geo in
            VStack(alignment: .leading, spacing: 0) {
                HStack {
                    Button(action: { self.visibleTab = false }, label: {
                        Text("DETALHES")
                            .font(Font.system(size: 12))
                            .foregroundColor(.white)
                            .fontWeight(.semibold)
                            .padding()
                    })
                    Button(action: { self.visibleTab = true }, label: {
                        Text("ASSISTA TAMBÉM")
                            .font(Font.system(size: 12))
                            .foregroundColor(.white)
                            .fontWeight(.semibold)
                            .padding()
                    })
                    Spacer()
                }
                .padding(.leading, 6)
                .background(Color.black)
                
                ZStack {
                    VStack(alignment: .leading, spacing: 4.2) {
                        Text("Ficha técnica")
                            .font(Font.system(size: 16))
                            .fontWeight(.bold)
                            .foregroundColor(.white)
                            .padding(.top, 30)
                            .padding(.bottom, 18)
                        
                        HStack(alignment: .top, spacing: 3) {
                            Text("Título Original:")
                                .fontWeight(.bold)
                            Text(self.movie.originalTitle)
                        }
                        
                        HStack(alignment: .top, spacing: 5) {
                            Text("Gênero:")
                                .fontWeight(.bold)
                            Text("\(self.movie.genreIds[0])")
                        }
                        
                        HStack(alignment: .top, spacing: 5) {
                            Text("Episódios:")
                                .fontWeight(.bold)
                            Text("\(self.movie.releaseDate)")
                        }
                        
                        HStack(alignment: .top, spacing: 5) {
                            Text("Ano de produção:")
                                .fontWeight(.bold)
                            Text("\(self.movie.releaseDate)")
                        }
                        
                        HStack(alignment: .top, spacing: 5) {
                            Text("País:")
                                .fontWeight(.bold)
                            Text("\(self.movie.releaseDate)")
                        }
                        
                        HStack(alignment: .top, spacing: 5) {
                            Text("Direção:")
                                .fontWeight(.bold)
                            Text("\(self.movie.releaseDate)")
                        }
                        Spacer()
                    }
                    .font(Font.system(size: 15))
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
                    .foregroundColor(Color("systemGray3"))
                    .background(Color("backgroundGray"))
                    .zIndex(self.visibleTab ? 0 : 1)
                    
                    VStack {
                        MovieRow(title: "", movies: sampleMovies)
                            .frame(width: geo.size.width, height: self.height)
                    }
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
                    .background(Color("backgroundGray"))
                    .zIndex(self.visibleTab ? 1 : 0)
                    
                }
                .animation(.easeInOut)
                .frame(width: geo.size.width, height: self.height)
                
            }
        }
    }
}

struct DetailTab_Previews: PreviewProvider {
    static var previews: some View {
        InfoView(movie: sampleMovies[7])
    }
}
