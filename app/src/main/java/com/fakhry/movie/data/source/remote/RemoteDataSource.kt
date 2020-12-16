package com.fakhry.movie.data.source.remote

import android.util.Log
import com.fakhry.movie.BuildConfig
import com.fakhry.movie.data.source.remote.response.ApiConfig
import com.fakhry.movie.data.source.remote.response.movie.details.MovieDetailsResponse
import com.fakhry.movie.data.source.remote.response.movie.popular.GetMovieResponseModel
import com.fakhry.movie.data.source.remote.response.movie.popular.MovieResponse
import com.fakhry.movie.data.source.remote.response.tvshow.details.TvShowDetailsResponse
import com.fakhry.movie.data.source.remote.response.tvshow.popular.GetTvShowResponseModel
import com.fakhry.movie.data.source.remote.response.tvshow.popular.TvShowResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    private val service = ApiConfig.getApiService(ApiService.TMDB_BASE_URL)

    companion object {
        const val API_KEY = BuildConfig.TMDB_API_KEY
        val TAG = RemoteDataSource::class.simpleName

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
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
                    if (data != null) {
                        val apiResponseData = ApiResponse.success(data)
                        callback.onAllMoviesReceived(apiResponseData)
                    }
                }
            }

            override fun onFailure(call: Call<GetMovieResponseModel>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }

    fun getPopularTvShows(callback: LoadAllTvShowsCallback) {
        service.getPopularTvShows(API_KEY).enqueue(object : Callback<GetTvShowResponseModel> {
            override fun onResponse(
                call: Call<GetTvShowResponseModel>,
                response: Response<GetTvShowResponseModel>,
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val data = responseBody?.results
                    if (data != null) {
                        val apiResponseData = ApiResponse.success(data)
                        callback.onAllTvShowReceived(apiResponseData)
                    }
                }
            }

            override fun onFailure(call: Call<GetTvShowResponseModel>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }


    fun getMovieDetails(movieId: Int, callback: LoadMovieDetailsCallback) {
        service.getMovieDetails(movieId, API_KEY).enqueue(object : Callback<MovieDetailsResponse> {
            override fun onResponse(
                call: Call<MovieDetailsResponse>,
                response: Response<MovieDetailsResponse>,
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.let {
                        val apiResponseData = ApiResponse.success(it)
                        callback.onMovieDetailsReceived(apiResponseData)
                    }
                }
            }

            override fun onFailure(call: Call<MovieDetailsResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }

    fun getTvShowDetails(tvShowId: Int, callback: LoadTvShowDetailsCallback) {
        service.getTvShowDetails(tvShowId, API_KEY)
            .enqueue(object : Callback<TvShowDetailsResponse> {
                override fun onResponse(
                    call: Call<TvShowDetailsResponse>,
                    response: Response<TvShowDetailsResponse>,
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        responseBody?.let {
                            val apiResponseData = ApiResponse.success(it)
                            callback.onTvShowDetailReceived(apiResponseData)
                        }
                    }
                }

                override fun onFailure(call: Call<TvShowDetailsResponse>, t: Throwable) {
                    Log.e(TAG, t.message.toString())
                }
            })
    }

    interface LoadAllMoviesCallback {
        fun onAllMoviesReceived(movieResponses: ApiResponse<List<MovieResponse>>)
    }

    interface LoadAllTvShowsCallback {
        fun onAllTvShowReceived(tvShowResponses: ApiResponse<List<TvShowResponse>>)
    }

    interface LoadMovieDetailsCallback {
        fun onMovieDetailsReceived(movieDetailsResponse: ApiResponse<MovieDetailsResponse>)
    }

    interface LoadTvShowDetailsCallback {
        fun onTvShowDetailReceived(tvShowDetailResponse: ApiResponse<TvShowDetailsResponse>)
    }
}