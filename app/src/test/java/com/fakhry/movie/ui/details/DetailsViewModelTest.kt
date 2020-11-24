package com.fakhry.movie.ui.details

import com.fakhry.movie.data.ApplicationRepository
import com.fakhry.movie.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailsViewModelTest {
    private lateinit var detailsViewModel: DetailsViewModel

    @Mock
    private lateinit var applicationRepository: ApplicationRepository

    private val dummyMovies = DataDummy.generateDummyMovie()[0]
    private val movieId = dummyMovies.id

    private val dummyTvShows = DataDummy.generateDummyTvShow()[0]
    private val tvShowId = dummyTvShows.id

    @Before
    fun setUp() {
        detailsViewModel = DetailsViewModel(applicationRepository)
    }

    @Test
    fun getMovies() {
        `when`(applicationRepository.getMovieDetails(movieId)).thenReturn(dummyMovies)
        val movieEntity = detailsViewModel.getMovieDetails(movieId)
        verify(applicationRepository).getMovieDetails(movieId)

        assertNotNull(movieEntity)
        assertEquals(dummyMovies.id, movieEntity.id)
        assertEquals(dummyMovies.title, movieEntity.title)
        assertEquals(dummyMovies.synopsis, movieEntity.synopsis)
        assertEquals(dummyMovies.poster_url, movieEntity.poster_url)
        assertEquals(dummyMovies.backdrop_url, movieEntity.backdrop_url)
        assertEquals(dummyMovies.rating, movieEntity.rating, 0.0)
    }

    @Test
    fun getTvShows() {
        `when`(applicationRepository.getTvShowDetails(tvShowId)).thenReturn(dummyTvShows)
        val tvShowEntity = detailsViewModel.getTvShowDetails(tvShowId)
        verify(applicationRepository).getTvShowDetails(tvShowId)

        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShows.id, tvShowEntity.id)
        assertEquals(dummyTvShows.title, tvShowEntity.title)
        assertEquals(dummyTvShows.synopsis, tvShowEntity.synopsis)
        assertEquals(dummyTvShows.poster_url, tvShowEntity.poster_url)
        assertEquals(dummyTvShows.backdrop_url, tvShowEntity.backdrop_url)
        assertEquals(dummyTvShows.rating, tvShowEntity.rating, 0.0)
    }

}