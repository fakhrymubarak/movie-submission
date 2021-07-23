package com.fakhry.movie.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_movie")
    var movieId : Int,

    @ColumnInfo(name = "title")
    var title : String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "poster_path")
    var posterPath: String?,

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String?,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double,
    
    @ColumnInfo(name = "is_fav_movie")
    var isFavMovie: Boolean
)