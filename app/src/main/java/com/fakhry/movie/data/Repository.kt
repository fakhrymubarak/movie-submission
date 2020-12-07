package com.fakhry.movie.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fakhry.movie.data.source.remote.RemoteRepository
import com.fakhry.movie.data.source.remote.response.movie.details.MovieDetailsResponse
import com.fakhry.movie.data.source.remote.response.movie.popular.MovieResponse
import com.fakhry.movie.data.source.remote.response.tvshow.popular.TvShowResponse

class Repository(private val remoteRepository: RemoteRepository) :
    DataSource {
    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(remoteRepository: RemoteRepository): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteRepository)
            }
    }

    override fun getPopularMovies(): LiveData<List<MovieResponse>> {
        val listPopularMovies = MutableLiveData<List<MovieResponse>>()
        remoteRepository.getPopularMovies(object : RemoteRepository.LoadAllMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<MovieResponse>) {
                listPopularMovies.postValue(movieResponses)
            }
        })
        return listPopularMovies
    }

    override fun getPopularTvShows(): LiveData<List<TvShowResponse>> {
        val listPopularTvShow = MutableLiveData<List<TvShowResponse>>()
        remoteRepository.getPopularTvShows(object : RemoteRepository.LoadAllTvShowsCallback {
            override fun onAllTvShowReceived(tvShowResponses: List<TvShowResponse>) {
                listPopularTvShow.postValue(tvShowResponses)
            }
        })
        return listPopularTvShow
    }

    override fun getMovieDetails(movieId: Int): LiveData<MovieDetailsResponse> {
        Log.d("asfas", "1 movie id = $movieId")
        val movieDetails = MutableLiveData<MovieDetailsResponse>()
        remoteRepository.getMovieDetails(object : RemoteRepository.LoadMovieDetailsCallback {
            override fun onMovieDetailsReceived(movieDetailsResponse: MovieDetailsResponse) {
                movieDetails.postValue(movieDetailsResponse)
            }
        }, movieId)
        return movieDetails
    }
//
//    fun getTvShowDetails(tvShowId: Int): LiveData<TvShowDetailsResponse> {
//        TODO("Not yet implemented")
//    }

//    override fun getMovieDetails(movieId: Int): LiveData<MovieAndTvShowEntity> {
//        val moviesResult = MutableLiveData<MovieAndTvShowEntity>()
//        remoteDataSource.getMovieDetails(movieId,
//            object : RemoteDataSource.LoadMovieDetailsCallback {
//                override fun onMovieDetailsReceived(movieDetailResponse: MovieAndTvShowResponse) {
//                    val movieResponse = MovieAndTvShowEntity(
//                        movieDetailResponse.id,
//                        movieDetailResponse.title,
//                        movieDetailResponse.synopsis,
//                        movieDetailResponse.poster_url,
//                        movieDetailResponse.backdrop_url,
//                        movieDetailResponse.rating
//                    )
//                    moviesResult.postValue(movieResponse)
//                }
//            })
//        return moviesResult
//    }
//
//    override fun getTvShowDetails(tvShowId: Int): LiveData<MovieAndTvShowEntity> {
//        val tvShowResult = MutableLiveData<MovieAndTvShowEntity>()
//        remoteDataSource.getTvShowDetails(tvShowId,
//            object : RemoteDataSource.LoadTvShowDetailsCallback {
//                override fun onTvShowDetailReceived(tVShowDetailResponse: MovieAndTvShowResponse) {
//                    val tvShowResponse = MovieAndTvShowEntity(
//                        tVShowDetailResponse.id,
//                        tVShowDetailResponse.title,
//                        tVShowDetailResponse.synopsis,
//                        tVShowDetailResponse.poster_url,
//                        tVShowDetailResponse.backdrop_url,
//                        tVShowDetailResponse.rating
//                    )
//                    tvShowResult.postValue(tvShowResponse)
//                }
//            })
//        return tvShowResult
//    }
}
