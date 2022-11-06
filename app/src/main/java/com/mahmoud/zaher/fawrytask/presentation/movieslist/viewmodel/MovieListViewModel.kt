package com.mahmoud.zaher.fawrytask.presentation.movieslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoud.zaher.fawrytask.domain.interactors.MovieInteractor
import com.mahmoud.zaher.fawrytask.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(val movieInteractor: MovieInteractor) : ViewModel() {
    val screenState by lazy { MutableStateFlow<MovieListScreenState>(MovieListScreenState.Initial) }
    private var canLoading = true
    private var pageNumber = 1

    init {
        getAllMovies(pageNumber)
    }

    fun loadMore() {
        if (canLoading) {
            pageNumber++
            getAllMovies(pageNumber)
        }
    }

    private fun getAllMovies(pageNumber: Int) {
        screenState.value = MovieListScreenState.Loading
        canLoading = false
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = movieInteractor.getMovieList(pageNumber)
                screenState.value = MovieListScreenState.Success(result)
                screenState.value = MovieListScreenState.LoadingNextPage(result)
                canLoading = true
            } catch (exception: Exception) {
                screenState.value =
                    MovieListScreenState.Error(
                        message = exception.message ?: "Something wrong happened!"
                    )
            }
        }
    }

    fun loadCachedData() {
        screenState.value = MovieListScreenState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = movieInteractor.getCashedUsers()
                screenState.value = MovieListScreenState.Success(result)
            } catch (exception: Exception) {
                screenState.value =
                    MovieListScreenState.Error(
                        message = exception.message ?: "Something wrong happened!"
                    )
            }
        }
    }


}

sealed class MovieListScreenState {
    object Initial : MovieListScreenState()
    object Loading : MovieListScreenState()
    data class Success(val movies: List<Movie>) : MovieListScreenState()
    data class LoadingNextPage(val movies: List<Movie>) : MovieListScreenState()
    data class Error(val message: String) : MovieListScreenState()
}