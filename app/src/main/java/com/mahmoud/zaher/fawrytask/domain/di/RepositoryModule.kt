package com.mahmoud.zaher.fawrytask.domain.di

import com.mahmoud.zaher.fawrytask.data.repository.MoviesRepositoryImpl
import com.mahmoud.zaher.fawrytask.data.sources.local.MovieDao
import com.mahmoud.zaher.fawrytask.data.sources.remote.MovieApi
import com.mahmoud.zaher.fawrytask.domain.repository.MovieRepository
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@HiltAndroidApp
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides  //bind for interfaces and abstraction
    @Singleton
    fun provideRepository(
        remoteDataSource: MovieApi,
        movieDao: MovieDao
    ): MovieRepository {
        return MoviesRepositoryImpl(remoteDataSource, movieDao)
    }
}
// todo for seperate domain from data
/*@HiltAndroidApp
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(): MovieRepository
}*/