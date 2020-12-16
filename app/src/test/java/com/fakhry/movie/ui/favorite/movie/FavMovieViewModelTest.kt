package com.fakhry.movie.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.local.entity.MovieEntity
import com.fakhry.movie.utils.DataDummy
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavMovieViewModelTest {

    private lateinit var favMovieViewModel: FavMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp (){
        favMovieViewModel = FavMovieViewModel(repository)
    }
    @Test
    fun getFavMovies() {
        val dummyFavMovies = DataDummy.generateFavoriteMovie()
        val favTvShows = MutableLiveData<List<MovieEntity>>()
        favTvShows.value = dummyFavMovies

        Mockito.`when`(repository.getFavMovies()).thenReturn(favTvShows)
        val moviesEntities = favMovieViewModel.getFavMovies().value
        Mockito.verify(repository).getFavMovies()
        Assert.assertNotNull(moviesEntities)
        Assert.assertEquals(1, moviesEntities?.size)

        favMovieViewModel.getFavMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyFavMovies)
    }
}