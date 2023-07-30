//
//  ErrorView.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 30/07/23.
//

import SwiftUI

struct ErrorView: View {
    
    var viewModel: ViewModel
    
    var body: some View {
        VStack(spacing: 24){
            Image(systemName: getIcon()).font(.system(size: 100))
            Text(getText())
        }.padding(16)
    }
    
    private func getIcon() -> String{
        if viewModel.isNetworkError{
            return "wifi"
        }
        return "multiply.square"
    }
    
    private func getText() -> String{
        if viewModel.isNetworkError{
            return networkErrorText
        }
        return viewModel.errorString
    }
}
