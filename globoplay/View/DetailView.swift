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
//    var movie: Movie? { sampleMovie }
    var isFavorite = false

    init(movieId id: String) {
        let details: Request = .movie(detail: id)
        self.resource = Resource<Movie>(endpoint: Endpoint(json: .get, url: details.url!, headers: details.auth))
    }
    
    var body: some View {
        Group {
            if movie == nil {
                Loader()
            } else {
                List {
                    VStack {
                        Spacer()
                        AsyncImage(
                            url: .image(path: movie?.posterPath ?? ""),
                            cache: cache,
                            placeholder: PlaceholderImage(),
                            configuration: { $0.resizable() }
                        )
                            .frame(width: 108, height: 160)
                            .padding(.top, 40)
                        
                        VStack {
                            Text(movie!.title)
                                .font(.system(size: 20))
                                .fontWeight(.bold)
                                .lineLimit(5)
                                .padding([.top, .bottom], 5)
                            
                            Text(movie!.overview ?? "")
                                .font(.system(size: 14))
                                .font(.subheadline)
                                .lineLimit(5)
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
                                        if self.isFavorite {
                                            Image(systemName: "checkmark")
                                            Text("Adicionado")
                                        } else {
                                            Image(systemName: "star.fill")
                                            Text("Minha Lista")
                                        }
                                    }
                                    .frame(maxWidth: .infinity)
                                    .padding()
                                    .foregroundColor(.white)
                                    .overlay(
                                        RoundedRectangle(cornerRadius: 5).stroke(Color.white, lineWidth: 1)
                                    )
                                }
                            }
                        }
                    }
                    .foregroundColor(Color.white)
                    .frame(maxHeight: 410)
                    .background(
                        ImageBackground(path: movie?.posterPath ?? "")
                            .frame(height: 410)
                    )

                    InfoStackView(movie: movie!)
                        .frame(minHeight: 350)
                        .listRowInsets(EdgeInsets(top: 30, leading: 0, bottom: 0, trailing: 0))
                    
                    Spacer()

                }
                .edgesIgnoringSafeArea(.all)
                .onAppear {
                    UITableView.appearance().tableFooterView = UIView()
                    UITableView.appearance().backgroundColor = .black
                    UITableViewCell.appearance().backgroundColor = .clear
                    UITableView.appearance().separatorStyle = .none
                }
            }
        }
    }
}

struct DetailView_Previews: PreviewProvider {
    static var previews: some View {
        DetailView(movieId: sampleMovies.first!.id.stringValue)
            .environmentObject(Store())
    }
}
