package com.fakhry.movie.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fakhry.movie.data.source.remote.RemoteRepository
import com.fakhry.movie.utils.DataDummy
import com.fakhry.movie.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteRepository::class.java)
    private val repository = Repository(remote)

    private val movieResponse = DataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponse[0].id
    private val movieDetails = DataDummy.generateRemoteDummyMovieDetails(movieId)


    private val tvShowResponse = DataDummy.generateRemoteDummyTvShows()
    private val tvShowId = tvShowResponse[0].id
    private val tvShowDetails = DataDummy.generateRemoteDummyTvShowDetails(tvShowId)

    @Test
    fun getPopularMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteRepository.LoadAllMoviesCallback).onAllMoviesReceived(
                movieResponse)
            null
        }.`when`(remote).getPopularMovies(any())
        val moviesEntities = LiveDataTestUtil.getValue(repository.getPopularMovies())
        verify(remote).getPopularMovies(any())
        assertNotNull(moviesEntities)
        assertEquals(movieResponse.size.toLong(), moviesEntities.size.toLong())
    }

    @Test
    fun getPopularTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteRepository.LoadAllTvShowsCallback).onAllTvShowReceived(
                tvShowResponse)
            null
        }.`when`(remote).getPopularTvShows(any())
        val moviesEntities = LiveDataTestUtil.getValue(repository.getPopularTvShows())
        verify(remote).getPopularTvShows(any())
        assertNotNull(moviesEntities)
        assertEquals(movieResponse.size.toLong(), moviesEntities.size.toLong())
    }

    @Test
    fun getMovieDetails() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteRepository.LoadMovieDetailsCallback).onMovieDetailsReceived(
                movieDetails)
            null
        }.`when`(remote).getMovieDetails(eq(movieId), any())
        val movieDetailsEntities =
            LiveDataTestUtil.getValue(repository.getMovieDetails(movieId))
        verify(remote).getMovieDetails(eq(movieId), any())
        assertNotNull(movieDetailsEntities)
        assertEquals(movieResponse[0].title, movieDetailsEntities.title)
        assertEquals(movieResponse[0].overview, movieDetailsEntities.overview)
        assertEquals(movieResponse[0].posterPath, movieDetailsEntities.posterPath)
        assertEquals(movieResponse[0].backdropPath, movieDetailsEntities.backdropPath)
        assertEquals(movieResponse[0].voteAverage, movieDetailsEntities.voteAverage)
    }

    @Test
    fun getTvShowDetails() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteRepository.LoadTvShowDetailsCallback).onTvShowDetailReceived(
                tvShowDetails)
            null
        }.`when`(remote).getTvShowDetails(eq(tvShowId), any())
        val tvShowDetailsEntity =
            LiveDataTestUtil.getValue(repository.getTvShowDetails(tvShowId))
        verify(remote).getTvShowDetails(eq(tvShowId), any())
        assertNotNull(tvShowDetailsEntity)
        assertEquals(tvShowResponse[0].name, tvShowDetailsEntity.name)
        assertEquals(tvShowResponse[0].overview, tvShowDetailsEntity.overview)
        assertEquals(tvShowResponse[0].posterPath, tvShowDetailsEntity.posterPath)
        assertEquals(tvShowResponse[0].backdropPath, tvShowDetailsEntity.backdropPath)
        assertEquals(tvShowResponse[0].voteAverage, tvShowDetailsEntity.voteAverage)
    }
}