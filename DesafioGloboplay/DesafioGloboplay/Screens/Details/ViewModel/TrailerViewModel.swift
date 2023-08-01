//
//  TrailerViewModel.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 31/07/23.
//

import SwiftUI
class TrailerViewModel: ViewModel, ObservableObject{
    
    @Published var trailer: TrailerResult?
    
    func loadTrailer(id: Int, type: MediaType = .movie){
        if type == .movie{
            getTrailerResult(movieId: id)
        }else{
            getTrailerResult(tvShowId: id)
        }
    }
    
    fileprivate func getTrailerResult(movieId: Int){
        do{
            perform(TrailerResults.self, request: try APIURLs.getMovieVideos(movieId).request()) { videos in
                self.trailer = videos.results?.first(where: {$0.type == .trailer})
            }
        }
        catch {
            errorProcedure()
        }
    }
    
    fileprivate func getTrailerResult(tvShowId: Int){
        do{
            perform(TrailerResults.self, request: try APIURLs.getTVShowVideos(tvShowId).request()) { videos in
                self.trailer = videos.results?.first(where: {$0.type == .trailer})
            }
        }
        catch {
            errorProcedure()
        }
    }
    
    
}
