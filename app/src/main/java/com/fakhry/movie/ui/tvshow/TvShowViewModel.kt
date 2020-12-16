package com.fakhry.movie.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.local.entity.TvShowEntity
import com.fakhry.movie.vo.Resource

class TvShowViewModel(private val repository: Repository) : ViewModel() {
    fun getPopularTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> = repository.getPopularTvShows()
}