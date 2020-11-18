package com.fakhry.movie.ui.movie

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
import com.fakhry.movie.data.source.local.entity.MovieAndTvShowEntity
import com.fakhry.movie.ui.details.DetailsActivity
import com.fakhry.movie.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie.*


class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showLoading(true)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(
                this, factory
            )[MovieViewModel::class.java]
            val movies = viewModel.getMovies()

            showRecyclerView(movies)
        }
    }

    private fun showRecyclerView(movies: List<MovieAndTvShowEntity>) {
        rv_movie.setHasFixedSize(true)
        val movieAdapter = MovieAdapter()
        movieAdapter.setMovies(movies)
        movieAdapter.notifyDataSetChanged()

        rv_movie.layoutManager = LinearLayoutManager(context)
        rv_movie.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        rv_movie.adapter = movieAdapter
        movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MovieAndTvShowEntity) {
                showSelectedUser(data.id)
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
