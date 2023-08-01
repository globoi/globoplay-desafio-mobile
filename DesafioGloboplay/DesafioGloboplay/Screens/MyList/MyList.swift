//
//  MyList.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 26/07/23.
//

import SwiftUI
import RealmSwift

struct MyList: View {
    
    @ObservedResults(MyListResult.self) var myList
    
    @State var didTapRecommendation: Bool = false
    
    @State var choosedRecomendation: Result?
    
    var body: some View {
        NavigationView{
            VStack(alignment: .leading){
                if myList.isEmpty{
                    EmptyListView()
                }else{
                    
                    ScrollView{
                        LazyVGrid(columns: defaultGridColumns){
                            ForEach(myList, id: \.id){result in
                                PosterCell(result: result, goToDetailsClosure: {item in
                                    choosedRecomendation = item
                                    $didTapRecommendation.wrappedValue = true
                                })
                            }
                        }
                    }
                }
            }.padding().sheet(isPresented: $didTapRecommendation){
                if let recommendation = $choosedRecomendation.wrappedValue{
                    NavigationView{
                        Details(item: recommendation)
                    }
                }
                
            }.toolbar(content: {
                ToolbarItem(placement: .navigation, content: {Heading(text: screenTexts.myList.myListTitle.rawValue)})
            })
        }
    }
}

#Preview {
    MyList()
}
