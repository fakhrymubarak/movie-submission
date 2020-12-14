package com.fakhry.movie.ui.favorite.tvshow

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
import com.fakhry.movie.data.source.local.entity.TvShowEntity
import com.fakhry.movie.ui.details.DetailsActivity
import com.fakhry.movie.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv_show.*

class FavoriteTvShowFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
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
            moviesViewModel.getFavoriteTvShow().observe(this, { tvShows ->
                showRecyclerView(tvShows)
//                EspressoIdlingResource.decrement()
            })
        }
    }

    private fun showRecyclerView(movies: List<TvShowEntity>) {
        rv_tv_show.setHasFixedSize(true)
        val movieAdapter = FavTVShowAdapter()
        movieAdapter.setMovies(movies)
        movieAdapter.notifyDataSetChanged()
        rv_tv_show.layoutManager = LinearLayoutManager(context)
        rv_tv_show.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        rv_tv_show.adapter = movieAdapter
        movieAdapter.setOnItemClickCallback(object : FavTVShowAdapter.OnItemClickCallback {
            override fun onItemClicked(data: TvShowEntity) {
                showSelectedUser(data.tvShowId)
            }
        })
        showLoading(false)
    }

    private fun showSelectedUser(itemId: Int?) {
        val intent = Intent(requireActivity(), DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.EXTRA_TV, itemId)
        startActivity(intent)
    }

    private fun showLoading(state: Boolean) =
        if (state) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.GONE
}
