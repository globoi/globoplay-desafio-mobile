//
//  HeaderDetailsTableViewCell.swift
//  globoplay-desafio-mobile
//
//  Created by Mariela on 05/10/20.
//  Copyright © 2020 Mariela. All rights reserved.
//

import AVKit
import UIKit
import XCDYouTubeKit

class DetailsHeaderTableViewCell: UITableViewCell {
    var segmentedControlValue                   = 0
    var isMinhaLista                            : Bool?
    var youTubeID                               : String?
    var currentMovie                            : Movie?
    var favoriteListArray                       :[Movie]? = []
    
    @IBOutlet weak var name                     : UILabel!
    @IBOutlet weak var imageTitle               : UIImageView!
    @IBOutlet weak var segmentedControlDetails  : UISegmentedControl!
    @IBOutlet weak var trailerButton            : CustomButton!
    @IBOutlet weak var myListButton             : CustomButton!
    @IBOutlet weak var descriptionLabel         : UILabel!
    
    var naLista                                 : Bool?
    let defaults                                = UserDefaults.standard
    
    override func awakeFromNib() {
        super.awakeFromNib()
        let colorNormal = [NSAttributedString.Key.foregroundColor: UIColor.white]
        let colorSelected = [NSAttributedString.Key.foregroundColor: UIColor.black]
        segmentedControlDetails.setTitleTextAttributes(colorNormal, for: .normal)
        segmentedControlDetails.setTitleTextAttributes(colorSelected, for: .selected)
        
        getFromUserDefaults()
    }
    override func layoutSubviews() {
        setupButton()
        naLista = procuraNaLista(list: favoriteListArray, currentId: currentMovie!.id)
    }

    func setupButton(){
        if (naLista == true){
            myListButton.setTitle("Adicionado", for: .normal)
            myListButton.setImage(UIImage(named: "check_black"), for: .normal)
        }
        else if (naLista == false){
            myListButton.setTitle("Minha Lista", for: .normal)
            myListButton.setImage(UIImage(named: "star_rate"), for: .normal)
        }
       
        if (youTubeID == nil || youTubeID == ""){
            trailerButton.backgroundColor = .gray
            trailerButton.tintColor = .darkGray
            trailerButton.setTitleColor(.darkGray, for: .normal)
            trailerButton.isUserInteractionEnabled = false
            trailerButton.borderColor = .gray
        }else{
            trailerButton.backgroundColor = .white
            trailerButton.tintColor = .black
            trailerButton.setTitleColor(.black, for: .normal)
            trailerButton.isUserInteractionEnabled = true
            trailerButton.borderColor = .white
        }
    }

    @IBAction func segmentedControlChanged(_ sender: Any) {
        self.segmentedControlValue = segmentedControlDetails.selectedSegmentIndex
    }
    @IBAction func playTrailer(_ sender: Any) {
     
        let playerViewController: AVPlayerViewController = AVPlayerViewController()
        self.window?.rootViewController!.present(playerViewController, animated: true, completion: nil)
                
        XCDYouTubeClient.default().getVideoWithIdentifier(self.youTubeID) { (video, error) in
            guard let video: XCDYouTubeVideo = video else {
                playerViewController.dismiss(animated: true, completion: nil)
                return
            }
            playerViewController.player = AVPlayer(url: video.streamURL!)
            playerViewController.player?.play()
        }
    }
    
    @IBAction func addToMinhaLista(_ sender: Any) {
        var search = procuraNaLista(list: favoriteListArray, currentId: currentMovie!.id)
        if (search == false){
            
            if defaults.object(forKey: "favoriteListArray") != nil {
                
                addToList()
    
            } else {
                addToEmptyList()
            }
            myListButton.setTitle("Adicionado", for: .normal)
            myListButton.setImage(UIImage(named: "check_black"), for: .normal)
            isMinhaLista = true
            return
        }
        else if (search == true) {
            if defaults.object(forKey: "favoriteListArray") != nil {
                removeFromList()
            }
            myListButton.setTitle("Minha Lista", for: .normal)
            myListButton.setImage(UIImage(named: "star_rate"), for: .normal)
            isMinhaLista = false
            return
        }
    }
    
    //MARK: Aux Functions
    
    func getFromUserDefaults(){
        if defaults.object(forKey: "favoriteListArray") != nil {
            do {
                let data = defaults.object(forKey: "favoriteListArray") as? Data
                let decoder = PropertyListDecoder()
                favoriteListArray = try decoder.decode(Array<Movie>.self, from: data!)
            } catch {
                print(error)
            }
        }
    }
    
    func addToList(){
        favoriteListArray?.append(currentMovie!)
        defaults.set(try? PropertyListEncoder().encode(favoriteListArray), forKey: "favoriteListArray")
    }
    
    func addToEmptyList(){
        favoriteListArray?.append(currentMovie!)
        print(favoriteListArray)
        defaults.set(try? PropertyListEncoder().encode(favoriteListArray), forKey: "favoriteListArray")
        
        do {
            let playerData = defaults.object(forKey: "favoriteListArray") as? Data
            let decoder = PropertyListDecoder()
            let player = try decoder.decode(Array<Movie>.self, from: playerData!)
            print(player)
            
        } catch {
            print(error)
        }
    }
    
    func removeFromList(){
        var i = 1
        guard var listAux = favoriteListArray else {return }
        for movie in listAux {
            if movie.id == currentMovie?.id {
                listAux.remove(at: i - 1)
                favoriteListArray = listAux
                defaults.set(try? PropertyListEncoder().encode(favoriteListArray), forKey: "favoriteListArray")
            }
            i += 1
        }
    }
    
    func procuraNaLista(list: [Movie]?, currentId: Int) -> Bool {
        guard let listAux = list else {return false}
        for movie in listAux {
            if movie.id == currentId{
                return true
            }
        }
        return false
    }
}
