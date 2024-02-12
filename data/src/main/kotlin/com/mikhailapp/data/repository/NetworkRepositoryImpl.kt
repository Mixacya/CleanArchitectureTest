package com.mikhailapp.data.repository

import com.mikhailapp.data.models.Details
import com.mikhailapp.data.models.ProductItems
import com.mikhailapp.data.network.NetworkApi
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi
) : NetworkRepository {

    override suspend fun getRandomItems(): ProductItems {
        return networkApi.getRandomProductItems()
    }

    override suspend fun getProductById(itemId: String): Details {
        return networkApi.getProductById(itemId)
    }

}