package com.fakhry.movie.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.remote.response.movie.details.MovieDetailsResponse
import com.fakhry.movie.data.source.remote.response.tvshow.details.TvShowDetailsResponse
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
class DetailsViewModelTest {
    private lateinit var detailsViewModel: DetailsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var movieDetailsObserver: Observer<MovieDetailsResponse>
    private lateinit var tvShowDetailsObserver: Observer<TvShowDetailsResponse>

    private val dummyMovies = DataDummy.generateRemoteDummyMovieDetails(1)
    private val movieId = dummyMovies.id

    private val dummyTvShows = DataDummy.generateRemoteDummyTvShowDetails(1)
    private val tvShowId = dummyTvShows.id

    @Before
    fun setUp() {
        detailsViewModel = DetailsViewModel(repository)
        detailsViewModel.setMovieSelected(movieId)
        detailsViewModel.setTvShowSelected(tvShowId)
    }

    @Test
    fun getDetailsMovies() {
        val detailsMovie = MutableLiveData<MovieDetailsResponse>()
        detailsMovie.value = dummyMovies

        `when`(repository.getMovieDetails(movieId)).thenReturn(detailsMovie)
        val movieEntity = detailsViewModel.getMovieDetails().value as MovieDetailsResponse
        verify(repository).getMovieDetails(movieId)

        assertNotNull(movieEntity)
        assertEquals(dummyMovies.id, movieEntity.id)
        assertEquals(dummyMovies.title, movieEntity.title)
        assertEquals(dummyMovies.overview, movieEntity.overview)
        assertEquals(dummyMovies.posterPath, movieEntity.posterPath)
        assertEquals(dummyMovies.backdropPath, movieEntity.backdropPath)
        assertEquals(dummyMovies.voteAverage, movieEntity.voteAverage, 0.0)

        detailsViewModel.getMovieDetails().observeForever(movieDetailsObserver)
        verify(movieDetailsObserver).onChanged(dummyMovies)
    }

    @Test
    fun getDetailTvShows() {
        val detailsTvShow = MutableLiveData<TvShowDetailsResponse>()
        detailsTvShow.value = dummyTvShows

        `when`(repository.getTvShowDetails(tvShowId)).thenReturn(detailsTvShow)
        val tvShowEntity = detailsViewModel.getTvShowDetails().value as TvShowDetailsResponse
        verify(repository).getTvShowDetails(tvShowId)

        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShows.id, tvShowEntity.id)
        assertEquals(dummyTvShows.name, tvShowEntity.name)
        assertEquals(dummyTvShows.overview, tvShowEntity.overview)
        assertEquals(dummyTvShows.posterPath, tvShowEntity.posterPath)
        assertEquals(dummyTvShows.backdropPath, tvShowEntity.backdropPath)
        assertEquals(dummyTvShows.voteAverage, tvShowEntity.voteAverage, 0.0)

        detailsViewModel.getTvShowDetails().observeForever(tvShowDetailsObserver)
        verify(tvShowDetailsObserver).onChanged(dummyTvShows)
    }
}