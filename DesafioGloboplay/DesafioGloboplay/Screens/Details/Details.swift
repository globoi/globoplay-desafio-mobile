//
//  Details.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import SwiftUI

struct Details: View {
    
    //TODO: Transformar imagem e gradiente em background
    
    var item: Result
    
    var body: some View {
        NavigationView{
            ZStack{
                
                if let url = URL.init(string: "\(posterURL)\(item.posterPath ?? "")"){
                    AsyncImage(url: url){image in
                        image.image?.resizable().aspectRatio(contentMode: .fill).blur(radius: 10.0).edgesIgnoringSafeArea(.top)
                    }
                    
                    LinearGradient(gradient: Gradient(colors: [Color.clear, Color.black]), startPoint: .top, endPoint: .center)
                    
                    VStack{
                        AsyncImage(url: url){image in
                            image.image?.resizable().aspectRatio(contentMode: .fit).frame(width: 150, height: 200)
                        }
                        
                        Text(item.title ?? "").font(.system(size: 28, weight: .bold))
                        Text("Gênero")
                        Text(item.overview ?? "").font(.system(size: 14, weight: .bold))
                    }.padding(20)
                }
                
                
            }
        }
    }
}

#Preview {
    Details(item: itemMock)
}
