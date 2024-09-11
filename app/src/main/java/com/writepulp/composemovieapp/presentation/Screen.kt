package com.writepulp.composemovieapp.presentation

sealed class Screen (val route:String) {
    data object MovieScreen : Screen("movie_screen")
    data object MovieDetailScreen : Screen("movie_detail_screen")
}