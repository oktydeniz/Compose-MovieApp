package com.writepulp.composemovieapp.presentation.movie

import com.writepulp.composemovieapp.domain.model.Movie

data class MovieState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error:String = "",
    val search: String = "batman"
)