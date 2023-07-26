//
//  HorizontalList.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import SwiftUI

struct HorizontalList: View {
    
    var results: [Result]?
    
    var body: some View {
        ScrollView(.horizontal){
            HStack{
                if let resultsUnwrapped = results{
                    ForEach(resultsUnwrapped, id: \.id){result in
                        AsyncImage(url: URL.init(string: "\(posterURL)\(result.posterPath ?? "")")){image in
                            image.resizable().aspectRatio(contentMode: .fit)
                        } placeholder: {
                            ProgressView()
                        }.frame(width: 150, height: 300)
                    }
                }else{
                    Text("Erro ao carregar lista")
                }
            }
        }
    }
}


