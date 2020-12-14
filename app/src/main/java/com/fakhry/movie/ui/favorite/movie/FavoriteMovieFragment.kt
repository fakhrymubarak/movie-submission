package com.fakhry.movie.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakhry.movie.R
import com.fakhry.movie.data.source.local.entity.MovieEntity
import com.fakhry.movie.ui.details.DetailsActivity
import com.fakhry.movie.ui.favorite.tvshow.FavTVShowAdapter
import com.fakhry.movie.ui.favorite.tvshow.FavTvShowViewModel
import com.fakhry.movie.viewmodel.ViewModelFactory
import com.fakhry.movie.vo.Status
import kotlinx.android.synthetic.main.fragment_movie.*


class FavoriteMovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showLoading(true)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val moviesViewModel = ViewModelProvider(
                this, factory
            )[FavTvShowViewModel::class.java]
//            EspressoIdlingResource.increment()
            moviesViewModel.getPopularMovies().observe(this, { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> {
                            showLoading(false)
                            if (movies.data != null) {
                                showRecyclerView(movies.data)
                            }
                        }
                        Status.ERROR -> showLoading(true)
                    }
                }
//                EspressoIdlingResource.decrement()
            })
        }
    }

    private fun showRecyclerView(movies: List<MovieEntity>) {
        rv_movie.setHasFixedSize(true)
        val movieAdapter = FavTVShowAdapter()
        movieAdapter.setMovies(movies)
        movieAdapter.notifyDataSetChanged()
        rv_movie.layoutManager = LinearLayoutManager(context)
        rv_movie.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        rv_movie.adapter = movieAdapter
        movieAdapter.setOnItemClickCallback(object : FavTVShowAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MovieEntity) {
                showSelectedUser(data.movieId)

            }
        })
        showLoading(false)
    }

    private fun showSelectedUser(itemId: Int?) {
        val intent = Intent(requireActivity(), DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.EXTRA_MOVIE, itemId)
        startActivity(intent)
    }

    private fun showLoading(state: Boolean) =
        if (state) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.GONE
}
