package com.fakhry.movie.data

import androidx.lifecycle.LiveData
import com.fakhry.movie.data.source.local.entity.MovieAndTvShowEntity

interface ApplicationDataSource {

    fun getAllMovies() : LiveData<List<MovieAndTvShowEntity>>

    fun getAllTvShows() : LiveData<List<MovieAndTvShowEntity>>

    fun getMovieDetails(movieId : Int) : LiveData<MovieAndTvShowEntity>

    fun getTvShowDetails(tvShowId : Int) : LiveData<MovieAndTvShowEntity>
}