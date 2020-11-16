package com.fakhry.movie.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fakhry.movie.R
import com.fakhry.movie.model.MovieAndTvShowEntity
import kotlinx.android.synthetic.main.item_rows.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private val listMovie = ArrayList<MovieAndTvShowEntity>()

    interface OnItemClickCallback {
        fun onItemClicked(data: MovieAndTvShowEntity)
    }

    fun setMovies(items: List<MovieAndTvShowEntity>) {
        listMovie.clear()
        listMovie.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_rows, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val movie = listMovie[position]
        Glide.with(holder.itemView.context)
            .load(movie.poster_url)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_refresh_24dp))
            .error(R.drawable.ic_broken_image_24dp)
            .into(holder.ivAvatar)
        holder.tvTitle.text = movie.title
        holder.tvSynopsis.text = movie.synopsis
        holder.tvRating.text = movie.rating.toString()
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listMovie[position]) }
    }

    override fun getItemCount(): Int = listMovie.size

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