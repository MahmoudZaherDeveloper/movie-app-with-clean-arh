package com.mahmoud.zaher.fawrytask.data.sources.di

import android.content.Context
import androidx.room.Room
import com.mahmoud.zaher.fawrytask.core.DB_NAME
import com.mahmoud.zaher.fawrytask.data.sources.local.MovieDao
import com.mahmoud.zaher.fawrytask.data.sources.local.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDao(movieDB: MovieDatabase): MovieDao {
        return movieDB.movieDao()
    }

    @Provides
    @Singleton
    fun provideMovieDB(@ApplicationContext appContext: Context): MovieDatabase {
        return Room.databaseBuilder(appContext, MovieDatabase::class.java, DB_NAME).build()
    }
}