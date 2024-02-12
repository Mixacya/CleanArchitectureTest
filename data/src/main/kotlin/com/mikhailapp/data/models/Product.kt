package com.mikhailapp.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product (
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("image")
    val imageUrl: String?,
    @SerialName("color")
    val color: String?
)