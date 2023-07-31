//
//  CustomTabButton.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import SwiftUI

struct CustomTabButton: View {
    
    var title: String
    
    var selected: Bool = true
    
    var action: () -> Void
    
    var body: some View {
        Button(action: action, label: {
            if selected{
                VStack{
                    Text(title.uppercased()).font(.system(size: 18, weight: .bold)).tint(.white)
                    Rectangle().frame(height: 3).tint(.white)
                }
            }else{
                VStack{
                    Text(title.uppercased()).font(.system(size: 18)).tint(Color.gray)
                    Rectangle().frame(height: 3).tint(.clear)
                }
            }
        })
    }
}

#Preview {
    CustomTabButton(title: "Teste teste", action: {})
}
