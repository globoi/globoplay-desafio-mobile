//
//  MyListFavoriteViewController.swift
//  TheMovieDB
//
//  Created by Edwy Lugo on 03/02/20.
//  Copyright © 2020 Edwy Lugo. All rights reserved.
//

import UIKit

class MyListFavoriteViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        let imageView = UIImageView(frame: CGRect(x: 0, y: 0, width: 28, height: 28))
        imageView.contentMode = .scaleAspectFit
        let image = UIImage(named: "globoplay-logo")
        imageView.image = image
        self.navigationItem.titleView = imageView
        // Do any additional setup after loading the view.
    }


    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
