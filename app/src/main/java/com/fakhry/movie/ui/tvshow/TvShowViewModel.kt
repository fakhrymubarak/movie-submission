package com.fakhry.movie.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.remote.response.tvshow.popular.TvShowResponse

class TvShowViewModel(private val repository: Repository) : ViewModel() {
    fun getPopularTvShows(): LiveData<List<TvShowResponse>> = repository.getPopularTvShows()
}