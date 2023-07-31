//
//  HorizontalList.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import SwiftUI

struct HorizontalList: View {
    
    var results: [Result]?
    var goToDetailsClosure: (Result) -> Void
    
    var body: some View {
        ScrollView(.horizontal){
            HStack{
                if let resultsUnwrapped = results{
                    ForEach(resultsUnwrapped, id: \.id){result in
                        PosterCell(result: result, goToDetailsClosure: goToDetailsClosure)
                    }
                }else{
                    Text(screenTexts.errors.listLoadingError.rawValue)
                }
            }
        }
    }
}


