package com.fakhry.movie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fakhry.movie.data.source.local.LocalDataSource
import com.fakhry.movie.data.source.local.entity.MovieEntity
import com.fakhry.movie.data.source.local.entity.TvShowEntity
import com.fakhry.movie.data.source.remote.ApiResponse
import com.fakhry.movie.data.source.remote.RemoteDataSource
import com.fakhry.movie.data.source.remote.response.movie.details.MovieDetailsResponse
import com.fakhry.movie.data.source.remote.response.movie.popular.MovieResponse
import com.fakhry.movie.data.source.remote.response.tvshow.details.TvShowDetailsResponse
import com.fakhry.movie.data.source.remote.response.tvshow.popular.TvShowResponse
import com.fakhry.movie.utils.AppExecutors
import com.fakhry.movie.vo.Resource

class Repository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : DataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors,
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteDataSource, localDataSource, appExecutors)
            }
    }

    override fun getPopularMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> = getDbMovies()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> {
                val listPopularMovies = MutableLiveData<ApiResponse<List<MovieResponse>>>()
                remoteDataSource.getPopularMovies(object : RemoteDataSource.LoadAllMoviesCallback {
                    override fun onAllMoviesReceived(movieResponses: ApiResponse<List<MovieResponse>>) {
                        listPopularMovies.postValue(movieResponses)
                    }
                })
                return listPopularMovies
            }

            override fun saveCallResult(data: List<MovieResponse>) {
                val moviesList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.overview,
                        response.posterPath,
                        response.backdropPath,
                        response.voteAverage,
                        false
                    )
                    moviesList.add(movie)
                }
                localDataSource.insertListMovie(moviesList)
            }
        }.asLiveData()
    }

    override fun getPopularTvShows(): LiveData<Resource<List<TvShowEntity>>> {
        return object :
            NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvShowEntity>> = getDbTvShows()

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> {
                val listPopularTvShow = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
                remoteDataSource.getPopularTvShows(object :
                    RemoteDataSource.LoadAllTvShowsCallback {
                    override fun onAllTvShowReceived(tvShowResponses: ApiResponse<List<TvShowResponse>>) {
                        listPopularTvShow.postValue(tvShowResponses)
                    }
                })
                return listPopularTvShow
            }

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (item in data) {
                    val tvShow = TvShowEntity(
                        item.id,
                        item.name,
                        item.overview,
                        item.posterPath,
                        item.backdropPath,
                        item.voteAverage,
                        false
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertListTvShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun getMovieDetails(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDetailsResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> = getDbMovieDetails(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean = data?.movieId == null

            override fun createCall(): LiveData<ApiResponse<MovieDetailsResponse>> {
                val movieDetails = MutableLiveData<ApiResponse<MovieDetailsResponse>>()
                remoteDataSource.getMovieDetails(movieId,
                    object : RemoteDataSource.LoadMovieDetailsCallback {
                        override fun onMovieDetailsReceived(movieDetailsResponse: ApiResponse<MovieDetailsResponse>) {
                            movieDetails.postValue(movieDetailsResponse)
                        }
                    })
                return movieDetails
            }

            override fun saveCallResult(data: MovieDetailsResponse) {
                val movieDetails = MovieEntity(
                    data.id,
                    data.title,
                    data.overview,
                    data.posterPath,
                    data.backdropPath,
                    data.voteAverage,
                    false
                )
                localDataSource.updateMovieDetails(movieDetails)
            }
        }.asLiveData()
    }

    override fun getTvShowDetails(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShowDetailsResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> = getDbTvShowDetails(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean = data?.tvShowId == null

            override fun createCall(): LiveData<ApiResponse<TvShowDetailsResponse>> {
                val tvShowDetails = MutableLiveData<ApiResponse<TvShowDetailsResponse>>()
                remoteDataSource.getTvShowDetails(tvShowId,
                    object : RemoteDataSource.LoadTvShowDetailsCallback {
                        override fun onTvShowDetailReceived(tVShowDetailResponse: ApiResponse<TvShowDetailsResponse>) {
                            tvShowDetails.postValue(tVShowDetailResponse)
                        }
                    })
                return tvShowDetails
            }

            override fun saveCallResult(data: TvShowDetailsResponse) {
                val tvShowDetails = TvShowEntity(
                    data.id,
                    data.name,
                    data.overview,
                    data.posterPath,
                    data.backdropPath,
                    data.voteAverage,
                    false
                )
                localDataSource.updateTvShowDetails(tvShowDetails)
            }
        }.asLiveData()
    }

    override fun getDbMovies(): LiveData<List<MovieEntity>> {
        return localDataSource.getAllMovies()
    }

    override fun getDbTvShows(): LiveData<List<TvShowEntity>> {
        return localDataSource.getAllTvShows()
    }

    override fun getDbMovieDetails(movieId: Int): LiveData<MovieEntity> {
        return localDataSource.getMovieDetails(movieId)
    }

    override fun getDbTvShowDetails(tvShowId: Int): LiveData<TvShowEntity> {
        return localDataSource.getTvShowDetails(tvShowId)
    }

    override fun setFavMovie(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavMovie(movie, state)
        }
    }

    override fun setFavTvShow(tvShow: TvShowEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavTvShow(tvShow, state)
        }
    }

    override fun getFavMovies(): LiveData<List<MovieEntity>> {
        return localDataSource.getFavMovies()
    }

    override fun getFavTvShows(): LiveData<List<TvShowEntity>> {
        return localDataSource.getFavTvShows()
    }
}
