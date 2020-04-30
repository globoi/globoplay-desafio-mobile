//
//  MovieInfoView.swift
//  globoplay
//
//  Created by Marcos Curvello on 27/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI

struct InfoView: View {
    var information: [Info]
    
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            HStack {
                Text("Ficha técnica")
                    .font(.system(size: 13))
                    .fontWeight(.bold)
                    .padding()
                Spacer()
            }
            .padding([.top, .bottom], 8)
            .foregroundColor(.white)
            .background(Color.backgroundGray)
            
            List(information) { info in
                VStack(alignment: .leading, spacing: 3) {
                    Text(info.name)
                        .fontWeight(.bold)
                    Text(info.value)
                        .font(.caption)
                }
                .font(.system(size: 12))
            }
            .listRowInsets(EdgeInsets(top: 0, leading: 0, bottom: 400, trailing: 0))
            .background(Color.backgroundGray)
            .foregroundColor(Color.gray3)
        }
    }
}

struct MovieInfoView_Previews: PreviewProvider {
    static var previews: some View {
        InfoView(information: sampleMovie.information)
            .previewLayout(.fixed(width: 350, height: 300))
    }
}
