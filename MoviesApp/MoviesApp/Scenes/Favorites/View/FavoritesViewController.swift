//
//  FavoritesViewController.swift
//  MoviesApp
//
//  Created by Gustavo Tiecker on 22/02/22.
//

import UIKit

class FavoritesViewController: DataLoadingViewController {
    
    // MARK: - Properties
    private let viewModel: FavoritesBusinessLogic
    
    // MARK: - UI Elements
    private let collectionView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .vertical
        
        let collectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
        collectionView.register(MovieCollectionViewCell.self, forCellWithReuseIdentifier: MovieCollectionViewCell.reuseID)
        collectionView.translatesAutoresizingMaskIntoConstraints = false
        collectionView.showsVerticalScrollIndicator = false
        collectionView.backgroundColor = .black
        
        return collectionView
    }()
    
    // MARK: - Initializers
    init(viewModel: FavoritesBusinessLogic) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        collectionView.delegate = self
        collectionView.dataSource = self
        configureViewController()
        setupViewCodeElement()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        showLoadingView()
        viewModel.getFavoritesList { [weak self] movieList in
            guard let self = self else { return }
            self.updateUI(with: movieList)
            self.dismissLoadingView()
        }
    }
    
    // MARK: - Public Methods
    func configureViewController() {
        view.backgroundColor = .black
        title = "Favorites"
        navigationController?.navigationBar.prefersLargeTitles = true
        navigationController?.navigationBar.barStyle = .black
    }
    
    func updateUI(with favorites: [Movie]) {
        if favorites.isEmpty {
            debugPrint("Empty list")
        } else {
            viewModel.setFavoritesList(with: favorites)
            DispatchQueue.main.async {
                self.collectionView.reloadData()
                self.view.bringSubviewToFront(self.collectionView)
            }
        }
    }
}

extension FavoritesViewController: ViewCode {
    func setupComponents() {
        view.addSubview(collectionView)
    }
    
    func setupConstraints() {
        NSLayoutConstraint.activate([
            collectionView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
            collectionView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 16),
            collectionView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -16),
            collectionView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor)
        ])
    }
}

extension FavoritesViewController: UICollectionViewDelegateFlowLayout {
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let width = collectionView.frame.size.width
        return CGSize(width: ((width/3) - 7), height: 200)
    }
}

extension FavoritesViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return viewModel.getFavoritesCount()
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: MovieCollectionViewCell.reuseID, for: indexPath) as! MovieCollectionViewCell
        viewModel.downloadImage(at: indexPath) { image in
            DispatchQueue.main.async {
                cell.setup(with: image)
            }
        }
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let detailsViewController = viewModel.goToDetailsScreen(of: indexPath)
        navigationController?.pushViewController(detailsViewController, animated: true)
    }
}
