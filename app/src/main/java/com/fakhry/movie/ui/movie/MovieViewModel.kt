package com.fakhry.movie.ui.movie

import androidx.lifecycle.ViewModel
import com.fakhry.movie.data.ApplicationRepository
import com.fakhry.movie.data.source.local.entity.MovieAndTvShowEntity
import com.fakhry.movie.utils.DataDummy

class MovieViewModel(private val applicationRepository: ApplicationRepository) : ViewModel() {
    fun getMovies() : List<MovieAndTvShowEntity> = applicationRepository.getAllMovies()
}