package com.fakhry.movie.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fakhry.movie.R
import com.fakhry.movie.data.source.remote.response.movie.popular.MovieResponse
import kotlinx.android.synthetic.main.item_rows.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private val listMovie = ArrayList<MovieResponse>()

    interface OnItemClickCallback {
        fun onItemClicked(data: MovieResponse)
    }

    fun setMovies(items: List<MovieResponse>) {
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
        val circularProgressDrawable = CircularProgressDrawable(holder.ivAvatar.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        val movie = listMovie[position]
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movie.posterPath)
            .apply(RequestOptions.placeholderOf(circularProgressDrawable))
            .error(R.drawable.ic_broken_image_24dp)
            .into(holder.ivAvatar)
        holder.tvTitle.text = movie.title
        holder.tvSynopsis.text = movie.overview
        holder.tvRating.text = movie.voteAverage.toString()
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