//
//  HomeViewModel.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import Foundation
import SwiftUI

class HomeViewModel: ViewModel, ObservableObject{
    
    @Published var movieList: MoviesResults?
    @Published var tvShowsList: TVShowsResults?
    
    
    func getMovieList(){
        do{
            perform(MoviesResults.self, request: try APIURLs.getMovies.request()) { movies in
                self.movieList = movies
            }
        }
        catch {
            errorProcedure()
        }
    }
    
    
    func getTVShowsList(){
        do{
            perform(TVShowsResults.self, request: try APIURLs.getTVShows.request()) { shows in
                self.tvShowsList = shows
            }
        }
        catch {
            errorProcedure()
        }
    }
    
}
