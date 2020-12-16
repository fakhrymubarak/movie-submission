package com.fakhry.movie.ui.favorite.movie

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
import com.fakhry.movie.data.source.local.entity.MovieEntity
import com.fakhry.movie.databinding.FragmentMovieBinding
import com.fakhry.movie.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie.*

class FavMovieFragment : Fragment() {
    private var _fragmentMovieBinding: FragmentMovieBinding? = null
    private val binding get() = _fragmentMovieBinding

    private lateinit var favMovieAdapter: FavMovieAdapter
    private lateinit var favMovieViewModel: FavMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _fragmentMovieBinding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showLoading(true)
        itemTouchHelper.attachToRecyclerView(binding?.rvMovie)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            favMovieViewModel = ViewModelProvider(this, factory)[FavMovieViewModel::class.java]
            favMovieViewModel.getFavMovies().observe(this, { movies ->
                if (movies != null) {
                    showRecyclerView(movies)
                    showLoading(false)
                }
            })
        }
    }

    private fun showRecyclerView(movies: PagedList<MovieEntity>) {
        rv_movie.setHasFixedSize(true)
        favMovieAdapter = FavMovieAdapter()
        favMovieAdapter.submitList(movies)
        favMovieAdapter.notifyDataSetChanged()
        rv_movie.layoutManager = LinearLayoutManager(context)
        rv_movie.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        rv_movie.adapter = favMovieAdapter
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
                val movieEntity = favMovieAdapter.getSwipedData(swipedPosition)
                movieEntity?.let { favMovieViewModel.setFavMovie(it) }

                val snackbar =
                    Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.undo) {
                    movieEntity?.let { favMovieViewModel.setFavMovie(it) }
                }
                snackbar.show()
            }
        }
    })

    private fun showLoading(state: Boolean) =
        if (state) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.GONE
}
