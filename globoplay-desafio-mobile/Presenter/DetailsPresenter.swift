//
//  DetailsPresenter.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 09/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import Foundation

protocol DetailsViewProtocol: NSObjectProtocol {
    func setYoutubeId(_ id: String?)
    func setDetailsList(_ movieList: MovieDetails?)
}


class DetailsPresenter {
    
    fileprivate let dataService: MovieService
    weak fileprivate var detailsView: DetailsViewProtocol?
    
    init(dataService: MovieService) {
        self.dataService = dataService
    }
    
    func attachView(_ viewProtocol: DetailsViewProtocol) {
        self.detailsView = viewProtocol
    }
    
    func detachView() {
        self.detailsView = nil
    }
    
    func getYoutubeId(id: String){
        MovieService.getTrailerKey(id: id, completion: {result, error  in
            if result != nil{
                self.detailsView?.setYoutubeId(id)
            } else{
            print("[DEBUG] no results")
            }
        })
    }
    
    func getDetailsList(id: Int){
        
        MovieService.getMovieDetails(id: String(id), completion: {results, error  in
            if results != nil{
                self.detailsView?.setDetailsList(results)
            } else{
                print("[DEBUG] no results")
            }
        })
    }
}

