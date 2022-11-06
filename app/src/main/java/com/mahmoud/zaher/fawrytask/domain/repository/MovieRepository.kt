package com.mahmoud.zaher.fawrytask.domain.repository

import com.mahmoud.zaher.fawrytask.domain.model.Movie

interface MovieRepository {
    suspend fun getMovieList(pageNumber: Int): List<Movie>
    fun getAllCashed():List<Movie>
}