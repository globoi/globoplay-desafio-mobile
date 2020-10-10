//
//  ViewController.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 04/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import UIKit
import Alamofire
import ObjectMapper

class ViewController: UIViewController,  UITableViewDelegate, UITableViewDataSource, CollectionViewCellDelegate{
    
    @IBOutlet weak var moviesTV : UITableView!
    var flag                    : Bool!
    var indice                  : Int!
    var tableIndex              : Int!
    var upcomingMovieList       : [Movie]?
    var popularMovieList        : [Movie]?
    var playingMovieList        : [Movie]?
        
    @IBOutlet weak var activityInd: UIActivityIndicatorView!
    
    fileprivate let presenter = MoviePresenter(dataService: MovieService())
  
    override func viewDidLoad() {
        
        presenter.attachView(self)
        
        presenter.getUpcomingListView()
        presenter.getPopularMovies()
        presenter.getNowPlayingMovies()

        let tvCellId = "movieComp"
        let nibName = UINib(nibName: "movieComponentCell", bundle: nil)
        self.moviesTV.register(nibName, forCellReuseIdentifier: tvCellId)
        
        moviesTV.delegate = self
        moviesTV.dataSource = self
        
        activityInd.hidesWhenStopped = true
        activityInd.center = view.center
        activityInd.startAnimating()
        
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.destination.isKind(of: DetailsViewController.self) {
            let secondVC = segue.destination as! DetailsViewController
            secondVC.isFromHome = flag
            secondVC.indexList = indice
            
            if (tableIndex == 0){
                secondVC.currentMovie = upcomingMovieList?[indice]
            }else if (tableIndex == 1){
                secondVC.currentMovie = popularMovieList?[indice]
            }else if (tableIndex == 2){
                secondVC.currentMovie = playingMovieList?[indice]
            }
            secondVC.tableIndex = tableIndex
        }
    }
    

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if upcomingMovieList == nil || popularMovieList == nil || playingMovieList == nil {
            return 0
        }
        activityInd.stopAnimating()
        return 3
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        if (indexPath.row == 0){
            let cell = tableView.dequeueReusableCell(withIdentifier: "movieComp", for: indexPath) as! MovieComponentTableViewCell
            cell.title.text = "Filmes Em Breve"
            cell.tableIndex = 0
            cell.delegate = self
            cell.movieList = upcomingMovieList
            cell.selectionStyle = .none
            return cell
        }
        if (indexPath.row == 1){
            let cell = tableView.dequeueReusableCell(withIdentifier: "movieComp", for: indexPath) as! MovieComponentTableViewCell
            cell.title.text = "Filmes Populares"
            cell.tableIndex = 1
            cell.delegate = self
            cell.movieList = popularMovieList
            cell.selectionStyle = .none
            return cell
        }
        else {
            let cell = tableView.dequeueReusableCell(withIdentifier: "movieComp", for: indexPath) as! MovieComponentTableViewCell
            cell.title.text = "Filmes Em Cartaz"
            cell.delegate = self
            cell.tableIndex = 2
            cell.movieList = playingMovieList
            cell.selectionStyle = .none
            return cell
        }
    }
    
    //MARK: CollectionViewCellDelegate

    func cellWasPressed(index: Int, tableId: Int) {
        flag = true
        indice = index
        tableIndex = tableId
        self.performSegue(withIdentifier: "toDetailViewFromHome", sender: self)
    }
}

extension ViewController: MovieViewProtocol {
    
    func setUpcomingListView(_ movieList: [Movie]?) {
        self.upcomingMovieList = movieList
        self.moviesTV.reloadData()
    }
    
    func setPopularListView(_ movieList: [Movie]?) {
        self.popularMovieList = movieList
        self.moviesTV.reloadData()
    }
    
    func setNowPlayingMovies(_ movieList: [Movie]?) {
        self.playingMovieList = movieList
        self.moviesTV.reloadData()
    }
}
