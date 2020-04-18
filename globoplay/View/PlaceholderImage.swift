//
//  PlaceholderImage.swift
//  globoplay
//
//  Created by Marcos Curvello on 18/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI

struct PlaceholderImage: View {
    var body: some View {
        Image(systemName: "photo")
            .foregroundColor(Color("systemGray3"))
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .background(Color("systemGray5"))
    }
}

struct PlaceholderImage_Previews: PreviewProvider {
    static var previews: some View {
        PlaceholderImage()
    }
}
