package com.fakhry.movie.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fakhry.movie.R
import com.fakhry.movie.model.MovieAndTvShowEntity
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_TV = "extra_tv"
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        showLoading(true)
        setDataDetails()

        btn_back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_back -> {
                onBackPressed()
            }
        }
    }

    private fun setDataDetails() {
        val dataMovie = intent.getParcelableExtra<MovieAndTvShowEntity>(EXTRA_MOVIE)
        val dataTvShow = intent.getParcelableExtra<MovieAndTvShowEntity>(EXTRA_TV)

        val data = dataMovie ?: dataTvShow

        if (data != null) {
            Glide.with(this)
                .load(data.poster_url)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_refresh_24dp))
                .error(R.drawable.ic_broken_image_24dp)
                .into(iv_poster)
            Glide.with(this)
                .load(data.backdrop_url)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_refresh_24dp))
                .error(R.drawable.ic_broken_image_24dp)
                .into(iv_backdrop)
            tv_title_details.text = data.title
            tv_synopsis_details.text = data.synopsis
            tv_rating_details.text = data.rating.toString()
            pb_rating.progress = (data.rating!! * 10).toInt()
            showLoading(false)
        }
    }

    private fun showLoading(state: Boolean) =
        if (state) progressBar4.visibility = View.VISIBLE else progressBar4.visibility = View.GONE
}