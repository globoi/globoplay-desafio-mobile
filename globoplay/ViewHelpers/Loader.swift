//
//  LoaderView.swift
//  globoplay
//
//  Created by Marcos Curvello on 24/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI

struct Loader: View {
    var title: String? = nil
    var body: some View {
        ZStack {
            Color.black.edgesIgnoringSafeArea(.all)
            ActivityIndicator(isAnimating: .constant(true), style: .medium)
            Text(title == nil ? "" : title!)
                .foregroundColor(.white)
                .offset(y: 30)
        }
    }
}

struct ActivityIndicator: UIViewRepresentable {
    @Binding var isAnimating: Bool
    let style: UIActivityIndicatorView.Style

    func makeUIView(context: UIViewRepresentableContext<ActivityIndicator>) -> UIActivityIndicatorView {
        let indicator = UIActivityIndicatorView(style: style)
        indicator.color = .white
        return indicator
    }

    func updateUIView(_ uiView: UIActivityIndicatorView, context: UIViewRepresentableContext<ActivityIndicator>) {
        isAnimating ? uiView.startAnimating() : uiView.stopAnimating()
    }
}

struct Loader_Previews: PreviewProvider {
    static var previews: some View {
        Loader()
    }
}
