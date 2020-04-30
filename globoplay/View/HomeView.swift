//
//  HomeView.swift
//  globoplay
//
//  Created by Marcos Curvello on 17/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI
import Combine

struct HomeView: View {
    @EnvironmentObject var store: Store
    var homeCollection: [HomeCollection]?
    init(collection: [HomeCollection]) {
        self.homeCollection = collection
    }
    
    var body: some View {
        List {
            ForEach(homeCollection ?? [], id: \.self.genre.id) { collection in
                CollectionRowView(
                    title: collection.genre.name,
                    movies: collection.resource.value?.results ?? []
                )
                    .foregroundColor(.white)
                    .background(Color.backgroundGray)
                    .listRowInsets(EdgeInsets())
            }
        }
        .background(Color.backgroundGray)
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationView {
            HomeView(collection: [])
                .environmentObject(Store())
        }
    }
}
