package com.fakhry.movie.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fakhry.movie.R
import com.fakhry.movie.data.source.local.entity.MovieEntity
import com.fakhry.movie.data.source.local.entity.TvShowEntity
import com.fakhry.movie.utils.EspressoIdlingResource
import com.fakhry.movie.viewmodel.ViewModelFactory
import com.fakhry.movie.vo.Status
import kotlinx.android.synthetic.main.activity_details.*
import kotlin.properties.Delegates

class DetailsActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV = "extra_tv"
    }

    private var isMovieDetails by Delegates.notNull<Boolean>()
    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var mMovieEntity: MovieEntity
    private lateinit var mTvShowEntity: TvShowEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        showLoading(true)
        setViewModel()
        btn_back.setOnClickListener(this)
        fab_favorite.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_back -> {
                onBackPressed()
            }
            fab_favorite -> {
                if (isMovieDetails) {
                    if (mMovieEntity.isFavMovie) {
                        Toast.makeText(this, R.string.remove_fav, Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, R.string.add_fav, Toast.LENGTH_LONG).show()
                    }
                    detailsViewModel.setFavMovie(mMovieEntity)
                } else {
                    if (mTvShowEntity.isFavTvShow) {
                        Toast.makeText(this, R.string.remove_fav, Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, R.string.add_fav, Toast.LENGTH_LONG).show()
                    }
                    detailsViewModel.setFavTvShow(mTvShowEntity)
                }
            }
        }
    }

    private fun setFavState(state: Boolean) {
        if (state) fab_favorite.setImageResource(R.drawable.ic_favorite_24dp)
        else fab_favorite.setImageResource(R.drawable.ic_favorite_border_24dp)
    }

    private fun setViewModel() {
        val factory = ViewModelFactory.getInstance(this)
        detailsViewModel = ViewModelProvider(
            this, factory
        )[DetailsViewModel::class.java]

        val extras = intent.extras

        if (extras != null) {
            val idMovie = extras.getInt(EXTRA_MOVIE)
            val idTvShows = extras.getInt(EXTRA_TV)

            when {
                extras.containsKey(EXTRA_MOVIE) -> {
                    isMovieDetails = true
                    detailsViewModel.setMovieSelected(idMovie)
                    detailsViewModel.getMovieDetails().observe(this, { movieDetails ->
                        if (movieDetails != null) {
                            when (movieDetails.status) {
                                Status.LOADING -> showLoading(true)
                                Status.SUCCESS -> {
                                    showLoading(false)
                                    if (movieDetails.data != null) {
                                        populateItem(movieDetails.data)
                                    }
                                }
                                Status.ERROR -> {
                                    showLoading(false)
                                    Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                    })
                }

                extras.containsKey(EXTRA_TV) -> {
                    isMovieDetails = false
                    detailsViewModel.setTvShowSelected(idTvShows)
                    detailsViewModel.getTvShowDetails().observe(this, { tvShowDetails ->
                        if (tvShowDetails != null) {
                            when (tvShowDetails.status) {
                                Status.LOADING -> showLoading(true)
                                Status.SUCCESS -> {
                                    showLoading(false)
                                    if (tvShowDetails.data != null) {
                                        populateItem(tvShowDetails.data)
                                    }
                                }
                                Status.ERROR -> {
                                    showLoading(false)
                                    Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }

                        }
                    })
                }
            }
        }
    }

    private fun populateItem(item: MovieEntity) {
        mMovieEntity = item
        setFavState(item.isFavMovie)
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

    private fun populateItem(item: TvShowEntity) {
        mTvShowEntity = item
        setFavState(item.isFavTvShow)
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

    private fun showLoading(state: Boolean) =
        if (state) progressBar4.visibility = View.VISIBLE else progressBar4.visibility = View.GONE
}