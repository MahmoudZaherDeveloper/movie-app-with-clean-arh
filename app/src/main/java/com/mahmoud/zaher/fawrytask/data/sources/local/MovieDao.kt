package com.mahmoud.zaher.fawrytask.data.sources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mahmoud.zaher.fawrytask.data.sources.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = REPLACE)
    fun insertAll(movies: List<MovieEntity>)
    //  fun insertAll(vararg movies: MovieEntity)

    //todo varargs -> you can add one object or more than one
}