//
//  HomeViewModel.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import Foundation
import SwiftUI

class HomeViewModel: ObservableObject{
    
    @Published var movieList: APIResults?
    @Published var tvShowsList: APIResults?
    
    @Published var showError: Bool = false
    
    @Published var isLoading: Bool = false
    
    var completion: (() -> Void)?
    
    
    func getMovieList(){
        
        isLoading = true
        
        guard NetworkTester().isConnected() else {
            self.isLoading = false
            self.showError = true
            return
        }
        
        do {
            try URLSession.shared.dataTask(with: APIURLs.getMovies.request()) { data, response, error in
                
                
                if (error != nil) {
                    print("Error")
                    print(error as Any)
                  } else {
                    let httpResponse = response as? HTTPURLResponse
                      print("Response:")
                    print(httpResponse)
                  }
                
                if let dataUnwrapped = data {
                    
                    if let movies = try? JSONDecoder().decode(APIResults.self, from: dataUnwrapped) {
                        DispatchQueue.main.async {
                            self.showError = false
                            self.movieList = movies
                            self.isLoading = false
                            if let completion = self.completion {completion()}
                        }
                        print("Filmes: ")
                        dump(movies)
                    } else {
                        print("JSON Inválido")
                        DispatchQueue.main.async {
                            self.showError = true
                            self.isLoading = false
                            if let completion = self.completion {completion()}
                        }
                    }
                }
                
            }.resume()
        } catch {
            self.showError = true
            self.isLoading = false
            if let completion = self.completion {completion()}
        }
        
    }
    
    
    func getTVShowsList(){
        
        
        
    }
    
}
