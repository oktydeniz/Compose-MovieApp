package com.writepulp.composemovieapp.presentation.movie

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.writepulp.composemovieapp.datautil.Resource
import com.writepulp.composemovieapp.domain.usecase.getmovies.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val useCase: GetMovieUseCase
): ViewModel() {

    private val _state  = mutableStateOf<MovieState>(MovieState())
    val state: State<MovieState> = _state

    private var job: Job? = null

    init {
        getMovies(_state.value.search)
    }

    private fun getMovies(search: String) {
        job?.cancel()
        job = useCase.executeGetMovies(search).onEach {
            when(it) {
                is Resource.Success -> {
                    _state.value = MovieState(movies = it.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = MovieState(error = it.message?: "Error !")
                }
                is Resource.Loading -> {
                    _state.value = MovieState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: MovieEvent) {
        when(event) {
            is MovieEvent.Search -> {
                getMovies(event.searchString)
            }
        }
    }

}