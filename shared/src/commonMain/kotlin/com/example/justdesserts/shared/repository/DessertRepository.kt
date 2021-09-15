package com.example.justdesserts.shared.repository

import com.example.justdesserts.*
import com.example.justdesserts.shared.ApolloProvider
import com.example.justdesserts.shared.cache.*
import com.example.justdesserts.type.DessertInput
import kotlinx.coroutines.flow.single

class DessertRepository(apolloProvider: ApolloProvider) : BaseRepository(apolloProvider) {

    // Remote Repositories (Network):

    suspend fun getDesserts(page: Int, size: Int): Desserts? {
        val response = apolloClient.query(GetDessertsQuery(page, size)).execute().single()
        return response.data?.desserts?.toDesserts()
    }

    suspend fun getDessert(dessertId: String): DessertDetail? {
        val response = apolloClient.query(GetDessertQuery(dessertId)).execute().single()
        return response.data?.dessert?.toDessertDetail()
    }

    suspend fun newDessert(dessertInput: DessertInput): Dessert? {
        val response = apolloClient.mutate(NewDessertMutation(dessertInput))
            .execute().single()
        return response.data?.createDessert?.toDessert()
    }

    suspend fun updateDessert(dessertId: String, dessertInput: DessertInput): Dessert? {
        val response = apolloClient.mutate(UpdateDessertMutation(dessertId, dessertInput))
            .execute().single()
        return response.data?.updateDessert?.toDessert()
    }

    suspend fun deleteDessert(dessertId: String): Boolean? {
        val response = apolloClient.mutate(DeleteDessertMutation(dessertId))
            .execute().single()
        return response.data?.deleteDessert
    }


    // Local Repositories (Database):

    fun saveFavorite(dessert: Dessert) {
        database.saveDessert(dessert)
    }

    fun removeFavorite(dessertId: String) {
        database.deleteDessert(dessertId)
    }

    fun updateFavorite(dessert: Dessert) {
        database.updateDessert(dessert)
    }

    fun getFavoriteDessert(dessertId: String): Dessert? {
        return database.getDessertById(dessertId)
    }

    fun getFavoriteDesserts(): List<Dessert> {
        return database.getDesserts()
    }
}