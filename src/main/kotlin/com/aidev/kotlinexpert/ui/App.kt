package com.aidev.kotlinexpert.ui

import androidx.compose.runtime.*
import com.aidev.kotlinexpert.ui.screens.detail.DetailScreen
import com.aidev.kotlinexpert.ui.screens.detail.DetailViewModel
import com.aidev.kotlinexpert.ui.screens.home.HomeScreen
import com.aidev.kotlinexpert.ui.screens.home.HomeViewModel

sealed interface Route {
    data object Home: Route
    data class Detail(val id: Long): Route
}

@Composable
fun App() {

    var route by remember { mutableStateOf<Route>(Route.Home) }
    val scope = rememberCoroutineScope()

    route.let {
        when(it) {
            is Route.Home -> HomeScreen(vm = HomeViewModel(scope), onCreateClick = { route = Route.Detail(-1L)})
            is Route.Detail -> DetailScreen(vm = DetailViewModel(scope, it.id), onClose = { route = Route.Home })

        }
    }
}

