//
//  MoviePresenter.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 09/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import Foundation

protocol MovieViewProtocol: NSObjectProtocol {
    func setUpcomingListView(_ movieList: [Movie]?)
    func setPopularListView(_ movieList: [Movie]?)
    func setNowPlayingMovies(_ movieList: [Movie]?)
}


class MoviePresenter {
    
    fileprivate let dataService: MovieService
    weak fileprivate var movieView: MovieViewProtocol?
    
    init(dataService: MovieService) {
        self.dataService = dataService
    }
    
    func attachView(_ viewProtocol: MovieViewProtocol) {
        self.movieView = viewProtocol
    }
    
    func detachView() {
        self.movieView = nil
    }
    
    func getUpcomingListView(){
        MovieService.getUpcomingMovies { [weak self] results, error  in
            if results != nil{
                self?.movieView!.setUpcomingListView(results)
            } else{
                print("no results")
            }
        }
    }
    
    func getPopularMovies(){
        MovieService.getPopularMovies { results, error  in
            if results != nil{
                self.movieView?.setPopularListView(results)
            } else{
                print("no results")
            }
        }
    }
    
    func getNowPlayingMovies(){
        MovieService.getNowPlayingMovies { results, error  in
            if results != nil{
                self.movieView?.setNowPlayingMovies(results)
            } else{
                print("no results")
            }
        }
    }
}
