//
//  ViewModel.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 27/07/23.
//

import Foundation
import SwiftUI

class ViewModel{
    
    @Published var isLoading: Bool = true
    @Published var showError: Bool = false
    @Published var isNetworkError: Bool = false
    @Published var errorString: String = ""
    var completion: (() -> Void)?  = {}
    
    
    internal func perform<T:Decodable>(_ type: T.Type, request: URLRequest, successCompletion: @escaping (T) -> Void){
    
        isLoading = true
        
        guard NetworkTester().isConnected() else {
            self.isLoading = false
            self.showError = true
            self.isNetworkError = true
            return
        }
        
        URLSession.shared.dataTask(with: request) { data, response, error in
            
            if (error != nil && ProcessInfo.processInfo.environment["XCTestConfigurationFilePath"] == nil) {
                print("Error")
                print(error as Any)
                if let errorDescription = error?.localizedDescription{
                    self.errorString = errorDescription
                }
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
                        self.isNetworkError = false
                        if let completion = self.completion {completion()}
                    }
                    print("Resposta: ")
                    dump(result)
                } else {
                    print("JSON Inválido")
                    DispatchQueue.main.async {
                        self.errorProcedure()
                        self.errorString = "Ocorreu um erro. Tente novamente."
                    }
                }
            }
            
        }.resume()
        
        
    }
    
    internal func errorProcedure(){
        self.showError = true
        self.isLoading = false
        self.isNetworkError = false
        if let completion = self.completion {completion()}
    }
}



