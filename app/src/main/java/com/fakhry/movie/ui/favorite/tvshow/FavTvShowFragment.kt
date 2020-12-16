package com.fakhry.movie.ui.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fakhry.movie.R
import com.fakhry.movie.data.source.local.entity.TvShowEntity
import com.fakhry.movie.databinding.FragmentTvShowBinding
import com.fakhry.movie.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_tv_show.*

class FavTvShowFragment : Fragment() {
    private var _fragmentTvShowBinding: FragmentTvShowBinding? = null
    private val binding get() = _fragmentTvShowBinding

    private lateinit var favTvShowAdapter: FavTVShowAdapter
    private lateinit var favTvShowViewModel: FavTvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _fragmentTvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding?.rvTvShow)
        showLoading(true)
        if (activity != null) {
            setViewModel()
        }
    }

    private fun setViewModel() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        favTvShowViewModel = ViewModelProvider(this, factory)[FavTvShowViewModel::class.java]
        favTvShowViewModel.getFavoriteTvShow().observe(this, { tvShows ->
            if (tvShows.isNotEmpty()) {
                iv_no_data_tv_show.visibility = View.GONE
                tv_no_data_tv_show.visibility = View.GONE
            } else {
                iv_no_data_tv_show.visibility = View.VISIBLE
                tv_no_data_tv_show.visibility = View.VISIBLE
            }
            showRecyclerView(tvShows)
            showLoading(false)
        })
    }

    override fun onResume() {
        setViewModel()
        super.onResume()
    }

    private fun showRecyclerView(movies: PagedList<TvShowEntity>) {
        rv_tv_show.setHasFixedSize(true)
        favTvShowAdapter = FavTVShowAdapter()
        favTvShowAdapter.submitList(movies)
        favTvShowAdapter.notifyDataSetChanged()

        rv_tv_show.layoutManager = LinearLayoutManager(context)
        rv_tv_show.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        rv_tv_show.adapter = favTvShowAdapter
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder,
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val tvShowEntity = favTvShowAdapter.getSwipedData(swipedPosition)
                tvShowEntity?.let { favTvShowViewModel.setFavTvShow(it) }

                val snackbar =
                    Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.undo) {
                    tvShowEntity?.let { favTvShowViewModel.setFavTvShow(it) }
                }
                snackbar.show()
            }
        }
    })

    private fun showLoading(state: Boolean) =
        if (state) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.GONE
}
