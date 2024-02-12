package com.mikhailapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mikhailapp.ui.screen.DetailsScreen
import com.mikhailapp.ui.screen.ListScreen
import com.mikhailapp.viewmodel.ListScreenViewModel
import com.mikhailapp.viewmodel.ProductScreenViewModel

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ScreenSettings.ListScreenRoute.route) {
        composable(
            route = ScreenSettings.ListScreenRoute.route
        ) {
            val hiltViewModel = hiltViewModel<ListScreenViewModel>()
            ListScreen(navController = navController, viewModel = hiltViewModel)
        }
        composable(
            route = "${ScreenSettings.ItemScreenRoute.route}/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType }
            )
        ) {
            val hiltViewModel = hiltViewModel<ProductScreenViewModel>()
            LaunchedEffect(Unit) {
                var productId = it.arguments?.getString("id")
                hiltViewModel.loadProduct(productId)
            }
            DetailsScreen(navController = navController, viewModel = hiltViewModel)
        }
//        composable(
//            route = ScreenSettings.ItemScreenRoute.route
//        ) {
//            val hiltViewModel = hiltViewModel<ProductScreenViewModel>()
//            DetailsScreen(navController = navController, viewModel = hiltViewModel)
//        }
    }
}