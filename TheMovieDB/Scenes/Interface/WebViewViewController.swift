//
//  WebViewViewController.swift
//  TheMovieDB
//
//  Created by Edwy Lugo on 26/02/20.
//  Copyright © 2020 Edwy Lugo. All rights reserved.
//

import UIKit
import WebKit


class WebViewViewController: UIViewController, WKNavigationDelegate {
    
    var webView: WKWebView!
    var urlString : String!
    
    init(urlString: String) {
        super.init(nibName: nil, bundle: nil)
        self.urlString = urlString
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func loadView() {
        self.webView = WKWebView()
        self.webView.navigationDelegate = self
        self.view = self.webView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
                
        if let url = URL(string: self.urlString) {
            self.webView.load(URLRequest(url: url))
            self.webView.allowsBackForwardNavigationGestures = true
        } else {
            Functions.showSimpleAlert(controller: self, title: "Ops!", message: "URL inacessível!")
        }
    }
    
    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
        self.title = webView.title
    }
}
