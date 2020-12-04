package com.fakhry.movie.data

import androidx.lifecycle.LiveData
import com.fakhry.movie.data.source.remote.response.movie.MovieResponse

interface DataSource {

    fun getPopularMovies() : LiveData<List<MovieResponse>>

//    fun getAllTvShows() : LiveData<List<MovieAndTvShowEntity>>
//
//    fun getMovieDetails(movieId : Int) : LiveData<MovieAndTvShowEntity>
//
//    fun getTvShowDetails(tvShowId : Int) : LiveData<MovieAndTvShowEntity>
}