//
//  DetalhesViewController.swift
//  Movs
//
//  Created by gmmoraes on 22/12/19.
//  Copyright © 2019 gmmoraes. All rights reserved.
//

import Foundation
import UIKit

class DetalhesViewController: UIViewController {
    
    
    @IBOutlet weak var tableView: UITableView!

    
    var titleText:  String?
    var image : UIImage?
    var overview:  String?
    var section: Int?
    
    var favoritedMedia: FavoritedMedia?
    
    let sectionHeaderFont = UIFont.loadFont(name: "NavBarFont")
    let textViewFont = UIFont.loadFont(name: "TextViewFont")
    let fontMetrics = UIFontMetrics(forTextStyle: .body)
    
    var safeAreHeight:CGFloat = 0.0
    var tableViewHeight:CGFloat = 0.0
    var tableViewWidth:CGFloat = 0.0
    var topSafeArea: CGFloat = 0.0
    var bottomSafeArea: CGFloat = 0.0
    
    var detalhesVcInfo: MediaDetails?
    
    deinit {
        NotificationCenter.default.removeObserver(self)
    }
    
    
    
    override func viewDidLoad() {

        tableView.delegate = self
        tableView.dataSource = self
        tableView.tableFooterView = UIView(frame: CGRect.zero)
        
        
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()

        if #available(iOS 11.0, *) {
            topSafeArea = view.safeAreaInsets.top
            bottomSafeArea = view.safeAreaInsets.bottom
        } else {
            topSafeArea = topLayoutGuide.length
            bottomSafeArea = bottomLayoutGuide.length
        }
        

        safeAreHeight = self.view.frame.height - topSafeArea - bottomSafeArea -  (self.navigationController?.navigationBar.frame.height ?? 0)
        tableViewHeight = safeAreHeight
    }
    
//    func configureUI(titleText: String?, image: UIImage?, section: Int?, overview:String?){
    func configureUI(image: UIImage?, favoritedMedia: FavoritedMedia?){
        if let movies = favoritedMedia?.movies {
            self.titleText = movies.title
            self.overview = movies.overview
            self.section = 0
        } else if let series = favoritedMedia?.series {
            self.titleText = series.name
            self.overview = series.overview
            self.section = 1
        }
        self.image = image
        self.favoritedMedia = favoritedMedia

    }
    
    @objc func popToRoot(){
        if self.presentingViewController != nil {
            self.dismiss(animated: false, completion: {
               self.navigationController!.popToRootViewController(animated: true)
            })
        }
        else {
            self.navigationController!.popToRootViewController(animated: true)
        }
    }
    
}

extension DetalhesViewController: UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if indexPath.section == 0 {
            return 376
        } else if indexPath.section == 2 {
            return 50
        }
        
        return 460
    }
    
    
}

extension DetalhesViewController: UITableViewDataSource {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 4
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        
        if indexPath.section == 0, let titleText = titleText, let image = image, let section = section {
            let firstHalfTableVIewCell = tableView.dequeueReusableCell(withIdentifier: "FirstHalfTableVIewCell", for: indexPath) as! FirstHalfTableVIewCell
            
            firstHalfTableVIewCell.titleImageView.image = image
            firstHalfTableVIewCell.backImageView.image = image
            firstHalfTableVIewCell.titleLabel.text = titleText
            firstHalfTableVIewCell.titleLabel.textColor = .white
            firstHalfTableVIewCell.titleLabel.font = fontMetrics.scaledFont(for: sectionHeaderFont)
            firstHalfTableVIewCell.tipoLabel.text = section == 0 ? "Filme" : "Série de TV"
            firstHalfTableVIewCell.tipoLabel.textColor = .white
            self.view.setNeedsLayout()
            self.view.layoutIfNeeded()
            
            return firstHalfTableVIewCell
        }else if indexPath.section == 1 {
            let detalhesTableViewCell = tableView.dequeueReusableCell(withIdentifier: "DetalhesTableViewCell", for: indexPath) as! DetalhesTableViewCell
            detalhesTableViewCell.assistaBtn.icon = UIImage(named: "baseline_play_arrow_black_24")!
            detalhesTableViewCell.assistaBtn.title = "Assista"
            detalhesTableViewCell.assistaBtn.selectedTitle = "Assista"
            detalhesTableViewCell.assistaBtn.tag = 0
            detalhesTableViewCell.assistaBtn.frame = CGRect(x: 0, y: 10, width: detalhesTableViewCell.frame.width, height: detalhesTableViewCell.frame.height - 10)
            detalhesTableViewCell.assistaBtn.configureUI()
            
            detalhesTableViewCell.minhaListaBtn.icon = UIImage(named: "baseline_star_rate_black_24")!
            detalhesTableViewCell.minhaListaBtn.title = "Minha lista"
            detalhesTableViewCell.minhaListaBtn.selectedTitle = "Adicionado"
            detalhesTableViewCell.minhaListaBtn.tag = 1
            detalhesTableViewCell.minhaListaBtn.frame = CGRect(x: 0, y: 10, width: detalhesTableViewCell.frame.width, height: detalhesTableViewCell.frame.height - 10)
            detalhesTableViewCell.minhaListaBtn.favoritedMedia = self.favoritedMedia
            detalhesTableViewCell.minhaListaBtn.configureUI()
            
            if let overview = overview {
                detalhesTableViewCell.overviewLabel.text = overview
                detalhesTableViewCell.overviewLabel.textColor = .white
                
            }
            detalhesTableViewCell.selectionStyle = .none
            
            return detalhesTableViewCell
            
        } else if indexPath.section == 2 {
            let tabTableViewCell = tableView.dequeueReusableCell(withIdentifier: "TabTableViewCell", for: indexPath) as! TabTableViewCell
            
            tabTableViewCell.assistaTabBtn.setTitle("Assista também", for: .normal)
            tabTableViewCell.detalhesTabBtn.setTitle("Detalhes", for: .normal)
            tabTableViewCell.selectionStyle = .none
            
            return tabTableViewCell
        } else  {
            let textViewTableViewCell = tableView.dequeueReusableCell(withIdentifier: "TextViewTableViewCell", for: indexPath) as! TextViewTableViewCell
            textViewTableViewCell.selectionStyle = .none
            textViewTableViewCell.textView.font = fontMetrics.scaledFont(for: textViewFont)
            let fichaTecnica = "Ficha técnica" + "\n"

            if let movies = self.favoritedMedia?.movies {
                let moviesName = "Título: " + movies.title + "\n"
                let popularidade = "Popularidade: " + String(movies.popularity)  + "\n"
                let votos = "Votos: " + String(movies.vote_count) + "\n"
                let dataDeLancamento = "Data de Lançamento: " + movies.release_date
                let infoText = moviesName + popularidade + votos + dataDeLancamento
                textViewTableViewCell.textView.text = fichaTecnica + infoText
                textViewTableViewCell.currentSection = 0
                
                let attributedString = NSMutableAttributedString.init(string: textViewTableViewCell.textView.text)
                let range = (infoText as NSString).range(of: infoText)
                attributedString.addAttribute(NSAttributedString.Key.foregroundColor, value: UIColor.white, range: range)
                textViewTableViewCell.textView.attributedText = attributedString
                
            }else if let series = self.favoritedMedia?.series {
                let seriesName = "Título: " + series.name + "\n"
                let popularidade = "Popularidade: " + String(series.popularity)  + "\n"
                let votos = "Votos: " + String(series.vote_count) + "\n"
                let dataDeLancamento = "Data de Lançamento: " + series.first_air_date
                let infoText = seriesName + popularidade + votos + dataDeLancamento
                textViewTableViewCell.textView.text = fichaTecnica + infoText
                textViewTableViewCell.currentSection = 1

            }
            textViewTableViewCell.textView.textColor = .gray
            
            
            return textViewTableViewCell
        }
        
        
        
    }
    
}

