package com.fakhry.movie.data.source.remote.response.tvshow.details

import com.google.gson.annotations.SerializedName

data class CreatedByItem(

	@field:SerializedName("gender")
	val gender: Int,

	@field:SerializedName("credit_id")
	val creditId: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("profile_path")
	val profilePath: String,

	@field:SerializedName("id")
	val id: Int
)