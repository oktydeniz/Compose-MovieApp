package com.writepulp.composemovieapp.domain.repository

import com.writepulp.composemovieapp.data.remote.dto.MovieDetailDto
import com.writepulp.composemovieapp.data.remote.dto.MoviesDto
import com.writepulp.composemovieapp.data.remote.dto.Search

interface MovieRepository {

    suspend fun getMovies(search: String): MoviesDto

    suspend fun getMovieDetail(imdbId: String) : MovieDetailDto

}