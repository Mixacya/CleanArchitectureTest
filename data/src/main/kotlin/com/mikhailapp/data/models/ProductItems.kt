package com.mikhailapp.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductItems(
    @SerialName("title")
    val title: String?,
    @SerialName("items")
    val products: MutableList<Product>?
)
