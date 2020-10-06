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
    
    var actualIndex2 :Int!
    

    override func viewDidLoad() {
        super.viewDidLoad()
        
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
            print("actualIndex X- \(actualIndex2)")
            
            cell.segmentedControlDetails.addTarget(self, action: #selector(self.onSegChange(_:)), for: .valueChanged)


            print("Segmented control - \(cell.segmentedControlDetails.selectedSegmentIndex)\n\n")

            return cell
        }
        
        else {
            
            if (actualIndex2 == 0){
                print("actualIndex - \(actualIndex2)")
                let cell = tableView.dequeueReusableCell(withIdentifier: idWatchCell, for: indexPath) as! DetailsWatchTableViewCell
                return cell

            }
            else{
                print("actualIndex - \(actualIndex2)")

                let cell = tableView.dequeueReusableCell(withIdentifier: idDetailsCell, for: indexPath) as! DetailsBodyTableViewCell
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
    

}


