//
//  ViewModel.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 27/07/23.
//

import Foundation
import SwiftUI

class ViewModel{
    
    @Published var isLoading: Bool = false
    @Published var showError: Bool = false
    var completion: (() -> Void)?  = {}
    
    
    internal func perform<T:Decodable>(_ type: T.Type, request: URLRequest, successCompletion: @escaping (T) -> Void){
    
        isLoading = true
        
        guard NetworkTester().isConnected() else {
            self.isLoading = false
            self.showError = true
            return
        }
        
        URLSession.shared.dataTask(with: request) { data, response, error in
            
            if (error != nil) {
                print("Error")
                print(error as Any)
            } else {
                let httpResponse = response as? HTTPURLResponse
                print("Response:")
                print(httpResponse)
            }
            
            if let dataUnwrapped = data {
                
                if let result = try? JSONDecoder().decode(type.self, from: dataUnwrapped) {
                    DispatchQueue.main.async {
                        self.showError = false
                        successCompletion(result)
                        self.isLoading = false
                        if let completion = self.completion {completion()}
                    }
                    print("Resposta: ")
                    dump(result)
                } else {
                    print("JSON Inválido")
                    DispatchQueue.main.async {
                        self.errorProcedure()
                    }
                }
            }
            
        }.resume()
        
        
    }
    
    internal func errorProcedure(){
        self.showError = true
        self.isLoading = false
        if let completion = self.completion {completion()}
    }
}



