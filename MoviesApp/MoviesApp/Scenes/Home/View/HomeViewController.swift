//
//  HomeViewController.swift
//  MoviesApp
//
//  Created by Gustavo Tiecker on 22/02/22.
//

import UIKit

class HomeViewController: UIViewController {
    
    private let viewModel: HomeBusinessLogic
    
    private let trendHeaderTitleLabel: TitleLabel = {
        let label = TitleLabel(textAlignment: .left, fontSize: 24)
        label.text = "Em alta"
        return label
    }()
    
    private let collectionView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .horizontal
        
        let collectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
        collectionView.register(MovieCollectionViewCell.self, forCellWithReuseIdentifier: MovieCollectionViewCell.reuseID)
        collectionView.translatesAutoresizingMaskIntoConstraints = false
        collectionView.showsHorizontalScrollIndicator = false
        collectionView.backgroundColor = .black
        
        return collectionView
    }()
    
    init(viewModel: HomeBusinessLogic) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureViewController()
        setDelegates()
        setupViewCodeElement()
        
        viewModel.fetchTrendMovieList {
            DispatchQueue.main.async {
                self.collectionView.reloadData()
            }
        }
    }
    
    private func configureViewController() {
        view.backgroundColor = .black
        navigationController?.navigationBar.prefersLargeTitles = true
        navigationController?.navigationBar.barStyle = .black
    }
    
    private func setDelegates() {
        collectionView.delegate = self
        collectionView.dataSource = self
    }
}

extension HomeViewController: ViewCode {
    func setupComponents() {
        view.addSubview(trendHeaderTitleLabel)
        view.addSubview(collectionView)
    }
    
    func setupConstraints() {
        NSLayoutConstraint.activate([
            trendHeaderTitleLabel.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
            trendHeaderTitleLabel.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 16),
            trendHeaderTitleLabel.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -16),
            trendHeaderTitleLabel.heightAnchor.constraint(equalToConstant: 56),
            
            collectionView.topAnchor.constraint(equalTo: trendHeaderTitleLabel.bottomAnchor),
            collectionView.leadingAnchor.constraint(equalTo: trendHeaderTitleLabel.leadingAnchor),
            collectionView.trailingAnchor.constraint(equalTo: trendHeaderTitleLabel.trailingAnchor),
            collectionView.heightAnchor.constraint(equalToConstant: 230)
        ])
    }
}

extension HomeViewController: UICollectionViewDelegateFlowLayout {
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return viewModel.getNumberOfSections()
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: collectionView.frame.width/3, height: collectionView.frame.width/2)
    }
}

extension HomeViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return viewModel.getNumberOfRowsIn(section: section)
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

extension HomeViewController: HomeViewModelViewDelegate {
    
    func didFinishWithError(_ error: APIError) {
        debugPrint(error.rawValue)
    }
}
