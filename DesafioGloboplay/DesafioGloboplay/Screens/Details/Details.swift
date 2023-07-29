//
//  Details.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import SwiftUI
import RealmSwift
import HidableTabView

struct Details: View {
    
    //TODO: Quebrar essa view em mais componentes
    
    @State var isDetailsSelected: Bool = false
    
    //Possível solução pro excesso de chamadas:
    // - Passar o item pra cada viewModel
    // - Fazer chamadas que hoje estão no onAppear no init do VM
    
    @StateObject var detailsTabViewModel = DetailsTabViewModel()
    @StateObject var recommendationsTabViewModel = RecommendationsTabViewModel()
    
    @State var didTapRecommendation: Bool = false
    @State var choosedRecomendation: Result = movieMock
    
    @ObservedResults(MyListResult.self) var myList
    
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
                        
                        
                        HStack{
                            Button(action: {$myList.append(item.asMyListResult())}, label: {
                                Text("Minha lista")
                            })
                        }
                        
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
                                RecommendationsTabView(item: item, viewModel: recommendationsTabViewModel, didTapRecommendation: self.goToRecommendation(_:)).padding(16)
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
            self.requestRecommendationData()
            UITabBar.hideTabBar()
        }).onDisappear(perform: {
            UITabBar.showTabBar()
        }).sheet(isPresented: $didTapRecommendation, onDismiss: {
            UITabBar.hideTabBar()
        }, content: {
            Details(item: choosedRecomendation)
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
    
    func requestRecommendationData(){
        guard let id = item.id else {return}
        
        if item.getMediaType() == .movie{
            recommendationsTabViewModel.getSuggestedMovies(id)
        }else{
            recommendationsTabViewModel.getSuggestedTVShows(id)
        }
    }
    
    func goToRecommendation(_ item: Result){
        self.choosedRecomendation = item
        self.$didTapRecommendation.wrappedValue = true
    }
    
}

#Preview {
    Details(item: movieMock)
}
