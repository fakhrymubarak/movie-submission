package com.fakhry.movie.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.remote.response.movie.MovieResponse

class MovieViewModel(private val repository: Repository) : ViewModel() {
    fun getPopularMovies() : LiveData<List<MovieResponse>> = repository.getPopularMovies()
}