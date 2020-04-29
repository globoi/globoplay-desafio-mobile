//
//  GridStackView.swift
//  globoplay
//
//  Created by Marcos Curvello on 21/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI

struct GridStackView: View {
    let rows: [GridStackRow]
    
    var body: some View {
        VStack(alignment: .leading) {
            ForEach(rows) { row in
                HStack {
                    ForEach(row.movies) { movie in
                        NavigationLink(destination:
                            LazyView(DetailView(movie: movie))
                        ) {
                            GridStackItem(movie: movie)
                            
                        }
                    }
                }
            }
        }
    }
    
    init(movies: [MovieList], columns: Int) {
        self.rows = movies.chunked(columns).map { GridStackRow(movies: $0) }
    }
}

struct GridStackRow: Identifiable {
    let id = UUID()
    let movies: [MovieList]
}

struct GridStackItem: View {
    @Environment(\.imageCache) var cache: ImageCache
    var movie: MovieList
    
    var body: some View {
        VStack(alignment: .leading) {
            AsyncImage(
                url: .image(size: "w200", path: movie.posterPath ?? ""),
                cache: self.cache,
                placeholder: ImagePlaceholder(),
                configuration: { $0.renderingMode(.original).resizable() }
            )
                .frame(width: 108, height: 160)
        }
    }
}

struct GridStack_Previews: PreviewProvider {
    static var previews: some View {
        GridStackView(movies: sampleMovies, columns: 3)
    }
}
