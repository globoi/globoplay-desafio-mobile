//
//  ViewController.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 04/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import UIKit

class ViewController: UIViewController,  UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var moviesTV: UITableView!
    
    //toDetailsView
    override func viewDidLoad() {
        
        let colViewCellId = "mcColCell"
        let tvCellId = "movieComp"
        
        let nibName = UINib(nibName: "movieComponentCell", bundle: nil)
        let nibNameCol = UINib(nibName: "MovieComponentCollectionViewCell", bundle: nil)
        self.moviesTV.register(nibName, forCellReuseIdentifier: tvCellId)
        
        moviesTV.delegate = self
        moviesTV.dataSource = self
        
//        xibView.collectionView.register(UINib(nibName: "MovieComponentCollectionViewCell", bundle: nil), forCellWithReuseIdentifier: "MovieComponentCollectionViewCell")

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
            return cell
        }
        if (indexPath.row == 1){
            let cellMovies = tableView.dequeueReusableCell(withIdentifier: "movieComp", for: indexPath) as! MovieComponentTableViewCell
            cellMovies.title.text = "Movies"
            cellMovies.tableIndex = 1
            cellMovies.name = "teste2"
            return cellMovies
        }
        else {
            let cellMovies = tableView.dequeueReusableCell(withIdentifier: "movieComp", for: indexPath) as! MovieComponentTableViewCell
            cellMovies.title.text = "Séries"
            cellMovies.name = "teste3"
            cellMovies.tableIndex = 2
            return cellMovies
        }
    }  
}
