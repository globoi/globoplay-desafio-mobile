//
//  ContentView.swift
//  globoplay
//
//  Created by Marcos Curvello on 17/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI

struct ContentView: View {
    @EnvironmentObject var store: Store
    @ObservedObject var home = Home()
    
    var body: some View {
        Group {
            if !home.loaded {
                Loader()
            } else {
                TabView {
                    NavigationView {
                        HomeView(collection: home.colletions)
                            .environmentObject(store)
                            .navigationBarTitle(Text("globoplay"))
                    }
                    .tabItem {
                        Image(systemName: "house.fill")
                        Text("Início")
                    }
                    
                    NavigationView {
                        FavoritesView()
                            .environmentObject(store)
                            .navigationBarTitle(Text("minha lista"))
                    }
                    .tabItem {
                        Image(systemName: "star.fill")
                        Text("Minhas lista")
                    }
                }
                .accentColor(.white)
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            .environmentObject(Store())
    }
}
