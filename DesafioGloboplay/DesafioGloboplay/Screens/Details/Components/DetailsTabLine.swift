//
//  DetailsTabLine.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 31/07/23.
//

import SwiftUI

struct DetailsTabLine: View {
    
    var title: String
    var value: String?
    
    var body: some View {
        (Text(title).font(.system(size: 18, weight: .bold))+Text("\(value ?? String())")).foregroundStyle(.secondary)
    }
}

#Preview {
    DetailsTabLine(title: "Teste: ", value: "Teste")
}
