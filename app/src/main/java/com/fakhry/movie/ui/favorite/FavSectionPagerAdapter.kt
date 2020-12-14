package com.fakhry.movie.ui.favorite

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fakhry.movie.R
import com.fakhry.movie.ui.favorite.movie.FavoriteMovieFragment
import com.fakhry.movie.ui.favorite.tvshow.FavoriteTvShowFragment

class FavSectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabTables = intArrayOf(
        R.string.tab_text_4,
        R.string.tab_text_5
    )

    override fun getItem(position: Int): Fragment =
        when(position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvShowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(tabTables[position])
    }

    override fun getCount(): Int = tabTables.size
}