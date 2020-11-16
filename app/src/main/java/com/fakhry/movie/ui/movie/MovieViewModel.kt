package com.fakhry.movie.ui.movie

import androidx.lifecycle.ViewModel
import com.fakhry.movie.model.MovieAndTvShowEntity
import com.fakhry.movie.utils.DataDummy

class MovieViewModel : ViewModel() {
    fun getMovies() : List<MovieAndTvShowEntity> = DataDummy.generateDummyMovie()
}