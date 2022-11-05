package com.mahmoud.zaher.fawrytask.data.sources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mahmoud.zaher.fawrytask.data.sources.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}