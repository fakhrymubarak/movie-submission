package com.fakhry.movie.data.source.remote


import com.fakhry.movie.BuildConfig
import com.fakhry.movie.data.source.remote.response.movie.details.GetMovieDetailsResponseModel
import com.fakhry.movie.data.source.remote.response.movie.popular.GetMovieResponseModel
import com.fakhry.movie.data.source.remote.response.tvshow.popular.GetTvShowResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val TMDB_BASE_URL = BuildConfig.TMDB_BASE_API
    }

    @GET("movie/popular?")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
    ): Call<GetMovieResponseModel>

    @GET("movie/{id}?")
    fun getMovieDetails(
        @Path("id")
        @Query("api_key") apiKey: String,
    ): Call<GetMovieDetailsResponseModel>

    @GET("tv/popular?")
    fun getPopularTvShows(
        @Query("api_key") apiKey: String,
    ): Call<GetTvShowResponseModel>

    @GET("tv/{id}?")
    fun getTvShowDetails(
        @Path("id")
        @Query("api_key") apiKey: String,
    ): Call<GetTvShowResponseModel>

}
