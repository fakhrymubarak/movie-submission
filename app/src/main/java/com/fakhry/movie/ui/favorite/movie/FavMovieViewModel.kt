package com.fakhry.movie.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.local.entity.MovieEntity

class FavMovieViewModel(private val repository: Repository) : ViewModel() {
    fun getFavMovies() : LiveData<List<MovieEntity>> = repository.getFavMovies()
}