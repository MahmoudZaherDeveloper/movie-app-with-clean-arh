package com.mahmoud.zaher.fawrytask.domain.repository

import com.mahmoud.zaher.fawrytask.data.sources.remote.pojo.moviedetails.MovieDetails
import com.mahmoud.zaher.fawrytask.domain.model.Movie

interface MovieRepository {
    suspend fun getMovieList(pageNumber: Int): List<Movie>
    suspend fun getMovieByID(movieId: Int): MovieDetails
    fun getAllCashed(): List<Movie>
}