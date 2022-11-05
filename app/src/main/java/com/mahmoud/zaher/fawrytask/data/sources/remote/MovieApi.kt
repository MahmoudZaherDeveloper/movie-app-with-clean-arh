package com.mahmoud.zaher.fawrytask.data.sources.remote

import com.mahmoud.zaher.fawrytask.data.sources.remote.pojo.MoviesResponse
import retrofit2.http.GET

interface MovieApi {
    @GET("3/movie/550?api_key=c50f5aa4e7c95a2a553d29b81aad6dd0")
    //todo how to save secure key in gradle
    suspend fun getMovies(): MoviesResponse
}