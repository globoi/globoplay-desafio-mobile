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
    
    // Indexes
    public var popularIndex = 0
    public var topIndex = 0
    public var moviesIndex = 0
    
    // Filters
    public var genders : [Int64] = [Int64]()
    public var searchText : String?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.view.backgroundColor = UIColor.black
        self.tableView.delegate = self
        self.tableView.dataSource = self
        self.tableView.register(UINib.init(nibName: "CardSessionTableViewCell", bundle: nil), forCellReuseIdentifier: "Cell")
        self.tableView.showsVerticalScrollIndicator = false
        self.loadData()
        self.addFilter()
        self.configVisual()
    }
    
    func addFilter() {
        let filterItem = UIBarButtonItem.init(barButtonSystemItem: .bookmarks, target: self, action: #selector(self.openFilterController))
        filterItem.tintColor = UIColor.white
        self.navigationItem.rightBarButtonItem = filterItem
    }
    
    @objc func openFilterController() {
        let controller = FilterTableViewController(controller: self, filters: self.genders)
        self.present(controller, animated: true) {
            
        }
    }
    
    func configVisual() {
        // Search Bar
        self.searchController.view.backgroundColor = UIColor.clear
        self.searchController.searchBar.backgroundColor = UIColor.black
        self.searchController.obscuresBackgroundDuringPresentation = false
        self.searchController.hidesNavigationBarDuringPresentation = false
        self.searchController.searchResultsUpdater = self

        self.searchController.searchBar.placeholder = "Buscar filmes"
        self.searchController.searchBar.delegate = self
//        self.searchController.searchBar.barStyle = .black
        
        self.tableView.tableHeaderView = self.searchController.searchBar

        self.tableView.separatorStyle = .none
        self.tableView.backgroundView = UIView();
    }
    
    
    
    func loadData() {
        self.popularIndex = 0
        self.topIndex = 0
        self.moviesIndex = 0
        // Carrega os mais populares
        self.loadPopular(page: self.popularIndex, append: false)
        // Carrega os mais votados
        self.loadTop(page: self.topIndex, append: false )
        // Carrega os filmes
        self.loadMovies(page: self.moviesIndex, append: false)
    }
    
    func loadPopular(page: Int, append: Bool) {

        ApplicationService.sharedInstance.getTVPopular(genres: self.genders, page: page, search: self.searchText, callback: { (cards: [Card], error: String?) in
            StaticFunctions.removeActivityIndicatorView()
            if let error = error {
                StaticFunctions.showSimpleAlert(controller: self, title: "Ops!", message: error)
                return
            }
            if append {
                self.cardsPopular += cards
            } else {
                self.cardsPopular = cards
            }
            self.tableView.reloadData()
        })
    }
    
    func loadTop(page: Int, append: Bool) {

        ApplicationService.sharedInstance.getTVTopRated(genres: self.genders, page: page, search: self.searchText, callback: { (cards: [Card], error: String?) in
            StaticFunctions.removeActivityIndicatorView()
            
            if let error = error {
                StaticFunctions.showSimpleAlert(controller: self, title: "Ops!", message: error)
                return
            }
            if append {
                self.cardsTopRated += cards
            } else {
                self.cardsTopRated = cards
            }
            self.tableView.reloadData()
        })
    }
    
    func loadMovies(page: Int, append: Bool) {

        ApplicationService.sharedInstance.getMovies(genres: self.genders, page: page, search: self.searchText, callback: { (cards: [Card], error: String?) in
            StaticFunctions.removeActivityIndicatorView()
            
            if let error = error {
                StaticFunctions.showSimpleAlert(controller: self, title: "Ops!", message: error)
                return
            }
            if append {
                self.cardsMovie += cards
            } else {
                self.cardsMovie = cards
            }
            self.tableView.reloadData()
        })
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
        let section = indexPath.section
        if section == 0 && self.cardsPopular.count == 0 {
            return 0
        } else if section == 1 && self.cardsTopRated.count == 0 {
            return 0
        } else if section == 2 && self.cardsMovie.count == 0 {
            return 0
        }
        let itemHeight = StaticFunctions.getItemHeight()
        return itemHeight
    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let view = UIView(frame: CGRect(x: 0, y: 0, width: screenWidth-20, height: 50))
        
        let label = UILabel(frame: CGRect(x: 10, y: 0, width: screenWidth-40, height: 50))
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
        label.font = UIFont.systemFont(ofSize: 22)
        label.backgroundColor = UIColor.clear
        
        view.backgroundColor = UIColor.black
        view.addSubview(label)
    
        return view
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 50
    }
}

extension HomeViewController: UISearchResultsUpdating {
  func updateSearchResults(for searchController: UISearchController) {
//    print(searchController.searchBar.text)
  }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        let text = searchBar.text
        self.searchText = text
        self.loadData()
    }
    
    func searchBarCancelButtonClicked(_ searchBar: UISearchBar) {
        self.searchText = nil
        self.loadData()
    }
}
