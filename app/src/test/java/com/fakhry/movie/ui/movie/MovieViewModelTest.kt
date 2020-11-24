package com.fakhry.movie.ui.movie

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
class MovieViewModelTest {
    private lateinit var movieViewModel: MovieViewModel

    @Mock
    private lateinit var applicationRepository: ApplicationRepository

    @Before
    fun setUp(){
        movieViewModel = MovieViewModel(applicationRepository)
    }

    @Test
    fun getMovies () {
        `when`(applicationRepository.getAllMovies()).thenReturn(
            DataDummy.generateDummyMovie())
        val moviesEntities = movieViewModel.getMovies()
        verify(applicationRepository).getAllMovies()
        assertNotNull(moviesEntities)
        assertEquals(10, moviesEntities.size)
    }
}