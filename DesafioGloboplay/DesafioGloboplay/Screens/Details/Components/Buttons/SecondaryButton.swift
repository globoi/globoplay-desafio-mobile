//
//  SecondaryButton.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 30/07/23.
//

import SwiftUI

struct SecondaryButton: View {
    
    var title: String
    var iconName: String
    var action: () -> Void
    
    var body: some View {
        Button(action: action, label: {
            HStack{
                Image(systemName: iconName)
                Text(title.uppercased()).padding(2)
            }.frame(maxWidth: .infinity).padding()
        }).background(
            RoundedRectangle(cornerRadius: 5.0)
                .stroke(lineWidth: 2).tint(.accent)
        )
    }
}
