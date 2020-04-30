//
//  FavoritesView.swift
//  globoplay
//
//  Created by Marcos Curvello on 20/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI

struct FavoriteMoviesView: View {
    @EnvironmentObject var store: Store
    var favorites: [MovieList] { store.favorites }
    
    var body: some View {
        ScrollView(.vertical) {
            GridStackView(movies: favorites, columns: 3)
        }
        .background(Color.backgroundGray)
    }
}

struct FavoritesView_Previews: PreviewProvider {
    static var previews: some View {
        FavoriteMoviesView()
            .environmentObject(Store())
    }
}
