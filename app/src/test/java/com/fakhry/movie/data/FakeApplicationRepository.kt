package com.fakhry.movie.data

import com.fakhry.movie.data.source.local.entity.MovieAndTvShowEntity
import com.fakhry.movie.data.source.remote.RemoteDataSource

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
        val movieResponse = remoteDataSource.getAllTvShows()
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

    override fun getMovieDetails(movieId: Int): MovieAndTvShowEntity {
        val movieResponse = remoteDataSource.getMovieDetails(movieId)
        return MovieAndTvShowEntity(
            movieResponse.id,
            movieResponse.title,
            movieResponse.synopsis,
            movieResponse.poster_url,
            movieResponse.backdrop_url,
            movieResponse.rating
        )
    }

    override fun getTvShowDetails(tvShowId: Int): MovieAndTvShowEntity {
        val movieResponse = remoteDataSource.getTvShowDetails(tvShowId)
        return MovieAndTvShowEntity(
            movieResponse.id,
            movieResponse.title,
            movieResponse.synopsis,
            movieResponse.poster_url,
            movieResponse.backdrop_url,
            movieResponse.rating
        )
    }

}