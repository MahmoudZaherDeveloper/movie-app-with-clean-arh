package com.mahmoud.zaher.fawrytask.domain.interactors

import com.mahmoud.zaher.fawrytask.domain.model.Movie
import com.mahmoud.zaher.fawrytask.domain.repository.MovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(val movieRepository: MovieRepository) {
    suspend fun getMovieList(): List<Movie> {
        return movieRepository.getMovieList()
    }

    fun getCashedUsers() = movieRepository.getAllCashed()
}