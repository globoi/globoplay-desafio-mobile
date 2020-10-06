//
//  ViewController.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 04/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import UIKit

class ViewController: UIViewController,  UITableViewDelegate, UITableViewDataSource, CollectionViewCellDelegate{
    
    @IBOutlet weak var moviesTV: UITableView!
    var flag :Bool!

    override func viewDidLoad() {
        
        let colViewCellId = "mcColCell"
        let tvCellId = "movieComp"
        
        let nibName = UINib(nibName: "movieComponentCell", bundle: nil)
        let nibNameCol = UINib(nibName: "MovieComponentCollectionViewCell", bundle: nil)
        self.moviesTV.register(nibName, forCellReuseIdentifier: tvCellId)
        
        moviesTV.delegate = self
        moviesTV.dataSource = self
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.destination.isKind(of: DetailsViewController.self) {
            let secondVC = segue.destination as! DetailsViewController
            secondVC.isFromHome = flag
        }
    }
    

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 3
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        if (indexPath.row == 0){
            let cell = tableView.dequeueReusableCell(withIdentifier: "movieComp", for: indexPath) as! MovieComponentTableViewCell
            cell.title.text = "Em Alta"
            cell.tableIndex = 0
            cell.name = "teste1"
            cell.delegate = self
            return cell
        }
        if (indexPath.row == 1){
            let cellMovies = tableView.dequeueReusableCell(withIdentifier: "movieComp", for: indexPath) as! MovieComponentTableViewCell
            cellMovies.title.text = "Movies"
            cellMovies.tableIndex = 1
            cellMovies.name = "teste2"
            cellMovies.delegate = self
            return cellMovies
        }
        else {
            let cellSeries = tableView.dequeueReusableCell(withIdentifier: "movieComp", for: indexPath) as! MovieComponentTableViewCell
            cellSeries.title.text = "Séries"
            cellSeries.name = "teste3"
            cellSeries.delegate = self
            cellSeries.tableIndex = 2
            return cellSeries
        }
    }

    func cellWasPressed() {
        flag = true
        self.performSegue(withIdentifier: "toDetailViewFromHome", sender: self)
    }
}
