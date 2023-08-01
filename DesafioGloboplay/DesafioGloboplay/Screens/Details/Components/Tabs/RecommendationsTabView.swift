//
//  RecommendationsTabView.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import SwiftUI

struct RecommendationsTabView: View {
    
    var item: Result
    
    @ObservedObject var viewModel: RecommendationsTabViewModel
    
    @Binding var goToRecommendation: Bool
    
    var body: some View {
        
        if viewModel.isLoading{
            Text(screenTexts.general.loading.rawValue)
        }else{
            
            if let results = getResults(){
                LazyVGrid(columns: defaultGridColumns){
                    ForEach(results, id: \.id){result in
                        PosterCell(result: result, goToDetailsClosure: {result in
                            self.viewModel.choosedRecommendation = result
                            self.goToRecommendation = true
                        })
                    }
                }
            }
        }
        
        if viewModel.showError{
            ErrorView(viewModel: viewModel)
        }
        
        
    }
    
    
    func getResults() -> [Result]?{
        return item.getMediaType() == .movie ? viewModel.suggestedMovies?.results : viewModel.suggestedTVShows?.results
        
    }
}
