package com.fakhry.movie.ui.tvshow

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
class TvShowViewModelTest {
    private lateinit var tvShowViewModel: TvShowViewModel

    @Mock
    private lateinit var applicationRepository: ApplicationRepository

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(applicationRepository)
    }

    @Test
    fun getTvShows() {
        `when`(applicationRepository.getAllTvShows()).thenReturn(
            DataDummy.generateDummyTvShow())
        val tvShowEntities = tvShowViewModel.getTvShow()
        verify(applicationRepository).getAllTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities.size)
    }
}