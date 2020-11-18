package com.fakhry.movie.data

import com.fakhry.movie.data.source.local.entity.MovieAndTvShowEntity

interface ApplicationDataSource {

    fun getAllMovies() : List<MovieAndTvShowEntity>

    fun getAllTvShows() : List<MovieAndTvShowEntity>

    fun getMovieDetails(movieId : Int) : MovieAndTvShowEntity

    fun getTvShowDetails(tvShowId : Int) : MovieAndTvShowEntity
}