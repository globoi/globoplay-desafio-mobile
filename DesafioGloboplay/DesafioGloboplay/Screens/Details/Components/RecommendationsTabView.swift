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
    
    var body: some View {
        
        if let results = getResults(){
            LazyVGrid(columns: defaultGridColumns){
                ForEach(results, id: \.id){result in
                    PosterCell(result: result, goToDetailsClosure: didTapRecommendation)
                }
            }
        }else{
            if viewModel.isLoading{
                Text("Carregando...")
            }
            if viewModel.showError{
                ErrorView(viewModel: viewModel)
            }
        }
        
    }
    
    
    func getResults() -> [Result]?{
        return item.getMediaType() == .movie ? viewModel.suggestedMovies?.results : viewModel.suggestedTVShows?.results
        
    }
}
