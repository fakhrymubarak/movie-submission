package com.fakhry.movie.ui.tvshow

import androidx.lifecycle.ViewModel
import com.fakhry.movie.data.ApplicationRepository
import com.fakhry.movie.data.source.local.entity.MovieAndTvShowEntity

class TvShowViewModel (private val applicationRepository: ApplicationRepository): ViewModel() {
    fun getTvShow() : List<MovieAndTvShowEntity> = applicationRepository.getAllTvShows()

}