package com.fakhry.movie.data

import com.fakhry.movie.data.source.local.entity.MovieAndTvShowEntity
import com.fakhry.movie.data.source.remote.RemoteDataSource

class ApplicationRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    ApplicationDataSource {
    companion object {
        @Volatile
        private var instance: ApplicationRepository? = null
        fun getInstance(remoteData: RemoteDataSource): ApplicationRepository =
            instance ?: synchronized(this) {
                instance ?: ApplicationRepository(remoteData)
            }
    }

    override fun getAllMovies(): List<MovieAndTvShowEntity> {
        val moviesResponse = remoteDataSource.getAllMovies()
        val movieList = ArrayList<MovieAndTvShowEntity>()
        for (response in moviesResponse) {
            val movies = MovieAndTvShowEntity(
                response.id,
                response.title,
                response.synopsis,
                response.poster_url,
                response.backdrop_url,
                response.rating
            )
            movieList.add(movies)
        }
        return movieList
    }

    override fun getAllTvShows(): List<MovieAndTvShowEntity> {
        val moviesResponse = remoteDataSource.getAllTvShows()
        val tvShowList = ArrayList<MovieAndTvShowEntity>()
        for (response in moviesResponse) {
            val show = MovieAndTvShowEntity(
                response.id,
                response.title,
                response.synopsis,
                response.poster_url,
                response.backdrop_url,
                response.rating
            )
            tvShowList.add(show)
        }
        return tvShowList
    }

    override fun getMovieDetails(movieId: Int): MovieAndTvShowEntity {
        val moviesResponse = remoteDataSource.getAllMovies()
        lateinit var movieDetails: MovieAndTvShowEntity
        for (response in moviesResponse) {
            if (response.id == movieId) {
                movieDetails = MovieAndTvShowEntity(
                    response.id,
                    response.title,
                    response.synopsis,
                    response.poster_url,
                    response.backdrop_url,
                    response.rating
                )
            }
        }
        return movieDetails
    }

    override fun getTvShowDetails(tvShow: Int): MovieAndTvShowEntity {
        val tvShowResponse = remoteDataSource.getAllTvShows()
        lateinit var tvShowDetails: MovieAndTvShowEntity
        for (response in tvShowResponse) {
            if (response.id == tvShow) {
                tvShowDetails = MovieAndTvShowEntity(
                    response.id,
                    response.title,
                    response.synopsis,
                    response.poster_url,
                    response.backdrop_url,
                    response.rating
                )
            }
        }
        return tvShowDetails
    }
}