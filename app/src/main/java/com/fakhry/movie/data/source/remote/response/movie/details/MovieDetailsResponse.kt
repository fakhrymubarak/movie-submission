package com.fakhry.movie.data.source.remote.response.movie.details

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,
)