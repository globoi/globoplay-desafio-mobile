//
//  RecommendationsTabView.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import SwiftUI

struct RecommendationsTabView: View {
    
    var item: Result
    
    var viewModel: RecommendationsTabViewModel
    
    var didTapRecommendation: (Result) -> Void
    
    var columns = [
        GridItem(.flexible()),
        GridItem(.flexible()),
        GridItem(.flexible())
    ]
    
    var body: some View {
        if item.getMediaType() == .movie{
            if let results = viewModel.suggestedMovies?.results{
                LazyVGrid(columns: columns){
                    ForEach(results, id: \.id){result in
                        PosterCell(result: result, goToDetailsClosure: didTapRecommendation)
                    }
                }
            }
        }else{
            if let results = viewModel.suggestedTVShows?.results{
                LazyVGrid(columns: columns, content: {
                    ForEach(results, id: \.id){result in
                        PosterCell(result: result, goToDetailsClosure: didTapRecommendation)
                    }
                })
            }
        }
    }
}
