package com.fakhry.movie.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fakhry.movie.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setMovieAndTvShowAdapter()
    }
    private fun setMovieAndTvShowAdapter() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
    }
}