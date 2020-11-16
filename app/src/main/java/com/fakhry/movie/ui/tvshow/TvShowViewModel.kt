package com.fakhry.movie.ui.tvshow

import androidx.lifecycle.ViewModel
import com.fakhry.movie.model.MovieAndTvShowEntity
import com.fakhry.movie.utils.DataDummy

class TvShowViewModel : ViewModel() {
    fun getTvShow() : List<MovieAndTvShowEntity> = DataDummy.generateDummyMovie()

}