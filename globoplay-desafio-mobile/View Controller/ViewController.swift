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
    
   // var favoriteListArray :[Movie]? = []
    
    @IBOutlet weak var activityInd: UIActivityIndicatorView!
  
    override func viewDidLoad() {
        
//        let defaults = UserDefaults.standard
//        let encodedData = try? NSKeyedArchiver.archivedData(withRootObject: favoriteListArray, requiringSecureCoding: false)
//        defaults.set(encodedData, forKey: "favoriteListArray")
        
        MovieService.getUpcomingMovies { results, error  in
            if results != nil{
                self.upcomingMovieList = results
                self.moviesTV.reloadData()
            } else{
                print("no results")
            }
        }
        
        MovieService.getPopularMovies { results, error  in
            if results != nil{
                self.popularMovieList = results
                self.moviesTV.reloadData()
            } else{
                print("no results")
            }
        }
        
        MovieService.getNowPlayingMovies { results, error  in
            if results != nil{
                self.playingMovieList = results
                self.moviesTV.reloadData()
            } else{
                print("no results")
            }
        }
        
        let colViewCellId = "mcColCell"
        let tvCellId = "movieComp"
        
        let nibName = UINib(nibName: "movieComponentCell", bundle: nil)
        let nibNameCol = UINib(nibName: "MovieComponentCollectionViewCell", bundle: nil)
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
//            secondVC.isFromHome = flag
//            secondVC.indexList = indice
//            secondVC.playingMovieList = playingMovieList
//            secondVC.popularMovieList = popularMovieList
//            secondVC.upcomingMovieList = upcomingMovieList
//            secondVC.tableIndex = tableIndex
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
            cell.name = "teste1"
            cell.delegate = self
            cell.movieList = upcomingMovieList
            return cell
        }
        if (indexPath.row == 1){
            let cellMovies = tableView.dequeueReusableCell(withIdentifier: "movieComp", for: indexPath) as! MovieComponentTableViewCell
            cellMovies.title.text = "Filmes Populares"
            cellMovies.tableIndex = 1
            cellMovies.name = "teste2"
            cellMovies.delegate = self
            cellMovies.movieList = popularMovieList
            return cellMovies
        }
        else {
            let cellSeries = tableView.dequeueReusableCell(withIdentifier: "movieComp", for: indexPath) as! MovieComponentTableViewCell
            cellSeries.title.text = "Filmes Em Cartaz"
            cellSeries.name = "teste3"
            cellSeries.delegate = self
            cellSeries.tableIndex = 2
            cellSeries.movieList = playingMovieList
            return cellSeries
        }
    }

    func cellWasPressed(index: Int, tableId: Int) {
        flag = true
        indice = index
        tableIndex = tableId
        self.performSegue(withIdentifier: "toDetailViewFromHome", sender: self)
    }
}
