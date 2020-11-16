package com.fakhry.movie.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fakhry.movie.R

class DetailsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TV = "extra_tv"
        const val EXTRA_MOVIE = "extra_movie"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }
}