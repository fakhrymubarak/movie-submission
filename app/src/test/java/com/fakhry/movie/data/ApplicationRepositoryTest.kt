package com.fakhry.movie.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fakhry.movie.data.source.remote.RemoteDataSource
import com.fakhry.movie.utils.DataDummy
import com.fakhry.movie.utils.LiveDataTestUtil

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.Rule
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.doAnswer

class ApplicationRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val applicationRepository = FakeApplicationRepository(remote)

    private val movieResponse = DataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponse[0].id
    private val movieDetails = DataDummy.generateRemoteDummyMovieDetails(movieId)


    private val tvShowResponse = DataDummy.generateRemoteDummyTvShows()
    private val tvShowId = tvShowResponse[0].id
    private val tvShowDetails = DataDummy.generateRemoteDummyTvShowDetails(tvShowId)

    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadAllMoviesCallback).onAllMoviesReceived(
                movieResponse)
            null
        }.`when`(remote).getAllMovies(any())
        val moviesEntities = LiveDataTestUtil.getValue(applicationRepository.getAllMovies())
        verify(remote).getAllMovies(any())
        assertNotNull(moviesEntities)
        assertEquals(movieResponse.size.toLong(), moviesEntities.size.toLong())
    }

    @Test
    fun getAllTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadAllTvShowsCallback).onAllTvShowReceived(
                tvShowResponse)
            null
        }.`when`(remote).getAllTvShows(any())
        val tvShowsEntities = LiveDataTestUtil.getValue(applicationRepository.getAllTvShows())
        verify(remote).getAllTvShows(any())
        assertNotNull(tvShowsEntities)
        assertEquals(tvShowResponse.size.toLong(), tvShowsEntities.size.toLong())
    }

    @Test
    fun getMovieDetails() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMovieDetailsCallback).onMovieDetailsReceived(
                movieDetails)
            null
        }.`when`(remote).getMovieDetails(eq(movieId), any())
        val movieDetailsEntities =
            LiveDataTestUtil.getValue(applicationRepository.getMovieDetails(movieId))
        verify(remote).getMovieDetails(eq(movieId), any())
        assertNotNull(movieDetailsEntities)
        assertEquals(movieResponse[0].title, movieDetailsEntities.title)
        assertEquals(movieResponse[0].synopsis, movieDetailsEntities.synopsis)
        assertEquals(movieResponse[0].poster_url, movieDetailsEntities.poster_url)
        assertEquals(movieResponse[0].backdrop_url, movieDetailsEntities.backdrop_url)
    }

    @Test
    fun getTvShowDetails() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowDetailsCallback).onTvShowDetailReceived(
                tvShowDetails)
            null
        }.`when`(remote).getTvShowDetails(eq(tvShowId), any())
        val tvShowDetailsEntity =
            LiveDataTestUtil.getValue(applicationRepository.getMovieDetails(tvShowId))
        verify(remote).getTvShowDetails(eq(tvShowId), any())
        assertNotNull(tvShowDetailsEntity)
        assertEquals(tvShowResponse[0].title, tvShowDetailsEntity.title)
        assertEquals(tvShowResponse[0].synopsis, tvShowDetailsEntity.synopsis)
        assertEquals(tvShowResponse[0].poster_url, tvShowDetailsEntity.poster_url)
        assertEquals(tvShowResponse[0].backdrop_url, tvShowDetailsEntity.backdrop_url)
    }
}