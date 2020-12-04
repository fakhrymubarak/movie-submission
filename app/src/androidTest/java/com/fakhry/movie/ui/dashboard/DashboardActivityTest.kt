package com.fakhry.movie.ui.dashboard

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.fakhry.movie.R
import com.fakhry.movie.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class DashboardActivityTest {
    private val dummyMovies = DataDummy.generateDummyMovie()

    private val dummyTvShows = DataDummy.generateDummyTvShow()

    @get:Rule
    var activityRule = ActivityScenarioRule(DashboardActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
            dummyMovies.size))
    }

    @Test
    fun loadDetailMovies() {
        Thread.sleep(2500)
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0,
            click()))
        Thread.sleep(2500)
        onView(withId(R.id.tv_title_details)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_title_details)).check(ViewAssertions.matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.tv_synopsis_details)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_synopsis_details)).check(ViewAssertions.matches(withText(dummyMovies[0].synopsis)))
        onView(withId(R.id.tv_rating_details)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_rating_details)).check(ViewAssertions.matches(withText(dummyMovies[0].rating.toString())))
        onView(withId(R.id.rb_rating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.btn_back)).perform(click())
        onView(withId(R.id.rv_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
            dummyTvShows.size))
    }


    @Test
    fun loadDetailTvShows() {
        onView(withText("TV Shows")).perform(click())
        Thread.sleep(2500)
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0,
            click()))
        Thread.sleep(2500)
        onView(withId(R.id.tv_title_details)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_title_details)).check(ViewAssertions.matches(withText(dummyTvShows[0].title)))
        onView(withId(R.id.tv_synopsis_details)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_synopsis_details)).check(ViewAssertions.matches(withText(dummyTvShows[0].synopsis)))
        onView(withId(R.id.tv_rating_details)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_rating_details)).check(ViewAssertions.matches(withText(dummyTvShows[0].rating.toString())))
        onView(withId(R.id.rb_rating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}