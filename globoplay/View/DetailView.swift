//
//  MovieDetail.swift
//  globoplay
//
//  Created by Marcos Curvello on 18/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI
import TinyNetworking

struct DetailView: View {
    @EnvironmentObject var store: Store
    @ObservedObject var movie: Resource<Movie>
    
    init(movieId: Int) {
        let endpoint: EndpointUrl = .movie(detail: String(movieId))
        self.movie = Resource<Movie>(endpoint: Endpoint(json: .get, url: endpoint.url!, headers: endpoint.auth))
    }
    
    var body: some View {
        Group {
            if movie.value != nil {
                List {
                    VStack(spacing: 5) {
                        ImageHeader(path: movie.value!.posterPath ?? "")
                            .frame(height: 242)
                        
                        Spacer()
                            .frame(height: 20)
                        
                        Text(movie.value!.title)
                            .font(Font.system(size: 20))
                            .fontWeight(.bold)
                        
                        Text(movie.value!.overview ?? "")
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
                                    
                                .background(Color.black)
                                .foregroundColor(.white)
                                .cornerRadius(5)
                            }
                            
                            Button(action: {
                                self.store.favorite(self.movie.value!)
                            }) {
                                HStack {
                                    Image(systemName: "star.fill")
                                    Text("Minha Lista")
                                }
                                .frame(minWidth: 0, maxWidth: .infinity)
                                .padding()
                                .foregroundColor(.black)
                                .overlay(
                                    RoundedRectangle(cornerRadius: 5)
                                        .stroke(Color.black, lineWidth: 1)
                                )
                            }
                        }
                    }
                }
                .foregroundColor(.black)
                .navigationBarHidden(true)
                .edgesIgnoringSafeArea([.top, .bottom])
            } else {
                
            }
        }
    }
}

struct MovieDetail_Previews: PreviewProvider {
    static var previews: some View {
        DetailView(movieId: 76341)
            .environmentObject(Store())
    }
}
