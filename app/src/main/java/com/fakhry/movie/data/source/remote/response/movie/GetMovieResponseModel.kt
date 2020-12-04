package com.fakhry.movie.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class GetMovieResponseModel(

    @field:SerializedName("page")
	val page: Int,

    @field:SerializedName("total_pages")
	val totalPages: Int,

    @field:SerializedName("results")
	val results: List<MovieResponse>,

    @field:SerializedName("total_results")
	val totalResults: Int
)