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
                .listRowInsets(EdgeInsets(top: 0, leading: 16, bottom: 0, trailing: 0))
            }
        }
        .padding(.horizontal, -20)
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
