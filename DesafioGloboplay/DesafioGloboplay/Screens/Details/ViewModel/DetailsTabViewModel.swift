//
//  DetailsTabViewModel.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 27/07/23.
//

import Foundation
import SwiftUI

class DetailsTabViewModel: ViewModel, ObservableObject{
    
    @Published var movieDetails: MovieDetails?
    @Published var tvShowDetails: TVShowDetails?
    
    @Published var credits: Credits?
    
    @Published var dataIsLoaded: Bool = false
    
    //MARK: - Details
    
    func getMovieDetails(_ id: Int){
        do{
            perform(MovieDetails.self, request: try APIURLs.detailsMovie(id).request()) { details in
                self.movieDetails = details
                self.dataIsLoaded = true
            }
        }
        catch {
            errorProcedure()
        }
    }
    
    func getTVShowDetails(_ id: Int){
        do{
            perform(TVShowDetails.self, request: try APIURLs.detailsTVShow(id).request()) { details in
                self.tvShowDetails = details
                self.dataIsLoaded = true
            }
        }
        catch {
            errorProcedure()
        }
    }
    
    //MARK: - Credits
    
    func getMovieCredits(_ id: Int){
        do{
            perform(Credits.self, request: try APIURLs.creditsMovie(id).request()) { credits in
                self.credits = credits
                self.dataIsLoaded = true
            }
        }
        catch {
            errorProcedure()
        }
    }
    
    func getTVShowCredits(_ id: Int){
        do{
            perform(Credits.self, request: try APIURLs.creditsTvShow(id).request()) { credits in
                self.credits = credits
                self.dataIsLoaded = true
            }
        }
        catch {
            errorProcedure()
        }
    }
    
    
    //MARK: - Helpers
    
    
    func getCasting() -> String?{
        if let cast = credits?.cast?.filter({$0.knownForDepartment == "Acting"}).compactMap({$0.name}){
            return cast.joined(separator: ", ")
        }else{
            return ""
        }
    }
    
    func getMovieGenres() -> String? {
        if let genres = movieDetails?.genres?.compactMap({$0.name}){
            return genres.joined(separator: ", ")
        }else{
            return ""
        }
    }
    
    func getTVShowGenres() -> String? {
        if let genres = tvShowDetails?.genres?.compactMap({$0.name}){
            return genres.joined(separator: ", ")
        }else{
            return ""
        }
    }
    
    
}
