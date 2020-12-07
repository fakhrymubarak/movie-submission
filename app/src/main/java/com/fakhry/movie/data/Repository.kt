package com.fakhry.movie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fakhry.movie.data.source.remote.RemoteRepository
import com.fakhry.movie.data.source.remote.response.movie.popular.MovieResponse
import com.fakhry.movie.data.source.remote.response.tvshow.popular.TvShowResponse

class Repository(private val remoteRepository: RemoteRepository) :
    DataSource {
    companion object {
        val TAG = Repository::class.simpleName

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

//    override fun getAllTvShows(): LiveData<List<MovieAndTvShowEntity>> {
//        val tvShowsResult = MutableLiveData<List<MovieAndTvShowEntity>>()
//        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadAllTvShowsCallback {
//            override fun onAllTvShowReceived(tvShowResponses: List<MovieAndTvShowResponse>) {
//                val tvShowList = ArrayList<MovieAndTvShowEntity>()
//                for (response in tvShowResponses) {
//                    val show = MovieAndTvShowEntity(
//                        response.id,
//                        response.title,
//                        response.synopsis,
//                        response.poster_url,
//                        response.backdrop_url,
//                        response.rating
//                    )
//                    tvShowList.add(show)
//                }
//                tvShowsResult.postValue(tvShowList)
//            }
//        })
//        return tvShowsResult
//    }
//
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
