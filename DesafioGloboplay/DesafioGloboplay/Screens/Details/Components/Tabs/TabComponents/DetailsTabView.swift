//
//  DetailsTabView.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import SwiftUI

struct DetailsTabView: View {
    
    @ObservedObject var viewModel: DetailsTabViewModel
    
    var item: Result
    
    var detailsText = screenTexts.detailsTab.self
    
    var body: some View {
        
        VStack(alignment: .leading, spacing: 8){
            Text(detailsText.title.rawValue).font(.system(size: 22, weight: .bold))
            Spacer()
            
            DetailsTabLine(title: detailsText.originalTitle.rawValue, value: item.getTitle())
            DetailsTabLine(title: detailsText.gender.rawValue, value: self.getGenres())
            if item.getMediaType() == .tvShow{
                DetailsTabLine(title: detailsText.episodes.rawValue, value: "\(viewModel.tvShowDetails?.numberOfEpisodes ?? 0)")
            }
            
            DetailsTabLine(title: detailsText.yearOfProduction.rawValue, value: getYearOfProduction())
            DetailsTabLine(title: detailsText.country.rawValue, value: getCountry())
            DetailsTabLine(title: detailsText.cast.rawValue, value: viewModel.getCasting())
            if item.getMediaType() == .tvShow{
                DetailsTabLine(title: detailsText.availability.rawValue, value: viewModel.getTVShowAvailability())
            }
        }
        
    }
    
    func getGenres() -> String?{
        if item.getMediaType() == .movie{
            return viewModel.getMovieGenres()
        }else{
            return viewModel.getTVShowGenres()
        }
    }
    
    func getCountry() -> String?{
        if item.getMediaType() == .movie{
            return viewModel.getMovieCountries()
        }else{
            return viewModel.getTVShowsCountries()
        }
    }
    
    func getYearOfProduction() -> String?{
        if item.getMediaType() == .movie{
            return viewModel.getYear(fromDate: viewModel.movieDetails?.releaseDate)
        }else{
            return viewModel.getYear(fromDate: viewModel.tvShowDetails?.firstAirDate)
        }
    }
    
}

