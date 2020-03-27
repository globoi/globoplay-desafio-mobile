//
//  MoviesListViewController.swift
//  Desafio Globoplay
//
//  Created by Gabriel Oliveira on 22/3/20.
//  Copyright © 2020 Gabriel Oliveira. All rights reserved.
//

import UIKit

protocol MoviesListViewProtocol: class {
    func display()
}

struct IndexPathDict {
    var section: String
    var row: Int
}

class MoviesListViewController: UIViewController {
    //MARK: Outlets
    @IBOutlet weak var moviesCollectionView: UICollectionView!
    @IBOutlet weak var soapOpCollectionView: UICollectionView!
    @IBOutlet weak var tvShowsCollectionView: UICollectionView!
    
    var presenter: MoviesListPresenterProtocol?
    
    //MARK: Life Cycle
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.setUp(collectionView: self.moviesCollectionView)
        self.setUp(collectionView: self.soapOpCollectionView)
        self.setUp(collectionView: self.tvShowsCollectionView)
        self.presenter?.getData()
    }
    
    func setUp(collectionView: UICollectionView) {
        collectionView.delegate = self
        collectionView.dataSource = self
        
        collectionView.backgroundColor = Colors.collectionBG
        collectionView.showsHorizontalScrollIndicator = false
        collectionView.showsVerticalScrollIndicator = false
        
        collectionView.collectionViewLayout = self.layout()
    }
    
    func layout() -> UICollectionViewCompositionalLayout {
        let itemSize = NSCollectionLayoutSize(widthDimension: .fractionalWidth(1),
                                              heightDimension: .fractionalHeight(1))
        
        let item = NSCollectionLayoutItem(layoutSize: itemSize)
        
        let groupSize = NSCollectionLayoutSize(widthDimension: .absolute(106),
                                               heightDimension: .absolute(159))
        
        let group = NSCollectionLayoutGroup.vertical(layoutSize: groupSize,
                                                     subitem: item, count: 1)
        
        group.edgeSpacing = NSCollectionLayoutEdgeSpacing(leading: nil,
                                                          top: .flexible(0),
                                                          trailing: nil,
                                                          bottom: .flexible(0))
        
        let section = NSCollectionLayoutSection(group: group)
        section.interGroupSpacing = 10
        
        let config = UICollectionViewCompositionalLayoutConfiguration()
        config.scrollDirection = .horizontal
        
        let layout = UICollectionViewCompositionalLayout(section: section, configuration:config)
        return layout
    }
}

extension MoviesListViewController: MoviesListViewProtocol {
    func display() {
        DispatchQueue.main.async {
            self.moviesCollectionView.reloadData()
            self.soapOpCollectionView.reloadData()
            self.tvShowsCollectionView.reloadData()
        }
    }
}

//MARK: - Collection Delegate
extension MoviesListViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        self.presenter?.showDetails(of: IndexPathDict(section: self.getCollectionIndex(for: collectionView),
                                                      row: indexPath.row))
    }
}

//MARK: - Collection DataSource
extension MoviesListViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.presenter?.getNumberOfItems(for: self.getCollectionIndex(for: collectionView)) ?? 0
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "movieCell", for: indexPath) as? MovieCellCollectionViewCell else { return UICollectionViewCell() }
        
        self.presenter?.configure(cell, at: IndexPathDict(section: self.getCollectionIndex(for: collectionView),
                                                          row: indexPath.row))
        
        return cell
    }
    
    private func getCollectionIndex(for collection: UICollectionView) -> String {
        switch collection {
            case soapOpCollectionView: return "popularMovies"
            case tvShowsCollectionView: return "topRatedMovies"
            case moviesCollectionView: return "tvShows"
            
            default: return ""
        }
    }
}
