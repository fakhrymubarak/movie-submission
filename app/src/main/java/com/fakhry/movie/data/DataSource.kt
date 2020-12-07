package com.fakhry.movie.data

import androidx.lifecycle.LiveData
import com.fakhry.movie.data.source.remote.response.movie.details.MovieDetailsResponse
import com.fakhry.movie.data.source.remote.response.movie.popular.MovieResponse
import com.fakhry.movie.data.source.remote.response.tvshow.popular.TvShowResponse

interface DataSource {

    fun getPopularMovies() : LiveData<List<MovieResponse>>
    fun getPopularTvShows() : LiveData<List<TvShowResponse>>
    fun getMovieDetails(movieId : Int) : LiveData<MovieDetailsResponse>
//
//    fun getTvShowDetails(tvShowId : Int) : LiveData<MovieAndTvShowEntity>
}