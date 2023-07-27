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
    
    var body: some View {
        
        VStack(alignment: .leading, spacing: 8){
            Text("Ficha Técnica").font(.system(size: 22, weight: .bold))
            Spacer()
            Text("**Título original:** \(item.getTitle() ?? String())").foregroundStyle(.secondary)
            Text("**Gênero:** \(self.getGenres() ?? String())").foregroundStyle(.secondary)
            if item.getMediaType() == .tvShow{
                Text("**Episódios:** \(viewModel.tvShowDetails?.numberOfEpisodes ?? 0)").foregroundStyle(.secondary)
            }
            Text("**Ano de produção:** \(getYearOfProduction() ?? String())").foregroundStyle(.secondary)
            Text("**País:** \(getCountry() ?? String())").foregroundStyle(.secondary)
            Text("**Elenco:** \(viewModel.getCasting() ?? String())").foregroundStyle(.secondary)
            if item.getMediaType() == .tvShow{
                Text("**Disponível até:** \(viewModel.getTVShowAvailability())").foregroundStyle(.secondary)
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

