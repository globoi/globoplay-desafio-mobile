//
//  DetailsViewController.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 05/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import UIKit

class DetailsViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, CollectionViewCellRelatedDelegate {
    
    @IBOutlet weak var detailsTV: UITableView!
    
    
    let idDetailsCell           = "bodyDetailsCell"
    let idWatchCell             = "watchDetailsCell"
    let idHeaderCell            = "headerDetailsCell"
    let idSegmentedCell         = "segmentedCell"
    
    var myMovieList             : [Movie]?
    var indexList               : Int?
    var tableIndex              : Int?
    var youTubeID               : String?
    
    private var listDetails     : MovieDetails?
    private var list            : [Movie]?
    var currentMovie            : Movie?
    private var relatedList     : [Movie]?
    
    var actualIndex2            : Int!
    var isFromHome              : Bool!
    
    fileprivate let presenter   = DetailsPresenter(dataService: MovieService())
    
    override func viewWillAppear(_ animated: Bool){
        super.viewWillAppear(animated)
        presenter.getYoutubeId(id: String(currentMovie!.id))
        presenter.getDetailsList(id: currentMovie!.id)
        presenter.getRelatedList(id: currentMovie!.id)
    }
    

    override func viewDidLoad() {
        super.viewDidLoad()
        
        presenter.attachView(self)
        
        let nibName             = UINib(nibName: "detailsHeaderTableViewCell", bundle: nil)
        let nibBodyName         = UINib(nibName: "detailsBodyTableViewCell", bundle: nil)
        let nibCollectionWatch  = UINib(nibName: "detailsWatchTableViewCell", bundle: nil)
        
        self.detailsTV.register(nibBodyName, forCellReuseIdentifier: idDetailsCell)
        self.detailsTV.register(nibCollectionWatch, forCellReuseIdentifier: idWatchCell)
        self.detailsTV.register(nibName, forCellReuseIdentifier: idHeaderCell)
    }
    

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 2
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {

        if (indexPath.row == 0){
            let cell = tableView.dequeueReusableCell(withIdentifier: idHeaderCell, for: indexPath) as! DetailsHeaderTableViewCell

            actualIndex2 = cell.segmentedControlValue
            
            cell.segmentedControlDetails.addTarget(self, action: #selector(self.onSegChange(_:)), for: .valueChanged)
            
            cell.isMinhaLista = isFromHome ? false : true
            
            let path = currentMovie?.poster_path ?? ""
            let imageUrl = URL(string: CONST.API_CONSTANTS.BASE_IMAGE_URL + path )
            cell.imageTitle.kf.setImage(with: imageUrl)
            cell.name.text = currentMovie?.title ?? ""
            cell.youTubeID = youTubeID
            cell.currentMovie = currentMovie
            cell.selectionStyle = .none
            return cell
        }
        
        else {
            
            if (actualIndex2 == 0){
                let cell = tableView.dequeueReusableCell(withIdentifier: idWatchCell, for: indexPath) as! DetailsWatchTableViewCell
                cell.currentMovieId = String(currentMovie!.id)
                cell.currentMovie = currentMovie
                cell.delegate = self
                cell.relatedList = relatedList
                cell.detailsCollectionView.reloadData()
                cell.selectionStyle = .none
                return cell

            }
            else{
                let cell = tableView.dequeueReusableCell(withIdentifier: idDetailsCell, for: indexPath) as! DetailsBodyTableViewCell
                cell.titleLabel.text = listDetails?.original_title
                cell.genreLabel.text = listDetails?.genres?.first?.name
                cell.descriptionLabel.text = listDetails?.overview
                cell.averageVotesLabel.text = String(listDetails!.vote_average)
                cell.releaseDateLabel.text = listDetails?.release_date
                cell.selectionStyle = .none
                return cell
            }
        }
    }

    //Mark: Actions
    
    @objc func onSegChange(_ sender: UISegmentedControl) {
        self.actualIndex2 = sender.selectedSegmentIndex
        self.detailsTV.reloadData()
    }
    
    @IBAction func dismissView(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }
    
    //MARK: Delegate
    
    func cellWasPressed(newCurrentMovie: Movie) {
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let vc = storyboard.instantiateViewController(withIdentifier: "DetailsViewController") as! DetailsViewController
        vc.currentMovie = newCurrentMovie
        vc.isFromHome = false
        self.navigationController!.pushViewController(vc, animated:false)
    }
}

extension DetailsViewController: DetailsViewProtocol {
    func setRelatedList(_ relatedList: [Movie]?) {
        self.relatedList = relatedList
        self.detailsTV.reloadData()
    }
    
    func setYoutubeId(_ id: String?) {
        self.youTubeID = id
        self.detailsTV.reloadData()
    }
    
    func setDetailsList(_ movieList: MovieDetails?) {
        self.listDetails = movieList
        self.detailsTV.reloadData()
    }
}

