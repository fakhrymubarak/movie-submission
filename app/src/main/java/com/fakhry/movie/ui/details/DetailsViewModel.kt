package com.fakhry.movie.ui.details

import androidx.lifecycle.ViewModel
import com.fakhry.movie.data.ApplicationRepository
import com.fakhry.movie.data.source.local.entity.MovieAndTvShowEntity
import kotlin.properties.Delegates

class DetailsViewModel(private val applicationRepository: ApplicationRepository) : ViewModel() {
    private var itemId by Delegates.notNull<Int>()

    fun setSelectedItem(itemId: Int) {
        this.itemId = itemId
    }

    fun getMovieDetails(movieId : Int) : MovieAndTvShowEntity = applicationRepository.getMovieDetails(movieId)


//    fun getMovies(): MovieAndTvShowEntity {
//        lateinit var movie: MovieAndTvShowEntity
//        val moviesEntities = DataDummy.generateDummyMovie()
//        for (movieEntity in moviesEntities) {
//            if (movieEntity.id == itemId) {
//                movie = movieEntity
//            }
//        }
//         return movie
//    }

    fun getTvShowDetails(tvShowId : Int) : MovieAndTvShowEntity = applicationRepository.getTvShowDetails(tvShowId)

//    fun getTvShows(): MovieAndTvShowEntity {
//        lateinit var tvShows: MovieAndTvShowEntity
//        val tvShowsEntities = DataDummy.generateDummyTvShow()
//        for (tvShow in tvShowsEntities) {
//            if (tvShow.id == itemId) {
//                tvShows = tvShow
//            }
//        }
//        return tvShows
//    }
}