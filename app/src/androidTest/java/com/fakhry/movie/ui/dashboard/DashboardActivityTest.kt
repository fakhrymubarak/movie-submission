package com.fakhry.movie.ui.dashboard

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.fakhry.movie.R
import com.fakhry.movie.utils.DataDummy
import com.fakhry.movie.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DashboardActivityTest {
    private val dummyMovies = DataDummy.generateDummyMovie()

    private val dummyTvShows = DataDummy.generateDummyTvShow()

    @get:Rule
    var activityRule = ActivityScenarioRule(DashboardActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
            dummyMovies.size))
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0, click()))
        onView(withId(R.id.tv_title_details)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_title_details)).check(ViewAssertions.matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.tv_synopsis_details)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_synopsis_details)).check(ViewAssertions.matches(withText(dummyMovies[0].overview)))
        onView(withId(R.id.tv_rating_details)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_rating_details)).check(ViewAssertions.matches(withText(dummyMovies[0].voteAverage.toString())))
        onView(withId(R.id.rb_rating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.btn_back)).perform(click())
        onView(withId(R.id.rv_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadTvShows() {
//        onView(withText("TV Shows")).perform(click())
        swipeLeft()
        onView(withId(R.id.rv_tv_show)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
            dummyTvShows.size))
    }

    @Test
    fun loadDetailTvShows() {
//        onView(withText("TV Shows")).perform(click())
        swipeLeft()
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0, click()))
        onView(withId(R.id.tv_title_details)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_title_details)).check(ViewAssertions.matches(withText(dummyTvShows[0].name)))
        onView(withId(R.id.tv_synopsis_details)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_synopsis_details)).check(ViewAssertions.matches(withText(dummyTvShows[0].overview)))
        onView(withId(R.id.tv_rating_details)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_rating_details)).check(ViewAssertions.matches(withText(dummyTvShows[0].voteAverage.toString())))
        onView(withId(R.id.rb_rating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}