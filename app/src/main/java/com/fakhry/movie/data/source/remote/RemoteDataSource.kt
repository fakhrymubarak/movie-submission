package com.fakhry.movie.data.source.remote

import android.os.Handler
import com.fakhry.movie.data.source.remote.response.MovieAndTvShowResponse
import com.fakhry.movie.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {
    private val handler = Handler()

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getAllMovies(callback: LoadAllMoviesCallback) {
        handler.postDelayed({ callback.onAllMoviesReceived(jsonHelper.loadPopularMovies()) },
            SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllTvShows(callback: LoadAllTvShowsCallback) {
        handler.postDelayed({ callback.onAllTvShowReceived(jsonHelper.loadPopularTvShows()) },
            SERVICE_LATENCY_IN_MILLIS)
    }

    fun getMovieDetails(movieId: Int, callback: LoadMovieDetailsCallback) {
        handler.postDelayed({
            callback.onMovieDetailsReceived(jsonHelper.loadPopularMovieDetails(movieId))
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getTvShowDetails(tvShowId: Int, callback: LoadTvShowDetailsCallback) {
        handler.postDelayed({
            callback.onTvShowDetailReceived(jsonHelper.loadPopularTvShowDetails(tvShowId))
        }, SERVICE_LATENCY_IN_MILLIS)
    }


    interface LoadAllMoviesCallback {
        fun onAllMoviesReceived(movieResponses: List<MovieAndTvShowResponse>)
    }

    interface LoadAllTvShowsCallback {
        fun onAllTvShowReceived(tvShowResponses: List<MovieAndTvShowResponse>)
    }

    interface LoadMovieDetailsCallback {
        fun onMovieDetailsReceived(movieDetailResponse: MovieAndTvShowResponse)
    }

    interface LoadTvShowDetailsCallback {
        fun onTvShowDetailReceived(tVShowDetailResponse: MovieAndTvShowResponse)
    }
}