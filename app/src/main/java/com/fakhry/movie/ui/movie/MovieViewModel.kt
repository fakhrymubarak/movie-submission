package com.fakhry.movie.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.local.entity.MovieEntity
import com.fakhry.movie.vo.Resource

class MovieViewModel(private val repository: Repository) : ViewModel() {
    fun getPopularMovies() : LiveData<Resource<PagedList<MovieEntity>>> = repository.getPopularMovies()
}