package com.fakhry.movie.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.local.entity.MovieEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavMovieViewModelTest {

    private lateinit var favMovieViewModel: FavMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp (){
        favMovieViewModel = FavMovieViewModel(repository)
    }
    @Test
    fun getFavMovies() {
        val dummyFavMovies = pagedList
        `when`(dummyFavMovies.size).thenReturn(1)

        val favMovies = MutableLiveData<PagedList<MovieEntity>>()
        favMovies.value = dummyFavMovies

        `when`(repository.getFavMovies()).thenReturn(favMovies)
        val moviesEntities = favMovieViewModel.getFavMovies().value
        verify(repository).getFavMovies()
        Assert.assertNotNull(moviesEntities)
        Assert.assertEquals(1, moviesEntities?.size)

        favMovieViewModel.getFavMovies().observeForever(observer)
        verify(observer).onChanged(dummyFavMovies)
    }
}