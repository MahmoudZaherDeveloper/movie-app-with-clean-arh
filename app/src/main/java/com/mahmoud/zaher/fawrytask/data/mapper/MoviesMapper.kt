package com.mahmoud.zaher.fawrytask.data.mapper

import com.mahmoud.zaher.fawrytask.data.sources.local.entity.MovieEntity
import com.mahmoud.zaher.fawrytask.data.sources.remote.pojo.Genre
import com.mahmoud.zaher.fawrytask.domain.model.Movie

fun Genre.mapToMovie(): Movie {
    return Movie(this.id, this.name)
}

//todo
//fun mapToMoviee(genre: Genre): Movie {
//    return Movie(genre.id, genre.name)
//}

fun Genre.mapToMovieEntity(): MovieEntity {
    return MovieEntity(this.id, this.name)
}