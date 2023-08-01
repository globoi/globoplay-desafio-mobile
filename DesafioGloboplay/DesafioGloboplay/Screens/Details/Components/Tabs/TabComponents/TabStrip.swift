//
//  TabStrip.swift
//  DesafioGloboplay
//
//  Created by Thalles Araújo on 31/07/23.
//

import SwiftUI

struct TabStrip: View {
    
    var titles: [String]
    var associatedViews: [AnyView]
    
    @State var selectedIndex: Int = 0
    
    var body: some View {
        VStack(alignment: .leading){
            HStack(spacing: 24){
                
                ForEach(titles, id: \.self){title in
                    CustomTabButton(title: title, selected: selectedIndex == titles.firstIndex(of: title)) {
                        selectedIndex = titles.firstIndex(of: title) ?? 0
                    }
                }
                
            }.padding(EdgeInsets(top: 16, leading: 0, bottom: 16, trailing: 0))
            
            getSelectedView()
            
        }.background(Color.black.opacity(0.4).padding(EdgeInsets(top: 0, leading: -16, bottom: 0, trailing: -16)))
    }
    
    
    func getSelectedView() -> AnyView{
        return associatedViews[$selectedIndex.wrappedValue]
    }
}
