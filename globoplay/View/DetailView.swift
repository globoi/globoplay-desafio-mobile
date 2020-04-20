//
//  MovieDetail.swift
//  globoplay
//
//  Created by Marcos Curvello on 18/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI

struct DetailView: View {
    @EnvironmentObject var store: Store
//    @ObservedObject var movie: Resource<Movie>
    
    var movie: Movie

    init(movie: Movie) {
//        self.movie = Resource<Movie>(endpoint: api.movie(details: id))
        self.movie = movie
        UITableView.appearance().backgroundColor = .black
        UITableViewCell.appearance().backgroundColor = .black
    }
    
    var body: some View {
        Group {
            if movie != nil {
                List {
                    VStack(spacing: 5) {
                        ImageHeader(path: movie.posterPath ?? "")
                            .frame(height: 242)
                        
                        Text(movie.title ?? "")
                            .font(Font.system(size: 20))
                            .fontWeight(.bold)
                        
                        Text(movie.overview ?? "")
                            .font(Font.system(size: 14))
                            .font(.subheadline)
                            .multilineTextAlignment(.leading)
                            .lineLimit(4)
                        
                        Spacer()
                            .frame(height: 5)
                        
                        HStack {
                            Button(action: {}) {
                                HStack {
                                    Image(systemName: "play.fill")
                                    Text("Assista")
                                }
                                .frame(minWidth: 0, maxWidth: .infinity)
                                .padding()
                                    
                                .background(Color.white)
                                .foregroundColor(.black)
                                .cornerRadius(5)
                            }
                            
                            Button(action: {
                                self.store.favorite(self.movie)
                            }) {
                                HStack {
                                    Image(systemName: "star.fill")
                                    Text("Minha Lista")
                                }
                                .frame(minWidth: 0, maxWidth: .infinity)
                                .padding()
                                .foregroundColor(.white)
                                .overlay(
                                    RoundedRectangle(cornerRadius: 5)
                                        .stroke(Color.white, lineWidth: 1)
                                )
                            }
                        }
                    }
                    .foregroundColor(.white)
                    .edgesIgnoringSafeArea(.all)
                }
            } else {
                Text("Loading...")
            }
        }
    }
}


struct MovieDetail_Previews: PreviewProvider {
    static var previews: some View {
        DetailView(movie: sampleMovie)
            .environmentObject(Store())
    }
}
