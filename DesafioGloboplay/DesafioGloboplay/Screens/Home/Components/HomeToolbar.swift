//
//  HomeToolbar.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import SwiftUI

struct HomeToolbar: ToolbarContent {
    var body: some ToolbarContent {
        ToolbarItem(placement: .principal) {
            VStack{
                Image(assets.images.logo.rawValue, bundle: .main).resizable().aspectRatio(contentMode: .fit)
            }.padding(10)
        }
    }
}
