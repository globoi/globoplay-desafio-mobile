//
//  EmptyView.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 30/07/23.
//

import SwiftUI

struct EmptyListView: View {
    var body: some View {
        VStack(spacing: 24){
            Image(systemName: assets.icons.myList.emptyListIcon.rawValue).font(.system(size: 100))
            Text(screenTexts.myList.myListIsEmpty.rawValue)
        }.padding()
    }
}

#Preview {
    EmptyListView()
}
