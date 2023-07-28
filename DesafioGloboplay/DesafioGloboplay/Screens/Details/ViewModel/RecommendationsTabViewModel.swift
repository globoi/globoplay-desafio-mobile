//
//  RecommendationsTabViewModel.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 27/07/23.
//

import Foundation
import SwiftUI

class RecommendationsTabViewModel: ViewModel, ObservableObject{
    
    @Published var suggestedMovies: MoviesResults?
    @Published var suggestedTVShows: TVShowsResults?
    
    func getSuggestedMovies(_ id: Int){
        do{
            perform(MoviesResults.self, request: try APIURLs.similarMovies(id).request()) { suggestions in
                self.suggestedMovies = suggestions
            }
        }
        catch {
            errorProcedure()
        }
    }
    
    func getSuggestedTVShows(_ id: Int){
        do{
            perform(TVShowsResults.self, request: try APIURLs.similarTVShows(id).request()) { suggestions in
                self.suggestedTVShows = suggestions
            }
        }
        catch {
            errorProcedure()
        }
    }
    
}
