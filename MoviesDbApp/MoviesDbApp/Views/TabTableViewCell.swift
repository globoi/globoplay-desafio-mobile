//
//  TabTableViewCell.swift
//  Movs
//
//  Created by gmmoraes on 26/12/19.
//  Copyright © 2019 gmmoraes. All rights reserved.
//

import Foundation
import UIKit

class TabTableViewCell : UITableViewCell {
    
 
    @IBOutlet weak var assistaTabBtn: TabButton!
    @IBOutlet weak var detalhesTabBtn: TabButton!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        configureButton(btn: assistaTabBtn)
        configureButton(btn: detalhesTabBtn)
        
        self.backgroundColor = .backgroundColor
    }
    
    
    func configureButton(btn: TabButton){
        btn.titleLabel?.font = UIFont(name: "OpenSans-ExtraBold", size: 10)
        btn.titleLabel?.textColor = .white
        btn.tintColor = .white
        btn.isUserInteractionEnabled = true
        btn.lineView = UIView(frame: CGRect(x: 0, y: (btn.frame.height * 0.90), width: btn.frame.size.width, height: 2))
        btn.lineView.backgroundColor = UIColor.clear
        btn.backgroundColor = .clear
    }

    @IBAction func changeAssistaTabBtnState(_ sender: Any) {
        
        
        assistaTabBtn.lineView.backgroundColor = assistaTabBtn.isSelected ? .clear : .white
        assistaTabBtn.isSelected = !assistaTabBtn.isSelected
        
        if detalhesTabBtn.isSelected {
            detalhesTabBtn.isSelected = false
            detalhesTabBtn.lineView.backgroundColor = .clear
        }
        
        let isTextViewVisible = [ "isTextViewVisible" : false]
        NotificationCenter.default.post(name: Notification.Name("isTextViewVisible"), object: nil, userInfo: isTextViewVisible)
        
    }
    
    
    @IBAction func changeDetalheTabBtnState(_ sender: Any) {
        
        if assistaTabBtn.isSelected {
            assistaTabBtn.isSelected = false
            assistaTabBtn.lineView.backgroundColor = .clear
        }
        detalhesTabBtn.lineView.backgroundColor = detalhesTabBtn.isSelected ? .clear : .white
        detalhesTabBtn.isSelected = !detalhesTabBtn.isSelected
        
        let isTextViewVisible = [ "isTextViewVisible" : true]
        NotificationCenter.default.post(name: Notification.Name("isTextViewVisible"), object: nil, userInfo: isTextViewVisible)
    }
    
}
