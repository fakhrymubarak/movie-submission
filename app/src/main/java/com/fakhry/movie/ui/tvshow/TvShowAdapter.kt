package com.fakhry.movie.ui.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fakhry.movie.R
import com.fakhry.movie.data.source.local.entity.TvShowEntity
import kotlinx.android.synthetic.main.item_rows.view.*


class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private val listTvShow = ArrayList<TvShowEntity>()

    interface OnItemClickCallback {
        fun onItemClicked(data: TvShowEntity)
    }

    fun setTvShows(items: List<TvShowEntity>) {
        listTvShow.clear()
        listTvShow.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_rows, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + tvShow.posterPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_refresh_24dp))
            .error(R.drawable.ic_broken_image_24dp)
            .into(holder.ivAvatar)
        holder.tvTitle.text = tvShow.name
        holder.tvSynopsis.text = tvShow.overview
        holder.tvRating.text = tvShow.voteAverage.toString()
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listTvShow[position]) }
    }

    override fun getItemCount(): Int = listTvShow.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivAvatar: ImageView = itemView.iv_poster
        var tvTitle: TextView = itemView.tv_title
        var tvSynopsis: TextView = itemView.tv_desc
        var tvRating: TextView = itemView.tv_rating
    }
}