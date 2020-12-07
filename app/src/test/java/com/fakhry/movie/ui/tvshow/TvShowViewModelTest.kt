package com.fakhry.movie.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.remote.response.tvshow.popular.TvShowResponse
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
class TvShowViewModelTest {
    private lateinit var tvShowViewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<List<TvShowResponse>>

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(repository)
    }

    @Test
    fun getTvShows() {
        val dummyTVShows = DataDummy.generateDummyTvShow()
        val tvShows = MutableLiveData<List<TvShowResponse>>()
        tvShows.value = dummyTVShows

        `when`(repository.getPopularTvShows()).thenReturn(tvShows)
        val moviesEntities = tvShowViewModel.getPopularTvShows().value
        verify(repository).getPopularTvShows()
        assertNotNull(moviesEntities)
        assertEquals(10, moviesEntities?.size)

        tvShowViewModel.getPopularTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTVShows)
    }
}