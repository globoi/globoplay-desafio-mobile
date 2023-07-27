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
    
    //MARK: - Details
    
    func getMovieDetails(_ id: Int){
        do{
            perform(MovieDetails.self, request: try APIURLs.detailsMovie(id).request()) { details in
                self.movieDetails = details
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
    
    
    func getMovieCountries() -> String? {
        if let countries = movieDetails?.productionCountries?.compactMap({$0.name}){
            return countries.joined(separator: ", ")
        }else{
            return ""
        }
    }
    
    func getTVShowsCountries() -> String? {
        if let countries = tvShowDetails?.productionCountries?.compactMap({$0.name}){
            return countries.joined(separator: ", ")
        }else{
            return ""
        }
    }
    
    func getYear(fromDate dateString: String?) -> String{
        if let dateUnwrapped = dateString{
            let dateFormatter = DateFormatter()
            dateFormatter.dateFormat = "yyyy-MM-dd"
            let date = dateFormatter.date(from: dateUnwrapped) ?? Date()
            dateFormatter.dateFormat = "yyyy"
            let yearString = dateFormatter.string(from: date)
            return yearString
        }else{
            return "informação indisponível"
        }
    }
    
    func getTVShowAvailability() -> String{
        
        if let lastAirDate = tvShowDetails?.lastAirDate{
            let dateFormatter = DateFormatter()
            dateFormatter.dateFormat = "yyyy-MM-dd"
            let date = dateFormatter.date(from: lastAirDate) ?? Date()
            dateFormatter.dateFormat = "dd/MM/yyyy"
            let dateString = dateFormatter.string(from: date)
            return dateString
        }else{
            return "informação indisponível"
        }
    }
    
}
