//
//  LazyView.swift
//  globoplay
//
//  Created by Marcos Curvello on 20/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI

struct LazyView<V: View>: View {
    let build: () -> V
    init(_ build: @escaping @autoclosure () -> V) {
        self.build = build
    }
    var body: V {
        build()
    }
}
