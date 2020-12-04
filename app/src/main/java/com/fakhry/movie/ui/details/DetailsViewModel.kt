package com.fakhry.movie.ui.details

import androidx.lifecycle.ViewModel
import com.fakhry.movie.data.Repository
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

//    fun getMovieDetails() : LiveData<MovieAndTvShowEntity> = repository.getMovieDetails(movieId)

//    fun getTvShowDetails() : LiveData<MovieAndTvShowEntity> = repository.getTvShowDetails(tvShowId)
}