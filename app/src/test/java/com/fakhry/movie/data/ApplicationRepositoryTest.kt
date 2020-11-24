package com.fakhry.movie.data

import com.fakhry.movie.data.source.remote.RemoteDataSource
import com.fakhry.movie.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class ApplicationRepositoryTest {

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val applicationRepository = FakeApplicationRepository(remote)

    private val movieResponse = DataDummy.generateRemoteDummyMovies()
//    private val movieId = movieResponse[0].id


    @Test
    fun getAllMovies() {
        `when`(remote.getAllMovies()).thenReturn(movieResponse)
        val courseEntities = applicationRepository.getAllMovies()
        verify(remote).getAllMovies()
        assertNotNull(courseEntities)
        assertEquals(movieResponse.size.toLong(), courseEntities.size.toLong())
    }

//    @Test
//    fun getAllTvShows() {
//    }
//
//    @Test
//    fun getMovieDetails() {
//    }
//
//    @Test
//    fun getTvShowDetails() {
//    }
}