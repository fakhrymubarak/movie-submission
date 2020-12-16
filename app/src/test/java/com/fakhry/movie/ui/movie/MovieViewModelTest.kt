package com.fakhry.movie.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.local.entity.MovieEntity
import com.fakhry.movie.utils.DataDummy
import com.fakhry.movie.vo.Resource
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
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<Resource<List<MovieEntity>>>

    @Before
    fun setUp(){
        movieViewModel = MovieViewModel(repository)
    }

    @Test
    fun getPopularMovies () {
        val dummyMovies = Resource.success(DataDummy.generateDummyMovie())
        val movies = MutableLiveData<Resource<List<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(repository.getPopularMovies()).thenReturn(movies)
        val moviesEntities = movieViewModel.getPopularMovies().value?.data
        verify(repository).getPopularMovies()

        assertNotNull(moviesEntities)
        assertEquals(10, moviesEntities?.size)

        movieViewModel.getPopularMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}