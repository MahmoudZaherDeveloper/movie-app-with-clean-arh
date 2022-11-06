package com.mahmoud.zaher.fawrytask.presentation.moviesdetails.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoud.zaher.fawrytask.data.sources.remote.pojo.moviedetails.MovieDetails
import com.mahmoud.zaher.fawrytask.domain.interactors.MovieInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    val movieInteractor: MovieInteractor,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val screenState by lazy { MutableStateFlow<MovieDetailsScreenState>(MovieDetailsScreenState.Initial) }

    init {
        getMoviesDetails(savedStateHandle["id"]!!)
    }

    private fun getMoviesDetails(movieId: Int) {
        screenState.value = MovieDetailsScreenState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = movieInteractor.getMovieDetails(movieId)
                screenState.value = MovieDetailsScreenState.Success(result)
            } catch (exception: Exception) {
                screenState.value =
                    MovieDetailsScreenState.Error(
                        message = exception.message ?: "Something wrong happened!"
                    )
            }
        }
    }
}

sealed class MovieDetailsScreenState {
    object Initial : MovieDetailsScreenState()
    object Loading : MovieDetailsScreenState()
    data class Success(val movies: MovieDetails) : MovieDetailsScreenState()
    data class Error(val message: String) : MovieDetailsScreenState()
}