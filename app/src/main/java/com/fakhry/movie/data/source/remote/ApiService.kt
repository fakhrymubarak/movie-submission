package com.fakhry.movie.data.source.remote


import com.fakhry.movie.BuildConfig
import com.fakhry.movie.data.source.remote.response.movie.GetMovieResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val TMDB_BASE_URL = BuildConfig.TMDB_BASE_API
    }

    /**
     * REGISTER AND LOGIN BUSINESS
     * */
    @GET("movie/popular?")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
    ): Call<GetMovieResponseModel>
}
