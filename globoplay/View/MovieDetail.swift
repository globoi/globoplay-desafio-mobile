//
//  MovieDetail.swift
//  globoplay
//
//  Created by Marcos Curvello on 18/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import SwiftUI

struct MovieDetail: View {
    @Environment(\.imageCache) var cache: ImageCache
    @ObservedObject var resource: Resource<Movie>
    
    init(id: Int) {
        print(api.movie(details: id))
        self.resource = Resource<Movie>(endpoint: api.movie(details: id))
        
        UITableView.appearance().backgroundColor = .clear
        UITableViewCell.appearance().backgroundColor = .clear
    }
    
    var body: some View {
        Group {
            if resource.value == nil {
                Text("Loading...")
            } else {
                List {
                    VStack(spacing: 5) {
                        HeaderView(path: resource.value?.posterPath ?? "")
                            .frame(height: 242)
                        
                        Text(resource.value?.title ?? "")
                            .font(Font.system(size: 20))
                            .fontWeight(.bold)
                        
                        Text(resource.value?.overview ?? "")
                            .font(Font.system(size: 14))
                            .font(.subheadline)
                            .multilineTextAlignment(.leading)
                            .lineLimit(4)
                        
                        Spacer()
                            .frame(height: 5)
                        
                        HStack {
                            Button(action: {}) {
                                HStack {
                                    Image(systemName: "play.fill")
                                    Text("Assista")
                                }
                                .frame(minWidth: 0, maxWidth: .infinity)
                                .padding()
                                    
                                .background(Color.white)
                                .foregroundColor(.black)
                                .cornerRadius(5)
                            }
                            
                            Button(action: {}) {
                                HStack {
                                    Image(systemName: "star.fill")
                                    Text("Minha Lista")
                                }
                                .frame(minWidth: 0, maxWidth: .infinity)
                                .padding()
                                .foregroundColor(.white)
                                .overlay(
                                    RoundedRectangle(cornerRadius: 5)
                                        .stroke(Color.white, lineWidth: 1)
                                )
                            }
                        }
                    }
                    .foregroundColor(.white)
                }
                .background(Color.black)
                .edgesIgnoringSafeArea(.all)
                .navigationBarHidden(true)
            }
        }
    }
}


struct MovieDetail_Previews: PreviewProvider {
    
    static var previews: some View {
        MovieDetail(id: 76341)
            .environmentObject(Store())
    }
}
