//
//  BackgroundView.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 31/07/23.
//

import SwiftUI

struct BackgroundView: View {
    
    var imageURL: URL?
    
    var body: some View {
        AsyncImage(url: imageURL){image in
            image.image?.resizable()
                .aspectRatio(contentMode: .fill)
                .edgesIgnoringSafeArea(.all)
                .blur(radius: 10.0)
                .overlay(LinearGradient(gradient: Gradient(colors: [Color.clear, Color.black]), startPoint: .top, endPoint: .center))
        }
    }
}
