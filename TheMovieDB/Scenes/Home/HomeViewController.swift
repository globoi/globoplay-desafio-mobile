//
//  HomeViewController.swift
//  TheMovieDB
//
//  Created by Edwy Lugo on 03/02/20.
//  Copyright © 2020 Edwy Lugo. All rights reserved.
//

import UIKit
import ImageLoader

class HomeViewController: UIViewController, UISearchBarDelegate {
    
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    @IBOutlet weak var tableView: UITableView!
    
    let searchController = UISearchController(searchResultsController: nil)
    
    public var cardsPopular = [Card]()
    public var cardsTopRated = [Card]()
    public var cardsMovie = [Card]()
    
    // Indexes
    public var popularIndex = 0
    public var topIndex = 0
    public var moviesIndex = 0
    
    public var popularLimit = false
    public var topLimit = false
    public var moviesLimit = false
    
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
                self.searchController.searchBar.backgroundColor = UIColor.white
                self.searchController.obscuresBackgroundDuringPresentation = false
                self.searchController.hidesNavigationBarDuringPresentation = false
                self.searchController.searchResultsUpdater = self
        self.searchController.searchBar.isTranslucent = false
        self.searchController.searchBar.tintColor = UIColor.white
        self.searchController.searchBar.barTintColor = UIColor(named: "background")
        self.searchController.searchBar.layer.borderColor = UIColor(named: "background")?.cgColor
        self.searchController.searchBar.layer.borderWidth = 1.00
        self.searchController.searchBar.searchTextField.textColor = UIColor.black
        self.searchController.searchBar.searchTextField.borderStyle = .none
        self.searchController.searchBar.searchTextField.backgroundColor = .white
        self.searchController.searchBar.searchTextField.layer.cornerRadius = 10
        self.searchController.searchBar.searchTextField.clipsToBounds = true

                self.searchController.searchBar.placeholder = "Buscar filmes"
                self.searchController.searchBar.delegate = self
    
        self.tableView.tableHeaderView = self.searchController.searchBar

        self.tableView.separatorStyle = .none
        self.tableView.backgroundView = UIView();
    }
    
    
    
    func loadData() {
        Functions.showActivityIndicatorView(onView: self.view)
        self.popularIndex = 0
        self.topIndex = 0
        self.moviesIndex = 0
        self.popularLimit = false
        self.topLimit = false
        self.moviesLimit = false
        // Carrega os mais populares
        self.loadPopular(page: self.popularIndex, append: false)
        // Carrega os mais votados
        self.loadTop(page: self.topIndex, append: false )
        // Carrega os filmes
        self.loadMovies(page: self.moviesIndex, append: false)
    }
    
    func loadPopular(page: Int, append: Bool) {

        TheMovieDBAPI.sharedInstance.getTVPopular(genres: self.genders, page: page, search: self.searchText, callback: { (cards: [Card], pages: Int64,  error: String?) in
            Functions.removeActivityIndicatorView()
            if let error = error {
                Functions.showSimpleAlert(controller: self, title: "Ops!", message: error)
                return
            }
            if append {
                self.cardsPopular += cards
            } else {
                self.cardsPopular = cards
            }
            if pages <= 1 {
                self.popularLimit = true
            }
            self.tableView.reloadData()
        })
    }
    
    func loadTop(page: Int, append: Bool) {

        TheMovieDBAPI.sharedInstance.getTVTopRated(genres: self.genders, page: page, search: self.searchText, callback: { (cards: [Card], pages: Int64, error: String?) in
            Functions.removeActivityIndicatorView()
            
            if let error = error {
                Functions.showSimpleAlert(controller: self, title: "Ops!", message: error)
                return
            }
            if append {
                self.cardsTopRated += cards
            } else {
                self.cardsTopRated = cards
            }
            if pages <= 1 {
                self.topLimit = true
            }
            self.tableView.reloadData()
        })
    }
    
    func loadMovies(page: Int, append: Bool) {

        TheMovieDBAPI.sharedInstance.getMovies(genres: self.genders, page: page, search: self.searchText, callback: { (cards: [Card], pages: Int64, error: String?) in
            Functions.removeActivityIndicatorView()
            
            if let error = error {
                Functions.showSimpleAlert(controller: self, title: "Ops!", message: error)
                return
            }
            if append {
                self.cardsMovie += cards
            } else {
                self.cardsMovie = cards
            }
            if pages <= 1 {
                self.moviesLimit = true
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
        let itemHeight = Functions.getItemHeight()
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
        
        view.backgroundColor = UIColor(named: "background")
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
