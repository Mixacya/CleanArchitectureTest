package com.mikhailapp.ui.navigation

import android.os.Parcelable
import androidx.navigation.NavController

fun <T: Parcelable> NavController.navigateWithParcelize(route: String, key: String, value: T) {
    this.currentBackStackEntry?.savedStateHandle?.apply {
        set(key, value)
    }
    this.navigate(route)
}