package com.writepulp.composemovieapp.presentation.movie

sealed class MovieEvent {

    data class Search(val searchString: String): MovieEvent()
}