package com.fakhry.movie.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.local.entity.MovieEntity
import com.fakhry.movie.data.source.local.entity.TvShowEntity
import com.fakhry.movie.vo.Resource

class DetailsViewModel(private val repository: Repository) : ViewModel() {
    private var tvShowId = MutableLiveData<Int>()
    private var movieId = MutableLiveData<Int>()

    fun setMovieSelected(itemId: Int) {
        this.movieId.value = itemId
    }

    fun setTvShowSelected(itemId: Int) {
        this.tvShowId.value = itemId
    }

    fun getMovieDetails(): LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(movieId) { mMovieId ->
            repository.getMovieDetails(mMovieId)
        }

    fun getTvShowDetails(): LiveData<Resource<TvShowEntity>> =
        Transformations.switchMap(tvShowId) { mTvShowId ->
            repository.getTvShowDetails(mTvShowId)
        }

    fun setFavMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.isFavMovie
        repository.setFavMovie(movieEntity, newState)
    }

    fun setFavTvShow(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.isFavTvShow
        repository.setFavTvShow(tvShowEntity, newState)
    }
}













