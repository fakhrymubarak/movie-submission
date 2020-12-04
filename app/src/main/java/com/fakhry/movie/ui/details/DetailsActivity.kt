package com.fakhry.movie.ui.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fakhry.movie.R
import com.fakhry.movie.data.source.local.entity.MovieAndTvShowEntity
import com.fakhry.movie.viewmodel.ViewModelFactory
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
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(
            this, factory
        )[DetailsViewModel::class.java]

        val extras = intent.extras

        if (extras != null) {
            val idMovie = extras.getInt(EXTRA_MOVIE)
            val idTvShows = extras.getInt(EXTRA_TV)

            when {
                extras.containsKey(EXTRA_MOVIE) -> {
                    viewModel.setMovieSelected(idMovie)
                    viewModel.getMovieDetails().observe(this, { movieDetails ->
                        populateItem(movieDetails)
                    })
                }

                extras.containsKey(EXTRA_TV) -> {
                    viewModel.setTvShowSelected(idTvShows)
                    viewModel.getTvShowDetails().observe(this, { tvShowDetails ->
                        populateItem(tvShowDetails)
                    })
                }
            }

        }
    }

    private fun populateItem(item: MovieAndTvShowEntity) {
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        Glide.with(this)
            .load(item.poster_url)
            .apply(RequestOptions.placeholderOf(circularProgressDrawable))
            .error(R.drawable.ic_broken_image_24dp)
            .into(iv_poster)
        Glide.with(this)
            .load(item.backdrop_url)
            .apply(RequestOptions.placeholderOf(circularProgressDrawable))
            .error(R.drawable.ic_broken_image_24dp)
            .into(iv_backdrop)
        tv_title_details.text = item.title
        tv_synopsis_details.text = item.synopsis
        tv_rating_details.text = item.rating.toString()
        rb_rating.rating = item.rating.toFloat() / 2
        showLoading(false)
    }

    private fun showLoading(state: Boolean) =
        if (state) progressBar4.visibility = View.VISIBLE else progressBar4.visibility = View.GONE
}