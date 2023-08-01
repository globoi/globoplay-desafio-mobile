//
//  TrailerView.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 31/07/23.
//

import SwiftUI
import YouTubePlayerKit

struct TrailerView: View {
    
    var videoId: String
    
    var body: some View {
        YouTubePlayerView(YouTubePlayer(stringLiteral: "\(youtubeURL)\(videoId)")){state in
            switch state {
            case .idle:
                ProgressView().scaleEffect(2.5)
            case .error:
                VStack{
                    Image(systemName: assets.icons.error.generic.rawValue)
                    Text(screenTexts.trailerView.trailerError.rawValue)
                }
            case .ready:
                EmptyView()
            }
        }
    }
}

#Preview {
    TrailerView(videoId: "psL_5RIBqnY")
}
