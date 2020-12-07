//package com.fakhry.movie.ui.details
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
//class DetailsViewModelTest {
//    private lateinit var detailsViewModel: DetailsViewModel
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var applicationRepository: ApplicationRepository
//
//    @Mock
//    private lateinit var observer: Observer<MovieAndTvShowEntity>
//
//    private val dummyMovies = DataDummy.generateDummyMovie()[0]
//    private val movieId = dummyMovies.id
//
//    private val dummyTvShows = DataDummy.generateDummyTvShow()[0]
//    private val tvShowId = dummyTvShows.id
//
//    @Before
//    fun setUp() {
//        detailsViewModel = DetailsViewModel(applicationRepository)
//        detailsViewModel.setMovieSelected(movieId)
//        detailsViewModel.setTvShowSelected(tvShowId)
//    }
//
//    @Test
//    fun getDetailsMovies() {
//        val detailsMovie = MutableLiveData<MovieAndTvShowEntity>()
//        detailsMovie.value = dummyMovies
//
//        `when`(applicationRepository.getMovieDetails(movieId)).thenReturn(detailsMovie)
//        val movieEntity = detailsViewModel.getMovieDetails().value as MovieAndTvShowEntity
//        verify(applicationRepository).getMovieDetails(movieId)
//        assertNotNull(movieEntity)
//        assertEquals(dummyMovies.id, movieEntity.id)
//        assertEquals(dummyMovies.title, movieEntity.title)
//        assertEquals(dummyMovies.synopsis, movieEntity.synopsis)
//        assertEquals(dummyMovies.poster_url, movieEntity.poster_url)
//        assertEquals(dummyMovies.backdrop_url, movieEntity.backdrop_url)
//        assertEquals(dummyMovies.rating, movieEntity.rating, 0.0)
//
//        detailsViewModel.getMovieDetails().observeForever(observer)
//        verify(observer).onChanged(dummyMovies)
//    }
//
//    @Test
//    fun getDetailTvShows() {
//        val detailsTvShow = MutableLiveData<MovieAndTvShowEntity>()
//        detailsTvShow.value = dummyTvShows
//
//        `when`(applicationRepository.getTvShowDetails(tvShowId)).thenReturn(detailsTvShow)
//        val tvShowEntity = detailsViewModel.getTvShowDetails().value as MovieAndTvShowEntity
//        verify(applicationRepository).getTvShowDetails(tvShowId)
//
//        assertNotNull(tvShowEntity)
//        assertEquals(dummyTvShows.id, tvShowEntity.id)
//        assertEquals(dummyTvShows.title, tvShowEntity.title)
//        assertEquals(dummyTvShows.synopsis, tvShowEntity.synopsis)
//        assertEquals(dummyTvShows.poster_url, tvShowEntity.poster_url)
//        assertEquals(dummyTvShows.backdrop_url, tvShowEntity.backdrop_url)
//        assertEquals(dummyTvShows.rating, tvShowEntity.rating, 0.0)
//
//        detailsViewModel.getTvShowDetails().observeForever(observer)
//        verify(observer).onChanged(dummyTvShows)
//    }
//}