package com.fakhry.movie.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fakhry.movie.data.ApplicationRepository
import com.fakhry.movie.data.source.local.entity.MovieAndTvShowEntity
import kotlin.properties.Delegates

class DetailsViewModel(private val applicationRepository: ApplicationRepository) : ViewModel() {
    private var itemId by Delegates.notNull<Int>()

    fun setSelectedItem(itemId: Int) {
        this.itemId = itemId
    }

    fun getTvShowDetails(tvShowId : Int) : LiveData<MovieAndTvShowEntity> = applicationRepository.getTvShowDetails(tvShowId)

    fun getMovieDetails(movieId : Int) : LiveData<MovieAndTvShowEntity> = applicationRepository.getMovieDetails(movieId)
}