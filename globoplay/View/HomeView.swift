//
//  HomeView.swift
//  globoplay
//
//  Created by Marcos Curvello on 17/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI

struct HomeView: View {
    @EnvironmentObject var store: Store
    
    var body: some View {
        List {
            ResultRowView(title: "Ação", movies: store.movies)
            ResultRowView(title: "Drama", movies: store.movies)
            ResultRowView(title: "Suspense", movies: store.movies)
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationView {
            HomeView()
                .environmentObject(Store())
        }
    }
}
