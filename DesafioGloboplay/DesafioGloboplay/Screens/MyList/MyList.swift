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
        VStack(alignment: .leading){
            Heading(text: "Minha Lista")
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
        }.padding().sheet(isPresented: $didTapRecommendation){
            if let recommendation = $choosedRecomendation.wrappedValue{
                NavigationView{
                    Details(item: recommendation)
                }
            }
        }
    }
}

#Preview {
    MyList()
}
