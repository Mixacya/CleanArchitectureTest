package com.mikhailapp.data.network

import com.mikhailapp.data.models.Details
import com.mikhailapp.data.models.ProductItems
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkApi {

    @GET("/items/random")
    suspend fun getRandomProductItems(): ProductItems

    @GET("/texts/{itemId}")
    suspend fun getProductById(@Path("itemId") itemId: String): Details

}