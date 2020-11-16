package com.fakhry.movie.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fakhry.movie.R
import com.fakhry.movie.model.MovieAndTvShowEntity
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV = "extra_tv"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        showLoading(true)
        setViewModel()

        btn_back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_back -> {
                onBackPressed()
            }
        }
    }

    private fun setViewModel() {
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailsViewModel::class.java]

        val extras = intent.extras

        if (extras != null) {
            val idMovie = extras.getInt(EXTRA_MOVIE)
            val idTvShows = extras.getInt(EXTRA_TV)
            Log.d("telolet", "${extras.containsKey(EXTRA_MOVIE)}")

            when {
                extras.containsKey(EXTRA_MOVIE) -> {
                    viewModel.setSelectedItem(idMovie)
                    val movies = viewModel.getMovies()
                    populateItem(movies)
                }

                extras.containsKey(EXTRA_TV) -> {
                    viewModel.setSelectedItem(idTvShows)
                    val tvShows = viewModel.getTvShows()
                    populateItem(tvShows)
                }
            }

        }
    }

    private fun populateItem(item: MovieAndTvShowEntity) {
        Glide.with(this)
            .load(item.poster_url)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_refresh_24dp))
            .error(R.drawable.ic_broken_image_24dp)
            .into(iv_poster)
        Glide.with(this)
            .load(item.backdrop_url)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_refresh_24dp))
            .error(R.drawable.ic_broken_image_24dp)
            .into(iv_backdrop)
        tv_title_details.text = item.title
        tv_synopsis_details.text = item.synopsis
        tv_rating_details.text = item.rating.toString()
        pb_rating.progress = (item.rating * 10).toInt()
        showLoading(false)
    }

    private fun showLoading(state: Boolean) =
        if (state) progressBar4.visibility = View.VISIBLE else progressBar4.visibility = View.GONE
}