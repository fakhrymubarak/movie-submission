package com.fakhry.movie.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.local.entity.MovieEntity
import com.fakhry.movie.data.source.local.entity.TvShowEntity
import com.fakhry.movie.vo.Resource

class DetailsViewModel(private val repository: Repository) : ViewModel() {
    private var movieId: Int = 0
    private var tvShowId: Int = 0

    fun setMovieSelected(itemId: Int) {
        this.movieId = itemId
    }

    fun setTvShowSelected(itemId: Int) {
        this.tvShowId = itemId
    }

    fun getMovieDetails(): LiveData<Resource<MovieEntity>> =
        repository.getMovieDetails(movieId)

    fun getTvShowDetails(): LiveData<Resource<TvShowEntity>> =
        repository.getTvShowDetails(tvShowId)


    fun setFavMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.isFavMovie
        repository.setFavMovie(movieEntity, newState)
    }

    fun setFavTvShow(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.isFavTvShow
        repository.setFavTvShow(tvShowEntity, newState)
    }
}