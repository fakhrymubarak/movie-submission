package com.fakhry.movie.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
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

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = mMovieDao.getMovies()

    fun getAllTvShows(): DataSource.Factory<Int, TvShowEntity> = mMovieDao.getTvShows()

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

    fun getFavMovies(): DataSource.Factory<Int, MovieEntity> =
        mMovieDao.getFavoriteMovies()

    fun getFavTvShows(): DataSource.Factory<Int, TvShowEntity> =
        mMovieDao.getFavoriteTvShows()

}