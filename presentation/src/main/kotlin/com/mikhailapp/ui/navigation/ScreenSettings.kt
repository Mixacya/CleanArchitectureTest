package com.mikhailapp.ui.navigation

sealed class ScreenSettings(val route: String) {
    object ListScreenRoute : ScreenSettings("list_screen_route")
    object ItemScreenRoute : ScreenSettings("item_screen_route")
}
