package com.fakhry.movie.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.local.entity.TvShowEntity

class FavTvShowViewModel(private val repository: Repository) : ViewModel() {
    fun getFavoriteTvShow() : LiveData<List<TvShowEntity>> = repository.getFavTvShows()
}