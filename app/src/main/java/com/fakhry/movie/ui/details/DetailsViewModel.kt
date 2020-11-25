package com.fakhry.movie.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fakhry.movie.data.ApplicationRepository
import com.fakhry.movie.data.source.local.entity.MovieAndTvShowEntity
import kotlin.properties.Delegates

class DetailsViewModel(private val applicationRepository: ApplicationRepository) : ViewModel() {
    private var tvShowId by Delegates.notNull<Int>()
    private var movieId by Delegates.notNull<Int>()

    fun setMovieSelected(itemId: Int) {
        this.movieId = itemId
    }

    fun setTvShowSelected(itemId: Int) {
        this.tvShowId = itemId
    }

    fun getMovieDetails() : LiveData<MovieAndTvShowEntity> = applicationRepository.getMovieDetails(movieId)

    fun getTvShowDetails() : LiveData<MovieAndTvShowEntity> = applicationRepository.getTvShowDetails(tvShowId)
}