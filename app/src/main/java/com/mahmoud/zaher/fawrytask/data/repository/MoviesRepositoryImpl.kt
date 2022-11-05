package com.mahmoud.zaher.fawrytask.data.repository

import com.mahmoud.zaher.fawrytask.data.mapper.mapToMovie
import com.mahmoud.zaher.fawrytask.data.mapper.mapToMovieEntity
import com.mahmoud.zaher.fawrytask.data.sources.local.MovieDao
import com.mahmoud.zaher.fawrytask.data.sources.remote.MovieApi
import com.mahmoud.zaher.fawrytask.domain.model.Movie
import com.mahmoud.zaher.fawrytask.domain.repository.MovieRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieApi,
    private val movieDao: MovieDao
) : MovieRepository {
    override suspend fun getMovieList(): List<Movie> {
        val result = remoteDataSource.getMovies().genres.map {
            it.mapToMovieEntity()
        }
        //todo * to accept list
        // movieDao.insertAll(*result.toTypedArray())
        movieDao.insertAll(result)
        return remoteDataSource.getMovies().genres.map {
            it.mapToMovie()
            //todo mapToMoviee(it)
        }
    }

}