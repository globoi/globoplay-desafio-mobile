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
    
    var didTapRecommendation: (Result) -> Void = {_ in }
    
    var columns = [
        GridItem(.flexible()),
        GridItem(.flexible()),
        GridItem(.flexible())
    ]
    
    var body: some View {
        LazyVGrid(columns: columns){
            ForEach(myList, id: \.id){result in
                PosterCell(result: result, goToDetailsClosure: didTapRecommendation)
            }
        }
    }
}

#Preview {
    MyList()
}
