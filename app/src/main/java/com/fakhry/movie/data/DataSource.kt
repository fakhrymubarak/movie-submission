package com.fakhry.movie.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.fakhry.movie.data.source.local.entity.MovieEntity
import com.fakhry.movie.data.source.local.entity.TvShowEntity
import com.fakhry.movie.vo.Resource

interface DataSource {

    fun getPopularMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getPopularTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getMovieDetails(movieId: Int): LiveData<Resource<MovieEntity>>
    fun getTvShowDetails(tvShowId: Int): LiveData<Resource<TvShowEntity>>

    fun getDbMovies(): DataSource.Factory<Int, MovieEntity>
    fun getDbTvShows(): DataSource.Factory<Int, TvShowEntity>
    fun getDbMovieDetails(movieId: Int): LiveData<MovieEntity>
    fun getDbTvShowDetails(tvShowId: Int): LiveData<TvShowEntity>

    fun setFavMovie(movie: MovieEntity, state: Boolean)
    fun setFavTvShow(tvShow: TvShowEntity, state: Boolean)

    fun getFavMovies(): LiveData<PagedList<MovieEntity>>
    fun getFavTvShows(): LiveData<PagedList<TvShowEntity>>
}