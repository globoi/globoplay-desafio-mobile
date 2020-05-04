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
    @ObservedObject var resource: Resource<Movie>
    @Environment(\.imageCache) var cache: ImageCache
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    var movie: Movie? { resource.value }
    init(movie: MovieList) {
        let request = Request(.detail(.movie, movie.id))
        self.resource = Resource<Movie>(endpoint: Endpoint(json: .get, url: request.url!, headers: request.auth))
    }
    
    var body: some View {
        Group {
            if movie == nil {
                Loader()
            } else {
                List {
                    VStack {
                        HStack {
                            Button(action: {}) {
                                Image(systemName: "arrow.left")
                                    .foregroundColor(.white)
                                    .padding()
                            }.onTapGesture { self.presentationMode.wrappedValue.dismiss() }
                            Spacer()
                        }
                        .offset(x: -10, y: 28)
                        
                        AsyncImage(
                            url: Request(.image("w200", movie?.posterPath ?? "")),
                            cache: cache,
                            placeholder: ImagePlaceholder(),
                            configuration: { $0.resizable() }
                        ).frame(width: 108, height: 160).padding(.top, -10)
                        
                        VStack {
                            Text(movie!.title)
                                .foregroundColor(.white)
                                .font(.system(size: 20, weight: .bold))
                                .lineLimit(0)
                                .multilineTextAlignment(.center)
                                .padding([.top, .bottom], 5)
                            
                            Text(movie!.genres.first?.name ?? "")
                                .foregroundColor(.gray4)
                                .font(.caption)
                                .padding(.bottom, 5)
                            
                            Text(movie!.overview ?? "")
                                .foregroundColor(.gray4)
                                .font(.system(size: 13))
                                .lineLimit(6)
                                .multilineTextAlignment(.leading)
                                .fixedSize(horizontal: false, vertical: true)
                                .padding(.bottom, 5)
                            
                            HStack {
                                Button(action: {}) {
                                    HStack {
                                        Image(systemName: "play.fill")
                                        Text("Assista")
                                    }
                                    .frame(maxWidth: .infinity)
                                    .padding()
                                    .foregroundColor(.black)
                                    .background(Color.white)
                                    .cornerRadius(5)
                                }
                                
                                Button(action: {}) {
                                    HStack {
                                        if store.isFavorite(movie: movie!.id) {
                                            Image(systemName: "checkmark")
                                            Text("Adicionado")
                                        } else {
                                            Image(systemName: "star.fill")
                                            Text("Minha Lista")
                                            
                                        }
                                    }
                                    .onTapGesture { self.store.toggleFavorite(movie: self.movie!) }
                                    .frame(maxWidth: .infinity)
                                    .padding()
                                    .foregroundColor(.white)
                                    .overlay(RoundedRectangle(cornerRadius: 5).stroke(Color.white, lineWidth: 1))
                                }
                            }
                        }
                    }
                    .frame(height: 420)
                    .background(ImageBackground(path: movie!.posterPath ?? ""))
                    
                    InfoStackView(movie: movie!)
                        .listRowInsets(EdgeInsets(top: 0, leading: 0, bottom: 30, trailing: 0))
                        .background(
                            VStack {
                                Color.backgroundGray
                                    .frame(minHeight: 300)
                                    .padding(.bottom, -200)
                            }
                    )
                }
                .background(Color.black)
                .edgesIgnoringSafeArea(.all)
            }
        }
    }
}

struct DetailView_Previews: PreviewProvider {
    static var previews: some View {
        DetailView(movie: sampleMovies.first!)
            .environmentObject(Store())
    }
}
