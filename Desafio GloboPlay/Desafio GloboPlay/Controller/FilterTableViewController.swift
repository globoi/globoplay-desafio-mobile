//
//  FilterTableViewController.swift
//  Desafio GloboPlay
//
//  Created by Filipo Negrao on 29/11/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import UIKit

class FilterTableViewController: UITableViewController {

    public var genres = [Genre]()
    public var selected = [Int64]()
    
    private weak var controller : HomeViewController!
    
    init(controller: HomeViewController, filters: [Int64]) {
        super.init(nibName: nil, bundle: nil)
        self.controller = controller
        self.selected = filters
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.configTableView()
        self.loadData()
    }
    
    func loadData() {
        let mapGenre = ApplicationService.sharedInstance.mapGenres
        for value in mapGenre {
            let genre = value.value
            genres.append(genre)
        }
        self.tableView.reloadData()
    }
    
    func configTableView() {
        self.view.backgroundColor = UIColor.black
        self.tableView.register(UITableViewCell.self, forCellReuseIdentifier: "Cell")
        self.tableView.separatorInset.left = 0
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return 1 + self.genres.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)
        cell.backgroundColor = UIColor.clear
        if indexPath.row == 0 {
            cell.textLabel?.text = "Todas"
            // Verifica o filtro
            if (self.selected.count == 0) {
                cell.accessoryType = .checkmark
            } else {
                cell.accessoryType = .none
            }
        } else {
            let genre = self.genres[indexPath.row-1]
            // Verifica o filtro
            if (self.isSelected(id: genre.id)) {
                cell.accessoryType = .checkmark
            } else {
                cell.accessoryType = .none
            }
            cell.textLabel?.text = genre.name
        }

        cell.accessoryView?.tintColor = UIColor.white
        cell.textLabel?.textColor = UIColor.white
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        
        let index = indexPath.row
        
        if index == 0 {
            self.selected = [Int64]()
            self.tableView.reloadData()
        } else {
            let genre = self.genres[index-1]
            // Se ja nao esta selecionado
            if !self.isSelected(id: genre.id) {
                self.selected.append(genre.id)
                self.tableView.reloadData()
            } else {
                // Se ja estiver, remove dos selecionados
                if let index = self.selected.firstIndex(of: genre.id) {
                    self.selected.remove(at: index)
                    self.tableView.reloadData()
                }
            }
        }
    }
    
    private func isSelected(id: Int64) -> Bool {
        for selected in self.selected {
            if selected == id {
                return true
            }
        }
        return false
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        self.controller.genders = self.selected
        self.controller.loadData()
    }

}
