            package com.fakhry.movie.data.source.remote

import androidx.lifecycle.LiveData
import com.fakhry.movie.data.source.remote.response.movie.details.MovieDetailsResponse
import com.fakhry.movie.data.source.remote.response.movie.popular.MovieResponse
import com.fakhry.movie.data.source.remote.response.tvshow.details.TvShowDetailsResponse
import com.fakhry.movie.data.source.remote.response.tvshow.popular.TvShowResponse

interface RemoteDataSource {

    fun getPopularMovies() : LiveData<List<MovieResponse>>
    fun getPopularTvShows() : LiveData<List<TvShowResponse>>
    fun getMovieDetails(movieId : Int) : LiveData<MovieDetailsResponse>
    fun getTvShowDetails(tvShowId : Int) : LiveData<TvShowDetailsResponse>
}