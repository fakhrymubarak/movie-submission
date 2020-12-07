package com.fakhry.movie.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.remote.response.movie.details.MovieDetailsResponse
import com.fakhry.movie.data.source.remote.response.tvshow.details.TvShowDetailsResponse
import kotlin.properties.Delegates

class DetailsViewModel(private val repository: Repository) : ViewModel() {
    private var tvShowId by Delegates.notNull<Int>()
    private var movieId by Delegates.notNull<Int>()

    fun setMovieSelected(itemId: Int) {
        this.movieId = itemId
    }

    fun setTvShowSelected(itemId: Int) {
        this.tvShowId = itemId
    }

    fun getMovieDetails() : LiveData<MovieDetailsResponse> = repository.getMovieDetails(movieId)

//    fun getTvShowDetails() : LiveData<TvShowDetailsResponse> = repository.getTvShowDetails(tvShowId)
}