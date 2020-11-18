package com.fakhry.movie.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieAndTvShowResponse(
    val id: Int,
    val title: String,
    val synopsis: String,
    val poster_url: String,
    val backdrop_url: String,
    val rating: Double,
) : Parcelable