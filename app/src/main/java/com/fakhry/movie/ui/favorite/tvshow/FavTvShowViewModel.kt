package com.fakhry.movie.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.local.entity.TvShowEntity

class FavTvShowViewModel(private val repository: Repository) : ViewModel() {
    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> = repository.getFavTvShows()

    fun setFavTvShow(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.isFavTvShow
        repository.setFavTvShow(tvShowEntity, newState)
    }
}