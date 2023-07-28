//
//  PosterCell.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 27/07/23.
//

import SwiftUI

struct PosterCell: View {
    
    var result: Result
    
    var goToDetailsClosure: (Result) -> Void
    
    var body: some View {
        AsyncImage(url: URL.init(string: "\(posterURL)\(result.posterPath ?? "")")){image in
            image.resizable().aspectRatio(contentMode: .fit)
        } placeholder: {
            ProgressView().scaleEffect(1.5)
                .background(.regularMaterial, in: RoundedRectangle(cornerRadius: 8, style: .continuous))
                .frame(width: 150, height: 150)
        }.frame(maxWidth: 150, maxHeight: 300).onTapGesture {
            goToDetailsClosure(result)
        }
    }
}
