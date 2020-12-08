package com.fakhry.movie.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_tvshow_entities")
data class FavTvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movie_id")
    var movieId : String,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double
)