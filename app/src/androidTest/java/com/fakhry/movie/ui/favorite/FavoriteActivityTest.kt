package com.fakhry.movie.ui.favorite

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.fakhry.movie.R
import com.fakhry.movie.ui.dashboard.DashboardActivity
import com.fakhry.movie.utils.DataDummy
import com.fakhry.movie.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteActivityTest {
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
        IdlingRegistry.getInstance()
            .unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovies() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size))
    }

//    @Test
//    fun loadDetailMovies() {
//        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//            0, click()))
//        onView(withId(R.id.tv_title_details)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_title_details)).check(matches(withText(dummyMovies[0].title)))
//        onView(withId(R.id.tv_synopsis_details)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_synopsis_details)).check(matches(withText(dummyMovies[0].overview)))
//        onView(withId(R.id.tv_rating_details)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_rating_details)).check(matches(withText(dummyMovies[0].voteAverage.toString())))
//        onView(withId(R.id.rb_rating)).check(matches(isDisplayed()))
//        onView(withId(R.id.btn_back)).perform(click())
//        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
//    }

    @Test
    fun loadTvShows() {
        Espresso.onView(ViewMatchers.withText("TV Shows")).perform(ViewActions.click())
//        swipeLeft()
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size))
    }

//    @Test
//    fun loadDetailTvShows() {
//        swipeLeft()
//        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//            0, click()))
//        onView(withId(R.id.tv_title_details)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_title_details)).check(matches(withText(dummyTvShows[0].name)))
//        onView(withId(R.id.tv_synopsis_details)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_synopsis_details)).check(matches(withText(dummyTvShows[0].overview)))
//        onView(withId(R.id.tv_rating_details)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_rating_details)).check(matches(withText(dummyTvShows[0].voteAverage.toString())))
//        onView(withId(R.id.rb_rating)).check(matches(isDisplayed()))
//    }

//    @Test
//    fun cekMovieFavorite() {
//        onView(withText("Favorite")).perform(click())
//        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//            0, click()))
//        onView(withId(R.id.tv_title_details)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_title_details)).check(matches(withText(dummyMovies[0].title)))
//        onView(withId(R.id.tv_synopsis_details)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_synopsis_details)).check(matches(withText(dummyMovies[0].overview)))
//        onView(withId(R.id.tv_rating_details)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_rating_details)).check(matches(withText(dummyMovies[0].voteAverage.toString())))
//        onView(withId(R.id.rb_rating)).check(matches(isDisplayed()))
//        onView(withId(R.id.btn_back)).perform(click())
//        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
//    }

//    @Test
//    fun cekTvShowFavorite() {
//        onView(withText("Favorite")).perform(click())
//        swipeLeft()
//        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//            0, click()))
//        onView(withId(R.id.tv_title_details)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_title_details)).check(matches(withText(dummyTvShows[0].name)))
//        onView(withId(R.id.tv_synopsis_details)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_synopsis_details)).check(matches(withText(dummyTvShows[0].overview)))
//        onView(withId(R.id.tv_rating_details)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_rating_details)).check(matches(withText(dummyTvShows[0].voteAverage.toString())))
//        onView(withId(R.id.rb_rating)).check(matches(isDisplayed()))
//    }
}