//
//  ImageHeader.swift
//  globoplay
//
//  Created by Marcos Curvello on 19/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import UIKit
import SwiftUI

struct ImageHeader: View {
    @Environment(\.imageCache) var cache: ImageCache
    var path: String
    
    var body: some View {
        ZStack {
            AsyncImage(
                url: .image(path: path),
                cache: self.cache,
                placeholder: PlaceholderImage(),
                configuration: { $0.resizable() }
            )
                .clipped()
                .blur(radius: 20)
            LinearGradient(gradient: Gradient(colors: [.clear, Color("fadingBlack"), .black]), startPoint: .top, endPoint: .bottom)
            
            AsyncImage(
                url: .image(path: path),
                cache: cache,
                placeholder: PlaceholderImage(),
                configuration: { $0.resizable() }
            )
                .frame(width: 120, height: 180)
                .offset(x: 0, y: 20)
        }
        .clipped()
        .padding(-30)
    }
}

struct ImageHeader_Previews: PreviewProvider {
    static var previews: some View {
        ImageHeader(path: "/jZowUf4okNYuSlgj5iURE7CDMho.jpg")
            .previewLayout(.fixed(width: 350, height: 400))
    }
}
