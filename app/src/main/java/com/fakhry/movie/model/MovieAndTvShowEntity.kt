package com.fakhry.movie.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieAndTvShowEntity(
    var id: Int,
    var title: String,
    var synopsis: String,
    var poster_url: String,
    var backdrop_url: String,
    var rating: Double,
) : Parcelable