//
//  DessertFavoriteView.swift
//  iosApp
//
//  Created by Michael Stromer on 1/17/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

@available(iOS 14.0, *)
struct DessertFavoriteView: View {
    
    private(set) var delegate: DessertDelegate
    
    @StateObject var favoriteViewModel: DessertFavoriteViewModel
    
    private(set) var detailViewModel: DessertDetailViewModel
    
    private(set) var createViewModel: DessertCreateViewModel
    
    var body: some View {
        NavigationView {
            List {
                ForEach(favoriteViewModel.favorites, id: \.id) { dessert in
                    NavigationLink(destination: DessertDetailView(detailViewModel: detailViewModel, dessert: dessert, delegate: delegate, createViewModel: createViewModel)) {
                        DessertListRowView(dessert: dessert)
                    }
                }
            }
            .navigationTitle("Favorites")
            .navigationBarItems(trailing:
                                    NavigationLink(destination: DessertCreateView(delegate: delegate, createViewModel: createViewModel, dessert: nil)) {
                    Image(systemName: "plus")
                }
            )
            .onAppear() {
                favoriteViewModel.fetchFavorites()
            }
        }
    }
    
}
