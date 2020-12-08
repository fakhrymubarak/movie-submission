package com.fakhry.movie.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_mov_entities")
data class FavMovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movie_id")
    var movieId : String,

    @ColumnInfo(name = "title")
    var title : String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double
)