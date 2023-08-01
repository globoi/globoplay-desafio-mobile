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
    
    @State var isDetailsSelected: Bool = false
    
    @StateObject var detailsTabViewModel = DetailsTabViewModel()
    @StateObject var recommendationsTabViewModel = RecommendationsTabViewModel()
    @StateObject var trailerViewModel = TrailerViewModel()
    
    @State var didTapRecommendation: Bool = false
    @State var showTrailerView: Bool = false
    
    @ObservedResults(MyListResult.self) var myList
    
    let manager = RealmManager()
    var item: Result
    
    func getURL() -> URL?{
        return URL.init(string: "\(posterURL)\(item.posterPath ?? "")")
    }
    
    var body: some View {
        
        ZStack{
            
            ScrollView{
                
                VStack(spacing: 16){
                    AsyncImage(url: getURL()){image in
                        image.resizable().aspectRatio(contentMode: .fit).frame(width: 150, height: 200)
                    } placeholder:{
                        ProgressView().scaleEffect(2.5).progressViewStyle(.circular).tint(.accentColor).padding(40).background(.regularMaterial, in: RoundedRectangle(cornerRadius: 8, style: .continuous))
                    }
                    
                    Text(item.getTitle() ?? "").font(.system(size: 28, weight: .bold))
                    Text("\(item.getMediaType().rawValue)")
                    
                    VStack(alignment: .leading, spacing: 16, content: {
                        Text(item.overview ?? "").font(.system(size: 14, weight: .bold))
                        
                        
                        HStack(spacing: 16){
                            
                            PrimaryButton(title: screenTexts.details.watchTrailerButton.rawValue, iconName: assets.icons.details.watchTrailer.rawValue, action: {
                                withAnimation {
                                    $showTrailerView.wrappedValue.toggle()
                                }
                            })
                            
                            SecondaryButton(title: getButtonText(), iconName: getButtonIcon(), action: {
                                manager.addItem(item.asMyListResult(), onList: $myList)
                            })
                        }
                        
                        if $showTrailerView.wrappedValue, let key = trailerViewModel.trailer?.key{
                            TrailerView(videoId: key).frame(minWidth: 300, minHeight: 200)
                        }
                        
                        TabStrip(titles: [screenTexts.details.recommendationsTabTitle.rawValue, screenTexts.details.detailsTabTitle.rawValue], associatedViews: [
                            AnyView(RecommendationsTabView(item: item, viewModel: recommendationsTabViewModel, goToRecommendation: $didTapRecommendation)),
                            AnyView(DetailsTabView(viewModel: detailsTabViewModel, item: item))
                        ])
                        
                    })
                    Spacer()
                }.padding(16)
                
            }.background(
                BackgroundView(imageURL: getURL())
                
            )
            
        }.onAppear(perform: {
            self.requestDetailData()
            self.requestRecommendationData()
            self.trailerViewModel.loadTrailer(id: item.id ?? 0)
            UITabBar.hideTabBar()
        }).onDisappear(perform: {
            UITabBar.showTabBar()
        }).sheet(isPresented: $didTapRecommendation, onDismiss: {
            UITabBar.hideTabBar()
        }, content: {
            Details(item: self.recommendationsTabViewModel.choosedRecommendation)
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
    
    func getButtonText() -> String{
        return manager.contains(id: item.id) ?
        screenTexts.details.itemAddedToList.rawValue :
        screenTexts.details.myListButtonInitialState.rawValue
    }
    
    func getButtonIcon() -> String{
        return manager.contains(id: item.id) ?
        assets.icons.details.myListItemAlreadyAdded.rawValue :
        assets.icons.details.myListInitial.rawValue
    }
    
}

#Preview {
    Details(item: movieMock)
}
