package com.writepulp.composemovieapp.domain.usecase.getmovies

import coil.network.HttpException
import com.writepulp.composemovieapp.data.remote.dto.toMovieList
import com.writepulp.composemovieapp.datautil.Resource
import com.writepulp.composemovieapp.domain.model.Movie
import com.writepulp.composemovieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
)
{

    fun executeGetMovies(search: String) : Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repository.getMovies(search)
            if (movieList.Response == "True"){
                emit(Resource.Success(movieList.toMovieList()))
            }else {
                emit(Resource.Error("No Movie Found"))
            }
        }catch (e:IOException){
            emit(Resource.Error("No Internet Connetcion"))
        }catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: "Server Error"))
        }
    }
}