package com.mikhailapp.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Details (
    val id : String,
    val text: String?
)
