//
//  EmptyView.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 30/07/23.
//

import SwiftUI

struct EmptyView: View {
    var body: some View {
        VStack(spacing: 24){
            Image(systemName: "list.star").font(.system(size: 100))
            Text(myListIsEmpty)
        }.padding()
    }
}

#Preview {
    EmptyView()
}
