package com.fakhry.movie.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.local.entity.TvShowEntity
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
class TvShowViewModelTest {
    private lateinit var tvShowViewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(repository)
    }

    @Test
    fun getTvShows() {
        val dummyTVShows = Resource.success(pagedList)
        `when`(dummyTVShows.data?.size).thenReturn(10)

        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTVShows

        `when`(repository.getPopularTvShows()).thenReturn(tvShows)
        val moviesEntities = tvShowViewModel.getPopularTvShows().value?.data
        verify(repository).getPopularTvShows()
        assertNotNull(moviesEntities)
        assertEquals(10, moviesEntities?.size)

        tvShowViewModel.getPopularTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTVShows)
    }
}