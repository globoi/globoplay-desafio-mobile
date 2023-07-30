//
//  Heading.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 30/07/23.
//

import SwiftUI

struct Heading: View {
    
    var text: String
    
    var body: some View {
        Text(text).font(.system(size: 24, weight: .bold))
    }
}
