//
//  HomeViewController.swift
//  globoplay-desafio
//
//  Created by Wagner Junior  on 01/05/21.
//

import UIKit

class HomeViewController: UIViewController {
    
    //MARK:- Properties
    let viewModel = HomeViewModel()
    
    //MARK:- IBOutlets
    @IBOutlet var moviesTableView: UITableView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        loadMovies()
    }
    
    fileprivate func loadMovies() {
        viewModel.loadPopularMovies()
    }
    
    fileprivate func loadUpcomingMovies() {
        
    }
    

}
