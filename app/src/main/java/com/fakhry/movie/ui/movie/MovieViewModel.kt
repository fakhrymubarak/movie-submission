package com.fakhry.movie.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fakhry.movie.data.ApplicationRepository
import com.fakhry.movie.data.source.local.entity.MovieAndTvShowEntity

class MovieViewModel(private val applicationRepository: ApplicationRepository) : ViewModel() {
    fun getMovies() : LiveData<List<MovieAndTvShowEntity>> = applicationRepository.getAllMovies()
}