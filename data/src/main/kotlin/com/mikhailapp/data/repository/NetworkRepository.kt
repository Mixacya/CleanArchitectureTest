package com.mikhailapp.data.repository

import com.mikhailapp.data.models.Details
import com.mikhailapp.data.models.ProductItems

interface NetworkRepository {

    suspend fun getRandomItems(): ProductItems

    suspend fun getProductById(itemId: String): Details

}