//
//  HomeViewController.swift
//  Movs
//
//  Created by gmmoraes on 20/12/19.
//  Copyright © 2019 gmmoraes. All rights reserved.
//

import UIKit

class HomeViewController: UIViewController, CellDelegate {
    
    //IBOutlets
    @IBOutlet weak var tableView: UITableView!
    
    var nextPage: Int = 1
    var isLoading = false
    var popularMovies: [Movies] = []
    var popularSeries: [Series] = []
    let tableViewLoadingCellNib = UINib(nibName: "LoadingCell", bundle: nil)
    
    let sectionNames = ["Filmes","Séries de TV"]
    
    var safeAreHeight:CGFloat = 0.0
    var sectionHeight:CGFloat = 0.0
    var tableViewHeight:CGFloat = 0.0
    var tableViewWidth:CGFloat = 0.0
    var topSafeArea: CGFloat = 0.0
    var bottomSafeArea: CGFloat = 0.0
    
    let sectionHeaderFont = UIFont.loadFont(name: "SectionHeaderFont")
    let fontMetrics = UIFontMetrics(forTextStyle: .body)
    
    var detalhesVcInfo: MediaDetails?
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tableView.delegate = self
        tableView.dataSource = self
        tableView.tableFooterView = UIView(frame: CGRect.zero)
    }
    
    deinit {
        NotificationCenter.default.removeObserver(self)
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
        
        self.tableView.backgroundColor = .backgroundColor
        self.navigationController?.navigationBar.topItem?.title = "TheMoviesDB"
        

        safeAreHeight = self.view.frame.height - topSafeArea - bottomSafeArea -  (self.navigationController?.navigationBar.frame.height ?? 0)
        sectionHeight = safeAreHeight * 0.10
        tableViewHeight = safeAreHeight * 0.40
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
    }

    
    func colCategorySelected(_ movies: Movies?, series: Series?, image: UIImage) {
        let currentId = movies != nil ? movies?.id : series?.id
        let currentFileName = String(currentId!) + ".json"
        if Storage.fileExists(currentFileName, in: .documents) {
            if let favoritedFromDisk = Storage.retrieve(currentFileName, from: .documents, as: FavoritedMedia.self) {
                detalhesVcInfo = MediaDetails(image: image, favoritedMedia: favoritedFromDisk)
            }
        } else {
            let favoritedFromDisk = FavoritedMedia(movies: movies, series: series, isFavorited: false)
            detalhesVcInfo = MediaDetails(image: image, favoritedMedia: favoritedFromDisk)
        }
        self.performSegue(withIdentifier: "toDetalhesViewController", sender: self)
      }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {

        if(segue.identifier == "toDetalhesViewController") {
            let nextVC = segue.destination as! DetalhesViewController
            nextVC.configureUI(image: detalhesVcInfo?.image, favoritedMedia: detalhesVcInfo?.favoritedMedia)
        }
    }
}

extension HomeViewController: UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return tableViewHeight
    }
    
    //Mark: Header
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return sectionHeight
    }
    
    func tableView(_ tableView: UITableView, willDisplayHeaderView view: UIView, forSection section: Int) {
        if let headerView = view as? UITableViewHeaderFooterView {
            headerView.contentView.backgroundColor = .backgroundColor
            headerView.textLabel?.textColor = .white
            headerView.textLabel?.text = sectionNames[section]
            headerView.textLabel?.font = fontMetrics.scaledFont(for: sectionHeaderFont)
        }
    }
    
}

extension HomeViewController: UITableViewDataSource {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return sectionNames.count
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "tableviewitemcellid", for: indexPath)
        if let tableViewItemCellid = cell as? TableViewItemCellid {
            tableViewItemCellid.tableViewHeight = tableViewHeight
            tableViewItemCellid.tableViewWidth = tableView.frame.width
            if indexPath.section == 0 {
                tableViewItemCellid.popularMovies = popularMovies
                tableViewItemCellid.currentSection = 0
            }else if indexPath.section == 1 {
                tableViewItemCellid.popularSeries = popularSeries
                tableViewItemCellid.currentSection = 1
            }
            tableViewItemCellid.delegate = self
        }
        return cell

    }
    
    //Mark: Header
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return "Section \(section)"
    }
    
}

        
        

