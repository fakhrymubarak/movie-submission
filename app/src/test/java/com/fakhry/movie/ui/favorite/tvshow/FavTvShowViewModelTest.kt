package com.fakhry.movie.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.local.entity.TvShowEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavTvShowViewModelTest {
    private lateinit var favTvShowViewModel: FavTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp (){
        favTvShowViewModel = FavTvShowViewModel(repository)
    }
    @Test
    fun getFavoriteTvShow() {
        val dummyFavTvShow = pagedList
        Mockito.`when`(dummyFavTvShow.size).thenReturn(1)

        val favTvShows = MutableLiveData<PagedList<TvShowEntity>>()
        favTvShows.value = dummyFavTvShow

        Mockito.`when`(repository.getFavTvShows()).thenReturn(favTvShows)
        val moviesEntities = favTvShowViewModel.getFavoriteTvShow().value
        Mockito.verify(repository).getFavTvShows()
        Assert.assertNotNull(moviesEntities)
        Assert.assertEquals(1, moviesEntities?.size)

        favTvShowViewModel.getFavoriteTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyFavTvShow)
    }
}