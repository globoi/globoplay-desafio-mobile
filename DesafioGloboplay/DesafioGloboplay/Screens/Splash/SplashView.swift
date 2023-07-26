//
//  SplashView.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import SwiftUI

struct SplashView: View {
    var body: some View {
            ZStack{
                Image(assets.images.logo.rawValue).resizable().aspectRatio(contentMode: .fit)
            }.padding(80)
    }
}

#Preview {
    SplashView()
}
