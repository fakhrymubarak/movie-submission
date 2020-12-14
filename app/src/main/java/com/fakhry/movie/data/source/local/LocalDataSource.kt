package com.fakhry.movie.data.source.local

import androidx.lifecycle.LiveData
import com.fakhry.movie.data.source.local.entity.MovieEntity
import com.fakhry.movie.data.source.local.entity.TvShowEntity
import com.fakhry.movie.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(movieDao)
            }
            return INSTANCE as LocalDataSource
        }
    }

    fun getAllMovies(): LiveData<List<MovieEntity>> = mMovieDao.getMovies()
//    fun insertMovies(movies: List<FavMovieEntity>) = mMovieDao.insertMovies(movies)
//    fun updateMovies(movie: FavMovieEntity) = mMovieDao.updateMovies(movie)

    fun getAllTvShows(): LiveData<List<TvShowEntity>> = mMovieDao.getTvShows()
//    fun insertTvShow(tvShows: List<FavTvShowEntity>) = mMovieDao.insertTvShows(tvShows)
//    fun updateTvShow(tvShow: FavTvShowEntity) = mMovieDao.updateTvShows(tvShow)

    fun insertListMovie(movies: List<MovieEntity>) {
        mMovieDao.insertMovies(movies)
    }

    fun insertListTvShow(tvShows: List<TvShowEntity>) {
        mMovieDao.insertTvShows(tvShows)
    }

    fun updateMovieDetails(movieDetails: MovieEntity) {
        mMovieDao.updateMovies(movieDetails)
    }

    fun updateTvShowDetails(tvShowDetails: TvShowEntity) {
        mMovieDao.updateTvShows(tvShowDetails)
    }

    fun setFavMovie(movie: MovieEntity, state: Boolean) {
        movie.isFavMovie = state
        mMovieDao.updateMovies(movie)
    }

    fun setFavTvShow(tvShow: TvShowEntity, state: Boolean) {
        tvShow.isFavTvShow = state
        mMovieDao.updateTvShows(tvShow)
    }

    fun getMovieDetails(movieId: Int): LiveData<MovieEntity> =
        mMovieDao.getMovieDetailsById(movieId)

    fun getTvShowDetails(tvShowId: Int): LiveData<TvShowEntity> =
        mMovieDao.getTvShowDetailsById(tvShowId)
}