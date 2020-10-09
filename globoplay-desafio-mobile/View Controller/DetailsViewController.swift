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
    
    
    let idDetailsCell = "bodyDetailsCell"
    let idWatchCell = "watchDetailsCell"
    let idHeaderCell = "headerDetailsCell"
    let idSegmentedCell = "segmentedCell"
    
  //  var upcomingMovieList       : [Movie]?
  //  var popularMovieList        : [Movie]?
  //  var playingMovieList        : [Movie]?
    var myMovieList             : [Movie]?
    var indexList               : Int?
    var tableIndex              : Int?
    var youTubeID               : String?
    
    private var listDetails     :MovieDetails?
    private var list            :[Movie]?
    var currentMovie            :Movie?
    
    var actualIndex2 :Int!
    var isFromHome :Bool!
    
    override func viewWillAppear(_ animated: Bool){
        super.viewWillAppear(animated)
        getYoutubeId(id: String(currentMovie!.id))
        getDetailsList(id: currentMovie!.id)
    }
    

    override func viewDidLoad() {
        super.viewDidLoad()
        
        let nibName             = UINib(nibName: "detailsHeaderTableViewCell", bundle: nil)
        let nibBodyName         = UINib(nibName: "detailsBodyTableViewCell", bundle: nil)
        let nibCollectionWatch  = UINib(nibName: "detailsWatchTableViewCell", bundle: nil)
        
        self.detailsTV.register(nibBodyName, forCellReuseIdentifier: idDetailsCell)
        self.detailsTV.register(nibCollectionWatch, forCellReuseIdentifier: idWatchCell)
        self.detailsTV.register(nibName, forCellReuseIdentifier: idHeaderCell)
        
        
       // self.list = fillPopulatedList()
        
        
       // currentMovie = list![indexList!]
        
        
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
            var imageUrl = URL(string: CONST.API_CONSTANTS.BASE_IMAGE_URL + path )
            cell.imageTitle.kf.setImage(with: imageUrl)
            cell.name.text = currentMovie?.title ?? ""
            cell.youTubeID = youTubeID
            cell.currentMovie = currentMovie
            
//            let path = list?[indexList!].poster_path ?? ""
//            var imageUrl = URL(string: CONST.API_CONSTANTS.BASE_IMAGE_URL + path )
//            cell.imageTitle.kf.setImage(with: imageUrl)
//            cell.name.text = list?[indexList!].title ?? ""
//            cell.youTubeID = youTubeID
//            cell.currentMovie = list?[indexList!]
            return cell
        }
        
        else {
            
            if (actualIndex2 == 0){
                let cell = tableView.dequeueReusableCell(withIdentifier: idWatchCell, for: indexPath) as! DetailsWatchTableViewCell
                cell.currentMovieId = String(currentMovie!.id)
                cell.currentMovie = currentMovie
                cell.delegate = self
                return cell

            }
            else{
                let cell = tableView.dequeueReusableCell(withIdentifier: idDetailsCell, for: indexPath) as! DetailsBodyTableViewCell
                cell.titleLabel.text = listDetails?.original_title
                cell.genreLabel.text = listDetails?.genres?.first?.name
                cell.descriptionLabel.text = listDetails?.overview
                cell.averageVotesLabel.text = String(listDetails!.vote_average)
                cell.releaseDateLabel.text = listDetails?.release_date
                return cell
            }
        }
    }
    
    func getYoutubeId(id: String){
        
       // print("[DEBUG] - \(String(list![indexList!].id))")
        
        MovieService.getTrailerKey(id: id, completion: {result, error  in
            if result != nil{
                self.youTubeID  = result
                self.detailsTV.reloadData()
            } else{
            print("[DEBUG] no results")
            }
        })
        
    }
    
    func getDetailsList(id: Int){
        
        
        MovieService.getMovieDetails(id: String(id), completion: {results, error  in
            if results != nil{
                self.listDetails = results
                self.detailsTV.reloadData()
            } else{
            print("[DEBUG] no results")
            }
        })
        
    }
    
//    func fillPopulatedList() -> [Movie]?  {
//
//        var movie       : [Movie]?
//        print("[DEBUG] - \(tableIndex)")
//
//
//        if (upcomingMovieList != nil && tableIndex == 0){
//            movie = upcomingMovieList!
//            return movie
//        }else if (popularMovieList != nil && tableIndex == 1){
//            movie = popularMovieList!
//            return movie
//        }else if (playingMovieList != nil && tableIndex == 2){
//            movie = playingMovieList!
//            return movie
//        }else{
//            movie = myMovieList
//            return movie
//        }
//    }

    
    
    
    
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
        print(currentMovie)
        self.navigationController!.pushViewController(vc, animated:false)

        //detailsTV.reloadData()
        
    }

    

}


