package com.fakhry.movie.data.source.remote

import com.fakhry.movie.data.source.remote.response.MovieAndTvShowResponse
import com.fakhry.movie.utils.JsonHelper

class RemoteDataSource(private val jsonHelper: JsonHelper) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getAllMovies(): List<MovieAndTvShowResponse> = jsonHelper.loadPopularMovies()

    fun getAllTvShows(): List<MovieAndTvShowResponse> = jsonHelper.loadPopularTvShows()

    fun getMovieDetails(movieId : Int): MovieAndTvShowResponse = jsonHelper.loadPopularMovies()[movieId]

    fun getTvShowsDetails(showId : Int): MovieAndTvShowResponse = jsonHelper.loadPopularTvShows()[showId]

}