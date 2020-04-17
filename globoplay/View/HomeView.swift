//
//  HomeView.swift
//  globoplay
//
//  Created by Marcos Curvello on 17/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI

struct HomeView: View {
    @EnvironmentObject private var store: Store
    
    var body: some View {
        List {
            MovieRow(title: "Novelas", items: store.movies)
            MovieRow(title: "Séries", items: store.movies)
            MovieRow(title: "Cinema", items: store.movies)
        }
        
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
            .environmentObject(Store())
    }
}
