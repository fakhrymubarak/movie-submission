package com.fakhry.movie.ui.favorite.tvshow

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
import com.fakhry.movie.data.source.local.entity.TvShowEntity
import com.fakhry.movie.databinding.ItemRowsBinding
import com.fakhry.movie.ui.details.DetailsActivity

class FavTVShowAdapter : PagedListAdapter<TvShowEntity, FavTVShowAdapter.ListViewHolder>(
    DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
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
        val favTvShow = getItem(position)
        if (favTvShow != null) {
            holder.bind(favTvShow)
        }
    }

    fun getSwipedData(swipedPosition: Int): TvShowEntity? = getItem(swipedPosition)

    class ListViewHolder(private val binding: ItemRowsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favTvShow: TvShowEntity) {
            with(binding){
                tvTitle.text = favTvShow.name
                tvDesc.text = favTvShow.overview
                tvRating.text = favTvShow.voteAverage.toString()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailsActivity::class.java)
                    intent.putExtra(DetailsActivity.EXTRA_TV, favTvShow.tvShowId)
                    itemView.context.startActivity(intent)
                }
                val circularProgressDrawable = CircularProgressDrawable(ivPoster.context)
                circularProgressDrawable.strokeWidth = 5f
                circularProgressDrawable.centerRadius = 30f

                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + favTvShow.posterPath)
                    .apply(RequestOptions.placeholderOf(circularProgressDrawable))
                    .error(R.drawable.ic_broken_image_24dp)
                    .into(ivPoster)
            }
        }
    }
}