package com.fakhry.movie.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.fakhry.movie.data.source.local.LocalDataSource
import com.fakhry.movie.data.source.local.entity.MovieEntity
import com.fakhry.movie.data.source.local.entity.TvShowEntity
import com.fakhry.movie.data.source.remote.ApiResponse
import com.fakhry.movie.data.source.remote.RemoteDataSource
import com.fakhry.movie.utils.AppExecutors
import com.fakhry.movie.utils.DataDummy
import com.fakhry.movie.utils.LiveDataTestUtil
import com.fakhry.movie.utils.PagedListUtil
import com.fakhry.movie.vo.Resource
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@Suppress("UNCHECKED_CAST")
class RepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val repository = Repository(remote, local, appExecutors)

    private val movieResponse = ApiResponse.success(DataDummy.generateRemoteDummyMovies())
    private val movieId = movieResponse.body[0].id
    private val movieDetails =
        ApiResponse.success(DataDummy.generateRemoteDummyMovieDetails(movieId))


    private val tvShowResponse = ApiResponse.success(DataDummy.generateRemoteDummyTvShows())
    private val tvShowId = tvShowResponse.body[0].id
    private val tvShowDetails =
        ApiResponse.success(DataDummy.generateRemoteDummyTvShowDetails(tvShowId))

    private val favMovieResponse = ApiResponse.success(DataDummy.generateFavoriteMovie())

    private val favTvShowResponse = ApiResponse.success(DataDummy.generateFavoriteTvShow())

    @Test
    fun getPopularMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        repository.getPopularMovies()

        val moviesEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()))
        verify(local).getAllMovies()
        assertNotNull(moviesEntities)
        assertEquals(movieResponse.body.size.toLong(), moviesEntities.data?.size?.toLong())
    }

    @Test
    fun getPopularTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        repository.getPopularTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShow()))
        verify(local).getAllTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.body.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieDetails() {
        val dummyMovieDetails = MutableLiveData<MovieEntity>()
        dummyMovieDetails.value = DataDummy.generateDummyMovie()[movieId]
        `when`(local.getMovieDetails(eq(movieId))).thenReturn(dummyMovieDetails)

        val movieDetailsEntities =
            LiveDataTestUtil.getValue(repository.getMovieDetails(movieId))
        verify(local).getMovieDetails(eq(movieId))
        assertNotNull(movieDetailsEntities)
        assertEquals(movieDetails.body.title, movieDetailsEntities.data?.title)
        assertEquals(movieDetails.body.overview, movieDetailsEntities.data?.overview)
        assertEquals(movieDetails.body.posterPath, movieDetailsEntities.data?.posterPath)
        assertEquals(movieDetails.body.backdropPath, movieDetailsEntities.data?.backdropPath)
        assertEquals(movieDetails.body.voteAverage, movieDetailsEntities.data?.voteAverage)
    }

    @Test
    fun getTvShowDetails() {
        val dummyTvShowDetails = MutableLiveData<TvShowEntity>()
        dummyTvShowDetails.value = DataDummy.generateDummyTvShow()[tvShowId]
        `when`(local.getTvShowDetails(eq(tvShowId))).thenReturn(dummyTvShowDetails)

        val tvShowDetailsEntity =
            LiveDataTestUtil.getValue(repository.getTvShowDetails(tvShowId))
        verify(local).getTvShowDetails(eq(tvShowId))
        assertNotNull(tvShowDetailsEntity)
        assertEquals(tvShowDetails.body.name, tvShowDetailsEntity.data?.name)
        assertEquals(tvShowDetails.body.overview, tvShowDetailsEntity.data?.overview)
        assertEquals(tvShowDetails.body.posterPath, tvShowDetailsEntity.data?.posterPath)
        assertEquals(tvShowDetails.body.backdropPath, tvShowDetailsEntity.data?.backdropPath)
        assertEquals(tvShowDetails.body.voteAverage, tvShowDetailsEntity.data?.voteAverage)
    }

    @Test
    fun getFavMovieDetails() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavMovies()).thenReturn(dataSourceFactory)
        repository.getFavMovies()

        val favMovieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateFavoriteTvShow()))
        verify(local).getFavMovies()
        assertNotNull(favMovieEntities)
        assertEquals(favMovieResponse.body.size.toLong(), favMovieEntities.data?.size?.toLong())
    }

    @Test
    fun getFavTvShowDetails() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavTvShows()).thenReturn(dataSourceFactory)
        repository.getFavTvShows()

        val favTvShowEntities =  Resource.success(PagedListUtil.mockPagedList(DataDummy.generateFavoriteTvShow()))
        verify(local).getFavTvShows()
        assertNotNull(favTvShowEntities)
        assertEquals(favTvShowResponse.body.size.toLong(), favTvShowEntities.data?.size?.toLong())
    }
}