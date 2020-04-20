//
//  FavoritesView.swift
//  globoplay
//
//  Created by Marcos Curvello on 20/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI

struct FavoritesView: View {
    @EnvironmentObject var store: Store
    
    var body: some View {
        VStack(alignment: .leading) {
            ScrollView(.vertical, showsIndicators: true) {
                HStack(alignment: .top, spacing: 0) {
                    ForEach(self.store.favorites ?? []) { movie in
                        NavigationLink(destination:
                            LazyView(DetailView(movie: sampleMovie))
                        ) {
                            MovieItem(movie: movie)
                        }
                    }
                }
            }
        }
    }
}

struct MovieItem: View {
    @Environment(\.imageCache) var cache: ImageCache
    var movie: Movie
    
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

struct FavoritesView_Previews: PreviewProvider {
    static var previews: some View {
        FavoritesView()
            .environmentObject(Persistance())
    }
}
