package com.fakhry.movie.ui.favorite.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fakhry.movie.R
import com.fakhry.movie.data.source.local.entity.MovieEntity
import com.fakhry.movie.databinding.ItemRowsBinding
import com.fakhry.movie.ui.details.DetailsActivity

class FavMovieAdapter :
    PagedListAdapter<MovieEntity, FavMovieAdapter.ListViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val itemRowsBinding =
            ItemRowsBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(itemRowsBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val favMovie = getItem(position)
        if (favMovie != null) {
            holder.bind(favMovie)
        }
    }

    fun getSwipedData(swipedPosition: Int): MovieEntity? = getItem(swipedPosition)

    class ListViewHolder(private val binding: ItemRowsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favMovie: MovieEntity) {
            with(binding){
                tvTitle.text = favMovie.title
                tvDesc.text = favMovie.overview
                tvRating.text = favMovie.voteAverage.toString()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailsActivity::class.java)
                    intent.putExtra(DetailsActivity.EXTRA_MOVIE, favMovie.movieId)
                    itemView.context.startActivity(intent)
                }
                val circularProgressDrawable = CircularProgressDrawable(ivPoster.context)
                circularProgressDrawable.strokeWidth = 5f
                circularProgressDrawable.centerRadius = 30f

                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + favMovie.posterPath)
                    .apply(RequestOptions.placeholderOf(circularProgressDrawable))
                    .error(R.drawable.ic_broken_image_24dp)
                    .into(ivPoster)
            }
        }
    }
}