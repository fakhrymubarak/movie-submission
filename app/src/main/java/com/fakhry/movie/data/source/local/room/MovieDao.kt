package com.fakhry.movie.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fakhry.movie.data.source.local.entity.FavMovieEntity
import com.fakhry.movie.data.source.local.entity.FavTvShowEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM fav_mov_entities")
    fun getMovies(): LiveData<List<FavMovieEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<FavMovieEntity>)

    @Update
    fun updateMovies(movies: FavMovieEntity)

    @Query("SELECT * FROM fav_tvshow_entities")
    fun getTvShows(): LiveData<List<FavTvShowEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<FavTvShowEntity>)

    @Update
    fun updateTvShows(tvShows: FavTvShowEntity)
}