package com.fakhry.movie.data

import com.fakhry.movie.data.source.local.entity.MovieAndTvShowEntity
import com.fakhry.movie.data.source.remote.RemoteDataSource
import org.junit.Test

class FakeApplicationRepository(private val remoteDataSource: RemoteDataSource) :
    ApplicationDataSource {

    override fun getAllMovies(): List<MovieAndTvShowEntity> {
        val movieResponse = remoteDataSource.getAllMovies()
        val movieList = ArrayList<MovieAndTvShowEntity>()
        for (response in movieResponse) {
            val movie = MovieAndTvShowEntity(
                response.id,
                response.title,
                response.synopsis,
                response.poster_url,
                response.backdrop_url,
                response.rating
            )
            movieList.add(movie)
        }
        return movieList
    }

    override fun getAllTvShows(): List<MovieAndTvShowEntity> {
        TODO("Not yet implemented")
    }

    override fun getMovieDetails(movieId: Int): MovieAndTvShowEntity {
        TODO("Not yet implemented")
    }

    override fun getTvShowDetails(tvShowId: Int): MovieAndTvShowEntity {
        TODO("Not yet implemented")
    }

    @Test
    fun getMovieDetails() {
    }

    @Test
    fun getTvShowDetails() {
    }
}