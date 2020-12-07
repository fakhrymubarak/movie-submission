package com.fakhry.movie.ui.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fakhry.movie.R
import com.fakhry.movie.data.source.remote.response.movie.details.MovieDetailsResponse
import com.fakhry.movie.data.source.remote.response.tvshow.details.TvShowDetailsResponse
import com.fakhry.movie.utils.EspressoIdlingResource
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
        val factory = ViewModelFactory.getInstance()
        val detailsViewModel = ViewModelProvider(
            this, factory
        )[DetailsViewModel::class.java]

        val extras = intent.extras

        if (extras != null) {
            val idMovie = extras.getInt(EXTRA_MOVIE)
            val idTvShows = extras.getInt(EXTRA_TV)

            when {
                extras.containsKey(EXTRA_MOVIE) -> {
                    EspressoIdlingResource.increment()
                    detailsViewModel.setMovieSelected(idMovie)
                    detailsViewModel.getMovieDetails().observe(this, { movieDetails ->
                        populateItem(movieDetails)
                        EspressoIdlingResource.decrement()
                    })
                }

                extras.containsKey(EXTRA_TV) -> {
                    EspressoIdlingResource.increment()
                    detailsViewModel.setTvShowSelected(idTvShows)
                    detailsViewModel.getTvShowDetails().observe(this, { tvShowDetails ->
                        populateItem(tvShowDetails)
                        EspressoIdlingResource.decrement()
                    })
                }
            }

        }
    }

    private fun populateItem(item: TvShowDetailsResponse) {
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + item.posterPath)
            .apply(RequestOptions.placeholderOf(circularProgressDrawable))
            .error(R.drawable.ic_broken_image_24dp)
            .into(iv_poster)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500_and_h282_face" + item.backdropPath)
            .apply(RequestOptions.placeholderOf(circularProgressDrawable))
            .error(R.drawable.ic_broken_image_24dp)
            .into(iv_backdrop)
        tv_title_details.text = item.name
        tv_synopsis_details.text = item.overview
        tv_rating_details.text = item.voteAverage.toString()
        rb_rating.rating = item.voteAverage.toFloat() / 2
        showLoading(false)
    }

    private fun populateItem(item: MovieDetailsResponse) {
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + item.posterPath)
            .apply(RequestOptions.placeholderOf(circularProgressDrawable))
            .error(R.drawable.ic_broken_image_24dp)
            .into(iv_poster)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500_and_h282_face" + item.backdropPath)
            .apply(RequestOptions.placeholderOf(circularProgressDrawable))
            .error(R.drawable.ic_broken_image_24dp)
            .into(iv_backdrop)
        tv_title_details.text = item.title
        tv_synopsis_details.text = item.overview
        tv_rating_details.text = item.voteAverage.toString()
        rb_rating.rating = item.voteAverage.toFloat() / 2
        showLoading(false)
    }

    private fun showLoading(state: Boolean) =
        if (state) progressBar4.visibility = View.VISIBLE else progressBar4.visibility = View.GONE
}