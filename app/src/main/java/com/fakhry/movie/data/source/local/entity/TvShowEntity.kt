package com.fakhry.movie.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show_entity")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_tv_show")
    var tvShowId : Int,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "poster_path")
    var posterPath: String,

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double,

    @ColumnInfo(name = "is_fav_tv_show")
    var isFavTvShow: Boolean
)