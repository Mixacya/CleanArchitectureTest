package com.mikhailapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mikhailapp.constants.Constants.BASE_API_URL
import com.mikhailapp.domain.models.Product
import com.mikhailapp.presentation.R
import com.mikhailapp.ui.navigation.ScreenSettings
import com.mikhailapp.viewmodel.ListScreenViewModel
import kotlinx.coroutines.channels.Channel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(navController: NavController, viewModel: ListScreenViewModel) {
//    val testUrl = "https://test-task-server.mediolanum.f17y.com/images/raspberry.png"

    val snackbarHostState = remember { SnackbarHostState() }
    val channel = remember { Channel<Int>(Channel.CONFLATED) }
    LaunchedEffect(channel) {
        if (viewModel.liveData.value == null) {
            viewModel.getProductItems()
        }
        viewModel.errorFlow.collect {
            snackbarHostState.showSnackbar(it)
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text(stringResource(id = R.string.food_screen_name)) },
                actions = {

                    IconButton(onClick = {
                        viewModel.getProductItems()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = stringResource(id = R.string.common_refresh)
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        val productItems by viewModel.liveData.observeAsState()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_32dp)),
            contentPadding = PaddingValues(all = dimensionResource(id = R.dimen.padding_32dp))
        ) {
            productItems?.products?.let { list ->
                items(
                    count = list.size
                ) { index ->
                    val item = list[index]
                    CardItem(product = item) {
                        navController.navigate(
                            route = "${ScreenSettings.ItemScreenRoute.route}/${item.id}"
                        )
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItem(product: Product, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = Modifier.height(dimensionResource(id = R.dimen.card_height_80dp)),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = dimensionResource(id = R.dimen.padding_16dp)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val imgUrl = BASE_API_URL + product.imageUrl
            Box {
                Text(
                    text = product.name ?: stringResource(id = R.string.common_placeholder),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = dimensionResource(id = R.dimen.placeholder_text_size_20).value.sp
                    )
                )
            }
            AsyncImage(
                model = imgUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(
                        dimensionResource(id = R.dimen.food_img_size_100dp),
                        dimensionResource(id = R.dimen.food_img_size_100dp)
                    )
                    .padding(dimensionResource(id = R.dimen.padding_8dp))
            )
        }

    }
}