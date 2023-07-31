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
                Home().tabItem {Label(screenTexts.homeTabs.home.rawValue, systemImage: assets.icons.tabIcons.home.rawValue)}
                MyList().tabItem{Label(screenTexts.homeTabs.myList.rawValue, systemImage: assets.icons.tabIcons.myList.rawValue)}
            }
        }
    }
}
