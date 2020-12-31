package com.example.justdesserts.androidApp.ui.desserts.detail

import androidx.lifecycle.ViewModel
import com.apollographql.apollo.api.ApolloExperimental
import com.example.justdesserts.GetDessertQuery
import com.example.justdesserts.shared.DessertRepository

@ApolloExperimental
class DessertDetailViewModel constructor(private val repository: DessertRepository): ViewModel() {
    suspend fun getDessert(dessertId: String): GetDessertQuery.Dessert? {
        return repository.getDessert(dessertId)
    }
    /* TODO: Create, Update, Destroy Dessert */
}