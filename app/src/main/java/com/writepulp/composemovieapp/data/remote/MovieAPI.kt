package com.writepulp.composemovieapp.data.remote

import com.writepulp.composemovieapp.data.remote.dto.MovieDetailDto
import com.writepulp.composemovieapp.data.remote.dto.MoviesDto
import com.writepulp.composemovieapp.datautil.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString: String,
        @Query("apikey") apiKey: String = API_KEY
    ): MoviesDto

    @GET(".")
    suspend fun getDetail(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String = API_KEY
    ): MovieDetailDto
}