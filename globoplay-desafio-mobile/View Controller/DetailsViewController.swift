//
//  DetailsViewController.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 05/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import UIKit

class DetailsViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var detailsTV: UITableView!
    
    
    let idDetailsCell = "bodyDetailsCell"
    let idWatchCell = "watchDetailsCell"
    let idHeaderCell = "headerDetailsCell"
    let idSegmentedCell = "segmentedCell"
    
    var upcomingMovieList       : [Movie]?
    var popularMovieList        : [Movie]?
    var playingMovieList        : [Movie]?
    var indexList               : Int?
    var tableIndex              : Int?
    var youTubeID               : String?
    
    private var listDetails         :MovieDetails?
    private var list                :[Movie]?
    
    var actualIndex2 :Int!
    var isFromHome :Bool!
    
    override func viewWillAppear(_ animated: Bool){
        super.viewWillAppear(animated)
       
    }
    

    override func viewDidLoad() {
        super.viewDidLoad()
        
        let nibName             = UINib(nibName: "detailsHeaderTableViewCell", bundle: nil)
        let nibBodyName         = UINib(nibName: "detailsBodyTableViewCell", bundle: nil)
        let nibCollectionWatch  = UINib(nibName: "detailsWatchTableViewCell", bundle: nil)
        
        self.detailsTV.register(nibBodyName, forCellReuseIdentifier: idDetailsCell)
        self.detailsTV.register(nibCollectionWatch, forCellReuseIdentifier: idWatchCell)
        self.detailsTV.register(nibName, forCellReuseIdentifier: idHeaderCell)
        
        
        self.list = fillPopulatedList()
        getYoutubeId(list: list)
        
        getDetailsList(id: list![indexList!].id)
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
            
            let path = list?[indexList!].poster_path ?? ""
            var imageUrl = URL(string: CONST.API_CONSTANTS.BASE_IMAGE_URL + path )
            cell.imageTitle.kf.setImage(with: imageUrl)
            cell.name.text = list?[indexList!].title ?? ""
            
           // cell.descriptionLabel.text = list?[indexList!].overview ?? ""
            
            
            cell.youTubeID = youTubeID
            return cell
        }
        
        else {
            
            if (actualIndex2 == 0){
                let cell = tableView.dequeueReusableCell(withIdentifier: idWatchCell, for: indexPath) as! DetailsWatchTableViewCell
                return cell

            }
            else{
                let cell = tableView.dequeueReusableCell(withIdentifier: idDetailsCell, for: indexPath) as! DetailsBodyTableViewCell
                cell.titleLabel.text = listDetails?.original_title
                cell.genreLabel.text = listDetails?.genres?.first?.name
                cell.descriptionLabel.text = listDetails?.overview
                cell.averageVotesLabel.text = String(listDetails!.vote_average)
                cell.releaseDateLabel.text = listDetails?.release_date
//                cell.titleLabel.text = listDetails?.status
//                cell.genreLabel.text = listDetails?.genres?.first?.name
//                cell.taglineLabel.text = list?[indexList!].overview ?? ""
                
                    return cell
               
            }
        }
    }
    
    func getYoutubeId(list: [Movie]?){
        
        print("[DEBUG] - \(String(list![indexList!].id))")
        
        MovieService.getTrailerKey(id: String(list![indexList!].id), completion: {result, error  in
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
    
    func fillPopulatedList() -> [Movie]?  {

        var movie       : [Movie]?
        print("[DEBUG] - \(tableIndex)")
        
        
        if (upcomingMovieList != nil && tableIndex == 0){
            movie = upcomingMovieList!
            return movie
        }else if (popularMovieList != nil && tableIndex == 1){
            movie = popularMovieList!
            return movie
        }else {
            movie = playingMovieList!
            return movie
        }
    }
    
//    func fillPopulatedListSerie() -> [Serie]?  {
//
//        var serie       : [Serie]?
//
//        if (popularSerieList != nil){
//            serie = popularSerieList!
//            return serie
//        }
//        return nil
//    }
//
    
    
    
    
    
    //Mark: Actions
    
    @objc func onSegChange(_ sender: UISegmentedControl) {
        self.actualIndex2 = sender.selectedSegmentIndex
        self.detailsTV.reloadData()
    }
    
    @IBAction func dismissView(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }
    

}


