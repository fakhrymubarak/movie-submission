//package com.fakhry.movie.ui.tvshow
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.Observer
//import com.fakhry.movie.data.ApplicationRepository
//import com.fakhry.movie.data.source.local.entity.MovieAndTvShowEntity
//import com.fakhry.movie.utils.DataDummy
//import org.junit.Assert.assertEquals
//import org.junit.Assert.assertNotNull
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito.`when`
//import org.mockito.Mockito.verify
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
//class TvShowViewModelTest {
//    private lateinit var tvShowViewModel: TvShowViewModel
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var applicationRepository: ApplicationRepository
//
//    @Mock
//    private lateinit var observer: Observer<List<MovieAndTvShowEntity>>
//
//    @Before
//    fun setUp() {
//        tvShowViewModel = TvShowViewModel(applicationRepository)
//    }
//
//    @Test
//    fun getTvShows() {
//        val dummyTVShows = DataDummy.generateDummyTvShow()
//        val tvShows = MutableLiveData<List<MovieAndTvShowEntity>>()
//        tvShows.value = dummyTVShows
//
//        `when`(applicationRepository.getAllTvShows()).thenReturn(tvShows)
//        val moviesEntities = tvShowViewModel.getTvShow().value
//        verify(applicationRepository).getAllTvShows()
//        assertNotNull(moviesEntities)
//        assertEquals(10, moviesEntities?.size)
//
//        tvShowViewModel.getTvShow().observeForever(observer)
//        verify(observer).onChanged(dummyTVShows)
//    }
//}