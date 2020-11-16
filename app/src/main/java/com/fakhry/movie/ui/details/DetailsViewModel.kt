package com.fakhry.movie.ui.details

import androidx.lifecycle.ViewModel
import com.fakhry.movie.model.MovieAndTvShowEntity
import com.fakhry.movie.utils.DataDummy
import kotlin.properties.Delegates

class DetailsViewModel : ViewModel() {
    private var itemId by Delegates.notNull<Int>()

    fun setSelectedItem(itemId: Int) {
        this.itemId = itemId
    }

    fun getMovies(): MovieAndTvShowEntity {
        lateinit var movie: MovieAndTvShowEntity
        val moviesEntities = DataDummy.generateDummyMovie()
        for (movieEntity in moviesEntities) {
            if (movieEntity.id == itemId) {
                movie = movieEntity
            }
        }
        return movie
    }

    fun getTvShows(): MovieAndTvShowEntity {
        lateinit var tvShows: MovieAndTvShowEntity
        val tvShowsEntities = DataDummy.generateDummyTvShow()
        for (tvShow in tvShowsEntities) {
            if (tvShow.id == itemId) {
                tvShows = tvShow
            }
        }
        return tvShows
    }
}