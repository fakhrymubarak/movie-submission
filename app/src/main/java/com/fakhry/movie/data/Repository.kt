package com.fakhry.movie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fakhry.movie.data.source.remote.RemoteRepository
import com.fakhry.movie.data.source.remote.response.movie.details.MovieDetailsResponse
import com.fakhry.movie.data.source.remote.response.movie.popular.MovieResponse
import com.fakhry.movie.data.source.remote.response.tvshow.details.TvShowDetailsResponse
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
        val movieDetails = MutableLiveData<MovieDetailsResponse>()
        remoteRepository.getMovieDetails(object : RemoteRepository.LoadMovieDetailsCallback {
            override fun onMovieDetailsReceived(movieDetailsResponse: MovieDetailsResponse) {
                movieDetails.postValue(movieDetailsResponse)
            }
        }, movieId)
        return movieDetails
    }

    override fun getTvShowDetails(tvShowId: Int): LiveData<TvShowDetailsResponse> {
        val tvShowDetails = MutableLiveData<TvShowDetailsResponse>()
        remoteRepository.getTvShowDetails(object : RemoteRepository.LoadTvShowDetailsCallback {
            override fun onTvShowDetailReceived(tVShowDetailResponse: TvShowDetailsResponse) {
                tvShowDetails.postValue(tVShowDetailResponse)
            }
        }, tvShowId)
        return tvShowDetails
    }
}
