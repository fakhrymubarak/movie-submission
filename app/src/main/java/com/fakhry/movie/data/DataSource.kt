package com.fakhry.movie.data

import androidx.lifecycle.LiveData
import com.fakhry.movie.data.source.local.entity.MovieEntity
import com.fakhry.movie.data.source.local.entity.TvShowEntity
import com.fakhry.movie.vo.Resource

interface DataSource {

    fun getPopularMovies():  LiveData<Resource<List<MovieEntity>>>
    fun getPopularTvShows():LiveData<Resource<List<TvShowEntity>>>
    fun getMovieDetails(movieId: Int): LiveData<Resource<MovieEntity>>
    fun getTvShowDetails(tvShowId: Int): LiveData<Resource<TvShowEntity>>

    fun getDbMovies(): LiveData<List<MovieEntity>>
    fun getDbTvShows(): LiveData<List<TvShowEntity>>
    fun getDbMovieDetails(movieId: Int): LiveData<MovieEntity>
    fun getDbTvShowDetails(tvShowId: Int): LiveData<TvShowEntity>

    fun setFavMovie(movie: MovieEntity, state: Boolean)
    fun setFavTvShow(tvShow: TvShowEntity, state: Boolean)
}