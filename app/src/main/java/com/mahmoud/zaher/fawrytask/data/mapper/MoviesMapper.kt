package com.mahmoud.zaher.fawrytask.data.mapper

import com.mahmoud.zaher.fawrytask.data.sources.local.entity.MovieEntity
import com.mahmoud.zaher.fawrytask.data.sources.remote.pojo.MovieResult
import com.mahmoud.zaher.fawrytask.domain.model.Movie

fun MovieResult.mapToMovie(): Movie {
    return Movie(this.id, this.title)
}

//todo
//fun mapToMoviee(genre: Genre): Movie {
//    return Movie(genre.id, genre.name)
//}

fun MovieResult.mapToMovieEntity(): MovieEntity {
    return MovieEntity(this.id, this.title)
}

fun MovieEntity.mapToMovie(): Movie {
    return Movie(this.id, this.movieName)
}