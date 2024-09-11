package com.writepulp.composemovieapp.domain.usecase.getmoviedetail

import coil.network.HttpException
import com.writepulp.composemovieapp.data.remote.dto.toMovieDetail
import com.writepulp.composemovieapp.datautil.Resource
import com.writepulp.composemovieapp.domain.model.MovieDetail
import com.writepulp.composemovieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    fun executeGetMovieDetail(movie: String) : Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repository.getMovieDetail(movie)
            if (movieList.Response == "True"){
                emit(Resource.Success(movieList.toMovieDetail()))
            }else {
                emit(Resource.Error("No Movie Found"))
            }
        }catch (e: IOException){
            emit(Resource.Error("No Internet Connetcion"))
        }catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: "Server Error"))
        }
    }
}