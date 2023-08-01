//
//  PrimaryButton.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 31/07/23.
//

import SwiftUI

struct PrimaryButton: View {
    
    var title: String
    var iconName: String
    var action: () -> Void
    
    var body: some View {
        Button(action: action, label: {
            HStack{
                Image(systemName: iconName).foregroundStyle(.black)
                Text(title.uppercased()).padding(2).foregroundColor(.black)
            }.frame(maxWidth: .infinity, maxHeight: .infinity).padding()
        }).background(
            RoundedRectangle(cornerRadius: 5.0)
                .tint(.accent)
        )
    }
}
