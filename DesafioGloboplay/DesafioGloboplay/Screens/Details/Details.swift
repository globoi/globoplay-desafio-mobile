//
//  Details.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import SwiftUI

struct Details: View {
    
    //TODO: Fazer pra série de TV
    
    //TODO: para aba veja também, usar essa request: https://api.themoviedb.org/3/discover/movie?with_genres=%2212%2C10751%2C14%22
    //Mais detalhes nessa documentação: https://developer.themoviedb.org/reference/discover-movie
    //Separar como query parameters de acordo com as regras da API
    
    //TODO: Quebrar essa view em mais componentes
    
    @State var isDetailsSelected: Bool = false
    
    @StateObject var detailsTabViewModel = DetailsTabViewModel()
    
    var item: Result
    
    func getURL() -> URL?{
        return URL.init(string: "\(posterURL)\(item.posterPath ?? "")")
    }
    
    var body: some View {
        
        ZStack{
            
            ScrollView{
                
                VStack{
                    AsyncImage(url: getURL()){image in
                        image.resizable().aspectRatio(contentMode: .fit).frame(width: 150, height: 200)
                    } placeholder:{
                        ProgressView().scaleEffect(2.5).progressViewStyle(.circular).tint(.accentColor).padding(40).background(.regularMaterial, in: RoundedRectangle(cornerRadius: 8, style: .continuous))
                    }
                    
                    
                    Text(item.getTitle() ?? "").font(.system(size: 28, weight: .bold))
                    Text("\(item.getMediaType().rawValue)")
                    
                    VStack(alignment: .leading, spacing: 16, content: {
                        Text(item.overview ?? "").font(.system(size: 14, weight: .bold)).padding(16)
                        
                        VStack(alignment: .leading){
                            HStack(spacing: 24){
                                
                                CustomTabButton(title: "Assista Também", selected: !isDetailsSelected) {
                                    $isDetailsSelected.wrappedValue = false
                                }
                                
                                CustomTabButton(title: "Detalhes", selected: isDetailsSelected) {
                                    $isDetailsSelected.wrappedValue = true
                                }
                                
                            }.fixedSize(horizontal: true, vertical: true).padding(16)
                            
                            if isDetailsSelected{
                                DetailsTabView(viewModel: detailsTabViewModel, item: item).padding(16)
                            }else{
                                SeeMoreTabView().padding(16)
                            }
                        }.background(.black.opacity(0.4))
                        
                    })
                    Spacer()
                }
                
            }.background(
                AsyncImage(url: getURL()){image in
                    image.image?.resizable()
                        .aspectRatio(contentMode: .fill)
                        .edgesIgnoringSafeArea(.top)
                        .blur(radius: 10.0)
                        .overlay(LinearGradient(gradient: Gradient(colors: [Color.clear, Color.black]), startPoint: .top, endPoint: .center))
                }
                
            )
            
        }.onAppear(perform: {
            self.requestDetailData()
        })
        
        
    }
    
    func requestDetailData(){
        guard let id = item.id else {return}
        
        if item.getMediaType() == .movie{
            detailsTabViewModel.getMovieDetails(id)
            detailsTabViewModel.getMovieCredits(id)
        }else{
            detailsTabViewModel.getTVShowDetails(id)
            detailsTabViewModel.getTVShowCredits(id)
        }
    }
    
}

#Preview {
    Details(item: movieMock)
}
