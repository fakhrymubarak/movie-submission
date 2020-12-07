package com.fakhry.movie.data.source.remote.response.tvshow.popular

import com.google.gson.annotations.SerializedName

data class GetTvShowResponseModel(

    @field:SerializedName("page")
	val page: Int,

    @field:SerializedName("total_pages")
	val totalPages: Int,

    @field:SerializedName("results")
	val results: List<TvShowResponse>,

    @field:SerializedName("total_results")
	val totalResults: Int
)