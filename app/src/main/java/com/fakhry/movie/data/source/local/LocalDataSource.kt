package com.fakhry.movie.data.source.local

import androidx.lifecycle.LiveData
import com.fakhry.movie.data.source.local.entity.FavMovieEntity
import com.fakhry.movie.data.source.local.entity.FavTvShowEntity
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

    fun getAllMovies(): LiveData<List<FavMovieEntity>> = mMovieDao.getMovies()
    fun insertMovies(movies: List<FavMovieEntity>) = mMovieDao.insertMovies(movies)
    fun updateMovies(movie: FavMovieEntity) = mMovieDao.updateMovies(movie)

    fun getAllTvShows(): LiveData<List<FavTvShowEntity>> = mMovieDao.getTvShows()
    fun insertTvShow(tvShows: List<FavTvShowEntity>) = mMovieDao.insertTvShows(tvShows)
    fun updateTvShow(tvShow: FavTvShowEntity) = mMovieDao.updateTvShows(tvShow)
}