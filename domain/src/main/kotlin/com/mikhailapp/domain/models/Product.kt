package com.mikhailapp.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val id: String?,
    val name: String?,
    val imageUrl: String?,
    val color: String?
) : Parcelable
