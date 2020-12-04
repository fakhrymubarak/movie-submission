package com.fakhry.movie.data

import androidx.lifecycle.LiveData
import com.fakhry.movie.data.source.remote.RemoteDataSource
import com.fakhry.movie.data.source.remote.response.movie.MovieResponse

class Repository private constructor(private val remoteDataSource: RemoteDataSource) :
    DataSource {
    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(remoteDataSource: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteDataSource)
            }
    }

    override fun getPopularMovies(): LiveData<List<MovieResponse>> =
        remoteDataSource.getPopularMovies()


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
