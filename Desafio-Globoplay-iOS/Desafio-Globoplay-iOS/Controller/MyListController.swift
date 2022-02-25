//
//  MyListController.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 23/02/22.
//

import UIKit

class MyListController: UIViewController {
    
    // MARK: - Properties
    
    private let reuseIdentifier = "minhaListaTableViewCell"
    
    private let mylistTableView: UITableView = {
        let tableView = UITableView()
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "minhaListaTableViewCell")
        return tableView
    }()
    
    // MARK: - Lifecycle
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        mylistTableView.frame = view.bounds
    }
    
    // MARK: - API
    
    // MARK: - Helper Methods
    
    func configureUI() {
        view.backgroundColor = .homeBlack
        title = "Minha lista"
        navigationController?.navigationBar.prefersLargeTitles = true
        navigationController?.navigationItem.largeTitleDisplayMode = .always
        
        view.addSubview(mylistTableView)
        mylistTableView.delegate = self
        mylistTableView.dataSource = self
    }
    
    // MARK: - Selectors
    
}

// MARK: - UITableViewDelegate

extension MyListController: UITableViewDelegate, UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        10
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "minhaListaTableViewCell", for: indexPath)
        cell.textLabel?.text = "MOVIES!!!"
        return cell
    }
}
