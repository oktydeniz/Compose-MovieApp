package com.writepulp.composemovieapp.data.remote.dto

import com.writepulp.composemovieapp.data.remote.dto.Search
import com.writepulp.composemovieapp.domain.model.Movie

data class MoviesDto(
    val Response: String,
    val Search: List<Search>,
    val TotalResults: String
)

fun MoviesDto.toMovieList(): List<Movie> {
    return Search.map { s ->
        Movie(s.Poster, s.Title, s.Year, s.imdbID)
    }
}