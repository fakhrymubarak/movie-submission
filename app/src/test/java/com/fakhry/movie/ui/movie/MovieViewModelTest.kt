package com.fakhry.movie.ui.movie

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {
    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun setUp(){
        movieViewModel = MovieViewModel()
    }

    @Test
    fun getMovies (){
        val moviesEntities = movieViewModel.getMovies()
        assertNotNull(moviesEntities)
        assertEquals(10, moviesEntities.size)
    }
}