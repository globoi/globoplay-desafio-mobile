//
//  CategoryRow.swift
//  globoplay
//
//  Created by Marcos Curvello on 15/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI

struct ResultRowView: View {
    var title: String
    var movies: [MovieResult]
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(title)
                .font(.headline)
                .padding([.top, .leading, .trailing], 15)
            
            ScrollView(.horizontal, showsIndicators: false) {
                HStack(alignment: .top, spacing: 0) {
                    ForEach(self.movies) { movie in
                        NavigationLink(destination:
                            LazyView(DetailView(movieId: movie.id))
                        ) {
                            MovieResultItem(movie: movie)
                        }
                    }
                }
            }
            .frame(height: 200)
        }
        .padding(.bottom, -13)
    }
}

struct MovieResultItem: View {
    @Environment(\.imageCache) var cache: ImageCache
    var movie: MovieResult
    
    var body: some View {
        VStack(alignment: .leading) {
            AsyncImage(
                url: api.image(for: movie.posterPath ?? "N/A", size: "w200"),
                cache: self.cache,
                placeholder: PlaceholderImage(),
                configuration: { $0.renderingMode(.original).resizable() }
            )
                .frame(width: 120, height: 180)
        }
        .padding([.leading, .bottom], 15)
    }
}

struct CategoryRow_Previews: PreviewProvider {
    static var previews: some View {
        ResultRowView(title: "Cinema", movies: sampleMovies)
    }
}
