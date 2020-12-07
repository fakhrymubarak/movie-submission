package com.fakhry.movie.data.source.remote

import android.util.Log
import com.fakhry.movie.BuildConfig
import com.fakhry.movie.data.source.remote.response.ApiConfig
import com.fakhry.movie.data.source.remote.response.MovieAndTvShowResponse
import com.fakhry.movie.data.source.remote.response.movie.popular.GetMovieResponseModel
import com.fakhry.movie.data.source.remote.response.movie.popular.MovieResponse
import com.fakhry.movie.data.source.remote.response.tvshow.popular.GetTvShowResponseModel
import com.fakhry.movie.data.source.remote.response.tvshow.popular.TvShowResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository {
    private val service = ApiConfig.getApiService(ApiService.TMDB_BASE_URL)

    companion object {
        const val API_KEY = BuildConfig.TMDB_API_KEY
        val TAG = RemoteRepository::class.simpleName

        @Volatile
        private var instance: RemoteRepository? = null

        fun getInstance(): RemoteRepository =
            instance ?: synchronized(this) {
                instance ?: RemoteRepository()
            }
    }

    fun getPopularMovies(callback: LoadAllMoviesCallback) {
        service.getPopularMovies(API_KEY).enqueue(object : Callback<GetMovieResponseModel> {
            override fun onResponse(
                call: Call<GetMovieResponseModel>,
                response: Response<GetMovieResponseModel>,
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val data = responseBody?.results
                    data?.let { callback.onAllMoviesReceived(it) }
                }
            }

            override fun onFailure(call: Call<GetMovieResponseModel>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }

    fun getPopularTvShows(callback: LoadAllTvShowsCallback){
        service.getPopularTvShows(API_KEY).enqueue(object : Callback<GetTvShowResponseModel> {
            override fun onResponse(
                call: Call<GetTvShowResponseModel>,
                response: Response<GetTvShowResponseModel>,
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val data = responseBody?.results
                    data?.let { callback.onAllTvShowReceived(it) }
                }
            }

            override fun onFailure(call: Call<GetTvShowResponseModel>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }

//    fun getAllTvShows(callback: LoadAllTvShowsCallback) {
//        handler.postDelayed({ callback.onAllTvShowReceived(jsonHelper.loadPopularTvShows()) },
//            SERVICE_LATENCY_IN_MILLIS)
//    }
//
//    fun getMovieDetails(movieId: Int, callback: LoadMovieDetailsCallback) {
//        handler.postDelayed({
//            callback.onMovieDetailsReceived(jsonHelper.loadPopularMovieDetails(movieId))
//        }, SERVICE_LATENCY_IN_MILLIS)
//    }
//
//    fun getTvShowDetails(tvShowId: Int, callback: LoadTvShowDetailsCallback) {
//        handler.postDelayed({
//            callback.onTvShowDetailReceived(jsonHelper.loadPopularTvShowDetails(tvShowId))
//        }, SERVICE_LATENCY_IN_MILLIS)
//    }


    interface LoadAllMoviesCallback {
        fun onAllMoviesReceived(movieResponses: List<MovieResponse>)
    }

    interface LoadAllTvShowsCallback {
        fun onAllTvShowReceived(tvShowResponses: List<TvShowResponse>)
    }

    interface LoadMovieDetailsCallback {
        fun onMovieDetailsReceived(movieDetailResponse: MovieAndTvShowResponse)
    }

    interface LoadTvShowDetailsCallback {
        fun onTvShowDetailReceived(tVShowDetailResponse: MovieAndTvShowResponse)
    }
}