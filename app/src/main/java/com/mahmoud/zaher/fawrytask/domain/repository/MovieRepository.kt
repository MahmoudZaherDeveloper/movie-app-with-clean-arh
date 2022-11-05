package com.mahmoud.zaher.fawrytask.domain.repository

import com.mahmoud.zaher.fawrytask.domain.model.Movie

interface MovieRepository {
    suspend fun getMovieList(): List<Movie>
}