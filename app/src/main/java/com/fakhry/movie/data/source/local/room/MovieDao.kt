package com.fakhry.movie.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.fakhry.movie.data.source.local.entity.MovieEntity
import com.fakhry.movie.data.source.local.entity.TvShowEntity

@Dao
interface MovieDao {

//    @Query("SELECT * FROM movie_entities")
//    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie_entities")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_show_entity")
    fun getTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update
    fun updateMovies(movies: MovieEntity)

    @Update
    fun updateTvShows(tvShows: TvShowEntity)

    @Query("SELECT * FROM movie_entities WHERE id_movie = :movieId")
    fun getMovieDetailsById(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM tv_show_entity WHERE id_tv_show = :tvShowId")
    fun getTvShowDetailsById(tvShowId: Int): LiveData<TvShowEntity>

    @Query("SELECT * FROM movie_entities WHERE is_fav_movie = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_show_entity WHERE is_fav_tv_show = 1")
    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity>
}