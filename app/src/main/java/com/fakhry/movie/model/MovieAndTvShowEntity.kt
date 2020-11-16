package com.fakhry.movie.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieAndTvShowEntity (
    var id : Int? = 0,
    var title : String? = null,
    var synopsis : String? = null,
    var poster_url : String? = null,
    var backdrop_url : String? = null,
    var rating : Double? = null
) : Parcelable