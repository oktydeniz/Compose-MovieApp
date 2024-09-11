package com.writepulp.composemovieapp.data.repository

import com.writepulp.composemovieapp.data.remote.MovieAPI
import com.writepulp.composemovieapp.data.remote.dto.MovieDetailDto
import com.writepulp.composemovieapp.data.remote.dto.MoviesDto
import com.writepulp.composemovieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
private val api: MovieAPI
): MovieRepository {

    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovies(search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
       return api.getDetail(imdbId)
    }

}