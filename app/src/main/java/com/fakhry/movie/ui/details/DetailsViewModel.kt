package com.fakhry.movie.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.local.entity.MovieEntity
import com.fakhry.movie.data.source.local.entity.TvShowEntity
import com.fakhry.movie.vo.Resource
import kotlin.properties.Delegates

class DetailsViewModel(private val repository: Repository) : ViewModel() {
    private var tvShowId by Delegates.notNull<Int>()
    private var movieId by Delegates.notNull<Int>()

    fun setMovieSelected(itemId: Int) {
        this.movieId = itemId
    }

    fun setTvShowSelected(itemId: Int) {
        this.tvShowId = itemId
    }

    fun getMovieDetails(): LiveData<Resource<MovieEntity>> = repository.getMovieDetails(movieId)

    fun getTvShowDetails(): LiveData<Resource<TvShowEntity>> = repository.getTvShowDetails(tvShowId)

    fun setFavMovie() {
//        val movie = getMovieDetails().value
//        if (movie != null)
//        val newState = !courseEntity.bookmarked
//        repository.setFavMovie(courseEntity, newState)


    }
}