package com.writepulp.composemovieapp.presentation.detail

import com.writepulp.composemovieapp.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading : Boolean = false,
    val movie : MovieDetail? = null,
    val error : String = ""
)