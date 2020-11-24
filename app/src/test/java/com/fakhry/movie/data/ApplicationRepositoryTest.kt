package com.fakhry.movie.data

import com.fakhry.movie.data.source.remote.RemoteDataSource
import com.fakhry.movie.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class ApplicationRepositoryTest {

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val applicationRepository = FakeApplicationRepository(remote)

    private val movieResponse = DataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponse[0].id
    private val movieDetails = DataDummy.generateRemoteDummyMovieDetails(movieId)


    private val tvShowResponse = DataDummy.generateRemoteDummyTvShows()
    private val tvShowId = tvShowResponse[0].id
    private val tvShowDetails = DataDummy.generateRemoteDummyTvShowDetails(tvShowId)

    @Test
    fun getAllMovies() {
        `when`(remote.getAllMovies()).thenReturn(movieResponse)
        val moviesEntities = applicationRepository.getAllMovies()
        verify(remote).getAllMovies()
        assertNotNull(moviesEntities)
        assertEquals(movieResponse.size.toLong(), moviesEntities.size.toLong())
    }

    @Test
    fun getAllTvShows() {
        `when`(remote.getAllTvShows()).thenReturn(tvShowResponse)
        val tvShowsEntities = applicationRepository.getAllTvShows()
        verify(remote).getAllTvShows()
        assertNotNull(tvShowsEntities)
        assertEquals(tvShowResponse.size.toLong(), tvShowsEntities.size.toLong())
    }

    @Test
    fun getMovieDetails() {
        `when`(remote.getMovieDetails(movieId)).thenReturn(movieDetails)
        val movieDetailsEntities = applicationRepository.getMovieDetails(movieId)
        verify(remote).getMovieDetails(movieId)
        assertNotNull(movieDetailsEntities)
        assertEquals(movieResponse[0].title, movieDetailsEntities.title)
        assertEquals(movieResponse[0].synopsis, movieDetailsEntities.synopsis)
        assertEquals(movieResponse[0].poster_url, movieDetailsEntities.poster_url)
        assertEquals(movieResponse[0].backdrop_url, movieDetailsEntities.backdrop_url)
    }

    @Test
    fun getTvShowDetails() {
        `when`(remote.getTvShowDetails(tvShowId)).thenReturn(tvShowDetails)
        val tvShowDetailsEntity = applicationRepository.getTvShowDetails(tvShowId)
        verify(remote).getTvShowDetails(tvShowId)
        assertNotNull(tvShowDetailsEntity)
        assertEquals(tvShowResponse[0].title, tvShowDetailsEntity.title)
        assertEquals(tvShowResponse[0].synopsis, tvShowDetailsEntity.synopsis)
        assertEquals(tvShowResponse[0].poster_url, tvShowDetailsEntity.poster_url)
        assertEquals(tvShowResponse[0].backdrop_url, tvShowDetailsEntity.backdrop_url)
    }
}