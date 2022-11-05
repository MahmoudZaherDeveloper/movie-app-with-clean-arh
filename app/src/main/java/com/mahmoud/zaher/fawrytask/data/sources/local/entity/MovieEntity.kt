package com.mahmoud.zaher.fawrytask.data.sources.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @ColumnInfo(name = "movie_name")
    val movieName: String
)