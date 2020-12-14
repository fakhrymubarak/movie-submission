package com.fakhry.movie.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fakhry.movie.R
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        setSupportActionBar(findViewById(R.id.toolbar_favorite))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setMovieAndTvShowAdapter()

        btn_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setMovieAndTvShowAdapter() {
        val sectionsPagerAdapter = FavSectionsPagerAdapter(this, supportFragmentManager)
        view_pager_fav.adapter = sectionsPagerAdapter
        tabs_fav.setupWithViewPager(view_pager_fav)
    }
}