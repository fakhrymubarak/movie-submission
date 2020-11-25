package com.fakhry.movie.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.fakhry.movie.data.ApplicationRepository
import com.fakhry.movie.data.source.local.entity.MovieAndTvShowEntity
import com.fakhry.movie.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var applicationRepository: ApplicationRepository

    @Mock
    private lateinit var observer: Observer<List<MovieAndTvShowEntity>>

    @Before
    fun setUp(){
        movieViewModel = MovieViewModel(applicationRepository)
    }

    @Test
    fun getMovies () {
        val dummyMovies = DataDummy.generateDummyMovie()
        val movies = MutableLiveData<List<MovieAndTvShowEntity>>()

        `when`(applicationRepository.getAllMovies()).thenReturn(movies)
        val moviesEntities = movieViewModel.getMovies().value
        verify(applicationRepository).getAllMovies()
        assertNotNull(moviesEntities)
        assertEquals(10, moviesEntities?.size)

        movieViewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}