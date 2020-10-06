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
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let nibName = UINib(nibName: "detailsHeaderTableViewCell", bundle: nil)
        let nibBodyName = UINib(nibName: "detailsBodyTableViewCell", bundle: nil)
        self.detailsTV.register(nibName, forHeaderFooterViewReuseIdentifier: "headerDetailsCell")
        self.detailsTV.register(nibBodyName, forCellReuseIdentifier: "bodyDetailsCell")
        
    }
    

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        
        let cell = tableView.dequeueReusableHeaderFooterView(withIdentifier: "headerDetailsCell") as! DetailsHeaderTableViewCell
        
        return cell
    }
    
    func tableView(tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 300
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCell(withIdentifier: "bodyDetailsCell", for: indexPath) as! DetailsBodyTableViewCell
        
        return cell
        
    }
    
    //Mark: Actions
    
    @IBAction func dismissView(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }
    

}
