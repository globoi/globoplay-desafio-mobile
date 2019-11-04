//
//  HomeViewController.swift
//  Desafio GloboPlay
//
//  Created by Filipo Negrao on 02/11/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import UIKit
import ImageLoader


class HomeViewController: UIViewController, UISearchBarDelegate {
    
    @IBOutlet weak var tableView: UITableView!
    
    let searchController = UISearchController(searchResultsController: nil)
    
    public var cardsPopular = [Card]()
    public var cardsTopRated = [Card]()
    public var cardsMovie = [Card]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.tableView.delegate = self
        self.tableView.dataSource = self
        self.tableView.register(UINib.init(nibName: "CardSessionTableViewCell", bundle: nil), forCellReuseIdentifier: "Cell")
        self.tableView.showsVerticalScrollIndicator = false
        self.loadData()
//        self.configVisual()
    }
    
//    func configVisual() {
//        // Search Bar
//        self.searchController.view.backgroundColor = UIColor.clear
//        self.searchController.searchBar.backgroundColor = UIColor.clear
//        self.searchController.obscuresBackgroundDuringPresentation = false
//        self.searchController.hidesNavigationBarDuringPresentation = false
//        self.searchController.searchBar.placeholder = "Buscar filmes"
//        self.searchController.searchBar.delegate = self
//        self.tableView.tableHeaderView.
//        self.tableView.tableHeaderView = self.searchController.searchBar
//        self.tableView.tableHeaderView?.backgroundColor = UIColor.clear
//        self.tableView.backgroundView?.backgroundColor = UIColor.clear
//
//        self.tableView.separatorStyle = .none
//
//        for view in tableView.subviews {
//            for subview in view.subviews {
//                subview.backgroundColor = UIColor.clear
//            }
//        }
//    }
    
    
    
    func loadData() {
        StaticFunctions.showActivityIndicatorView(onView: self.view)
        // Carrega os mais populares
        ApplicationService.sharedInstance.getTVPopular { (cards: [Card], error: String?) in
            StaticFunctions.removeActivityIndicatorView()
            if let error = error {
                StaticFunctions.showSimpleAlert(controller: self, title: "Ops!", message: error)
                return
            }
            self.cardsPopular = cards
            self.tableView.reloadData()
        }
        ApplicationService.sharedInstance.getTVTopRated { (cards: [Card], error: String?) in
            StaticFunctions.removeActivityIndicatorView()
            
            if let error = error {
                StaticFunctions.showSimpleAlert(controller: self, title: "Ops!", message: error)
                return
            }
            self.cardsTopRated = cards
            self.tableView.reloadData()
        }
        ApplicationService.sharedInstance.getMovies { (cards: [Card], error: String?) in
            StaticFunctions.removeActivityIndicatorView()
            
            if let error = error {
                StaticFunctions.showSimpleAlert(controller: self, title: "Ops!", message: error)
                return
            }
            self.cardsMovie = cards
            self.tableView.reloadData()
        }
        
    }
    
}

// MARK: Table view data source and delegate methods
extension HomeViewController: UITableViewDelegate, UITableViewDataSource {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 3
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath) as! CardSessionTableViewCell
        var cards : [Card]
        switch indexPath.section {
        case 0:
            cards = self.cardsPopular
        case 1:
            cards = self.cardsTopRated
        case 2:
            cards = self.cardsMovie
        default:
            cards = self.cardsPopular
        }
        cell.configCell(cards: cards, section: indexPath.section, controller: self)
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        let itemHeight = StaticFunctions.getItemHeight()
        return itemHeight
    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let label = UILabel(frame: CGRect(x: 0, y: 0, width: screenWidth-20, height: 50))
        var text = "Carregando..."
        switch section {
        case 0:
            text = "Mais populares"
        case 1:
            text = "Mais votados"
        case 2:
            text = "Cinema"
        default:
            text = ""
        }
        label.text = text
        label.textColor = UIColor.white
        label.font = UIFont.boldSystemFont(ofSize: 22)
        label.padding = UIEdgeInsets(top: 10, left: 10, bottom: 0, right: 0)
        label.backgroundColor = gray
        return label
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 50
    }
}
