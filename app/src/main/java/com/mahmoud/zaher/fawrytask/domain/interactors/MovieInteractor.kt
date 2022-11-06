package com.mahmoud.zaher.fawrytask.domain.interactors

import com.mahmoud.zaher.fawrytask.data.sources.remote.pojo.moviedetails.MovieDetails
import com.mahmoud.zaher.fawrytask.domain.model.Movie
import com.mahmoud.zaher.fawrytask.domain.repository.MovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(val movieRepository: MovieRepository) {
    suspend fun getMovieList(pageNumber: Int): List<Movie> {
        return movieRepository.getMovieList(pageNumber)
    }

    suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return movieRepository.getMovieByID(movieId)
    }

    fun getCashedUsers() = movieRepository.getAllCashed()
}