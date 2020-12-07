package com.fakhry.movie.data.source.remote.response.movie.details

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("iso_639_1")
	val iso6391: String,

	@field:SerializedName("english_name")
	val englishName: String
)