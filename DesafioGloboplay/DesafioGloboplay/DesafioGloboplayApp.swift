//
//  DesafioGloboplayApp.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import SwiftUI

@main
struct DesafioGloboplayApp: App {
    var body: some Scene {
        WindowGroup {
            TabView{
                Home().tabItem {Label("Início", systemImage: "house")}
                MyList().tabItem{Label("Minha Lista", systemImage: "star")}
            }
        }
    }
}
