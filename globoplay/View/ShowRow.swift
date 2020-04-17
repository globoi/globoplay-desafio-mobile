//
//  ShowRow.swift
//  globoplay
//
//  Created by Marcos Curvello on 17/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI

struct ShowRow: View {
    var show: ShowResult

    var body: some View {
        HStack {
            Spacer()
            Text(show.name)
            Spacer()
        }
    }
}

struct ShowRow_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            ShowRow(show: sampleShows[0])
            ShowRow(show: sampleShows[1])
        }
        .previewLayout(.fixed(width: 300, height: 70))
    }
}
