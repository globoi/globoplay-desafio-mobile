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
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    @Environment(\.imageCache) var cache: ImageCache
    @EnvironmentObject var store: Store
    @ObservedObject var resource: Resource<Movie>
    var movie: Movie? { resource.value }
    init(movie: MovieList) {
        let details: Request = .movie(detail: String(movie.id))
        self.resource = Resource<Movie>(endpoint: Endpoint(json: .get, url: details.url!, headers: details.auth))
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
                        .offset(x: -10, y: 22)
                        
                        AsyncImage(
                            url: .image(path: movie?.posterPath ?? ""),
                            cache: cache,
                            placeholder: ImagePlaceholder(),
                            configuration: { $0.resizable() }
                        ).frame(width: 114, height: 166).padding(.top, -8)
                        
                        VStack {
                            Text(movie!.title)
                                .foregroundColor(.white)
                                .font(.system(size: 20, weight: .bold))
                                .lineLimit(0)
                                .multilineTextAlignment(.center)
                                .padding([.top, .bottom], 5)
                            
                            Text(movie!.overview ?? "")
                                .font(.caption)
                                .lineLimit(nil)
                                .multilineTextAlignment(.leading)
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
                    .background(ImageBackground(path: movie!.posterPath ?? ""))
                    
                    InfoStackView(movie: movie!)
                        .listRowInsets(EdgeInsets(top: 4, leading: 0, bottom: 0, trailing: 0))
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
