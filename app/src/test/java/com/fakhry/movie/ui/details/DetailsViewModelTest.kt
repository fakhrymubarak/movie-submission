package com.fakhry.movie.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.local.entity.MovieEntity
import com.fakhry.movie.data.source.local.entity.TvShowEntity
import com.fakhry.movie.utils.DataDummy
import com.fakhry.movie.vo.Resource
import com.nhaarman.mockitokotlin2.any
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
class DetailsViewModelTest {
    private lateinit var detailsViewModel: DetailsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var movieDetailsObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowDetailsObserver: Observer<Resource<TvShowEntity>>

    private val dummyMovies = Resource.success(DataDummy.generateDummyMovie()[1])
    private val movieId = dummyMovies.data!!.movieId

    private val dummyTvShows = Resource.success(DataDummy.generateDummyTvShow()[1])
    private val tvShowId = dummyTvShows.data!!.tvShowId

    @Before
    fun setUp() {
        detailsViewModel = DetailsViewModel(repository)
        detailsViewModel.setMovieSelected(movieId)
        detailsViewModel.setTvShowSelected(tvShowId)
    }

    @Test
    fun getDetailsMovies() {
        val detailsMovie = MutableLiveData<Resource<MovieEntity>>()
        detailsMovie.value = dummyMovies

        `when`(repository.getMovieDetails(any())).thenReturn(detailsMovie)
        val movieEntity = detailsViewModel.getMovieDetails().value?.data
        verify(repository).getMovieDetails(movieId)

        assertNotNull(movieEntity)
        assertEquals(dummyMovies.data?.movieId, movieEntity?.movieId)
        assertEquals(dummyMovies.data?.title, movieEntity?.title)
        assertEquals(dummyMovies.data?.overview, movieEntity?.overview)
        assertEquals(dummyMovies.data?.posterPath, movieEntity?.posterPath)
        assertEquals(dummyMovies.data?.backdropPath, movieEntity?.backdropPath)
//        assertEquals(dummyMovies.data?.voteAverage, movieEntity?.voteAverage, 0.0)

        detailsViewModel.getMovieDetails().observeForever(movieDetailsObserver)
        verify(movieDetailsObserver).onChanged(dummyMovies)
    }

    @Test
    fun getDetailTvShows() {
        val detailsTvShow = MutableLiveData<Resource<TvShowEntity>>()
        detailsTvShow.value = dummyTvShows

        `when`(repository.getTvShowDetails(tvShowId)).thenReturn(detailsTvShow)
        val tvShowEntity = detailsViewModel.getTvShowDetails().value?.data
        verify(repository).getTvShowDetails(tvShowId)

        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShows.data?.tvShowId, tvShowEntity?.tvShowId)
        assertEquals(dummyTvShows.data?.name, tvShowEntity?.name)
        assertEquals(dummyTvShows.data?.overview, tvShowEntity?.overview)
        assertEquals(dummyTvShows.data?.posterPath, tvShowEntity?.posterPath)
        assertEquals(dummyTvShows.data?.backdropPath, tvShowEntity?.backdropPath)
//        assertEquals(dummyTvShows.data?.voteAverage, tvShowEntity?.voteAverage, 0.0)

        detailsViewModel.getTvShowDetails().observeForever(tvShowDetailsObserver)
        verify(tvShowDetailsObserver).onChanged(dummyTvShows)
    }
}