//
//  CategoryRow.swift
//  globoplay
//
//  Created by Marcos Curvello on 15/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI

struct MovieRow: View {
    var title: String
    var items: [MovieResult]
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(title)
                .font(.headline)
                .padding(.leading, 15)
            
            ScrollView(.horizontal, showsIndicators: false) {
                HStack(alignment: .top, spacing: 0) {
                    ForEach(self.items) { item in
                        CategoryItem(item: item)
                    }
                }
            }
            .frame(height: 200)
        }
    }
}

struct CategoryItem: View {
    var item: MovieResult
    let image = Image("sample-image")
    
    var body: some View {
        VStack(alignment: .leading) {
            image
                .renderingMode(.original)
                .resizable()
                .frame(width: 100, height: 150)
        }
        .padding(.leading, 10)
    }
}

struct CategoryRow_Previews: PreviewProvider {
    static var previews: some View {
        MovieRow(title: "Novelas", items: sampleMovies)
    }
}
