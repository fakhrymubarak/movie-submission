package com.fakhry.movie.ui.tvshow

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {
    private lateinit var tvShowViewModel: TvShowViewModel

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel()
    }

    @Test
    fun getTvShows() {
        val moviesEntities = tvShowViewModel.getTvShow()
        assertNotNull(moviesEntities)
        assertEquals(10, moviesEntities.size)
    }
}
