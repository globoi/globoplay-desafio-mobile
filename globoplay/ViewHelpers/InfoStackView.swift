//
//  DetailTab.swift
//  globoplay
//
//  Created by Marcos Curvello on 19/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI
import TinyNetworking

struct InfoStackView: View {
    @Binding var selection: Selection
    @ObservedObject var resource: Resource<Discover<MovieList>>
    var relatedMovies: [MovieList]? { resource.value?.results.filter { $0.id != self.movie!.id } }
    var movie: Movie?
    
    init(movie: Movie, selection: Binding<Selection>) {
        self.movie = movie
        self._selection = selection
        
        let id =  movie.genres.first?.id != nil ? movie.genres.first!.id : 0
        let request = Request(.discover(.movie), queries: [Query(name: .genre, value: id)])
        self.resource = Resource<Discover<MovieList>>(endpoint: Endpoint(json: .get, url: request.url!, headers: request.auth))
    }
    
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            HStack(spacing: 0) {
                ForEach(Selection.allCases, id: \.self) { selection in
                    Button(action: {}) {
                        Text(selection.rawValue.uppercased())
                            .fontWeight(.bold)
                            .padding(20)
                            .background(self.selection == selection ?
                                Rectangle().fill(Color.black).padding(.bottom, 3.0).background(Color.white) :
                                Rectangle().fill(Color.black).padding(.bottom, 0.0).background(Color.black)
                        )
                    }
                    .animation(.easeOut)
                    .onTapGesture { self.selection = selection }
                }
                Spacer()
            }
            .font(.system(size: 12))
            .foregroundColor(.white)
            .background(Color.black)
            
            ZStack {
                VStack {
                    InfoView(information: movie!.information)
                }
                .frame(minHeight: 350)
                .zIndex(self.selection == .details ? 1 : 0)
                
                VStack {
                    Group {
                        if relatedMovies == nil {
                            Loader(.constant(true))
                        } else {
                            CollectionRowView(movies: self.relatedMovies!).padding(.top, 10)
                        }
                    }
                    Spacer()
                }
                .frame(minHeight: 300)
                .background(Color.backgroundGray)
                .zIndex(self.selection == .related ? 1 : 0)
            }
        }
    }
}

extension InfoStackView {
    enum Selection: String, CaseIterable {
        case related = "Assista Também"
        case details = "Detalhes"
    }
}

struct InfoStackView_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            InfoStackView(movie: sampleMovie, selection: .constant(.details))
                .environmentObject(Store())
                .frame(height: 300)
        }
        .previewLayout(.fixed(width: 380, height: 400))
    }
}
