//
//  ImageHeader.swift
//  globoplay
//
//  Created by Marcos Curvello on 19/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI

struct ImageBackground: View {
    @Environment(\.imageCache) var cache: ImageCache
    var path: String
    
    var body: some View {
        AsyncImage(
            url: Request(.image("original", path)),
            cache: self.cache,
            placeholder: ImagePlaceholder(),
            configuration: { $0.resizable() }
        )
            .blur(radius: 20)
            .clipped()
            .overlay(LinearGradient(gradient: Gradient(colors: [.clear, .alphaBlack, .black]), startPoint: .top, endPoint: .bottom))
            .clipped()
            .background(Color.black)
            .padding(EdgeInsets(top: -20, leading: -20, bottom: -10, trailing: -20))
    }
}

struct ImageHeader_Previews: PreviewProvider {
    static var previews: some View {
        ImageBackground(path: "/jZowUf4okNYuSlgj5iURE7CDMho.jpg")
            .previewLayout(.fixed(width: 350, height: 400))
    }
}
