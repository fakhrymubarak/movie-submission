package com.fakhry.movie.ui.dashboard

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.fakhry.movie.R
import com.fakhry.movie.utils.DataDummy
import com.fakhry.movie.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * NOTE :
 * Before running this testing,
 * Please make sure that the application in your device
 * doesn't have either favorite movie and favorite tv show
 */


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
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
            dummyMovies.size))
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0, click()))
        onView(withId(R.id.tv_title_details)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis_details)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating_details)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_back)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.activity_favorite)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShows() {
        onView(withText(R.string.tab_text_2)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
            dummyTvShows.size))
    }

    @Test
    fun loadDetailTvShows() {
        onView(withText(R.string.tab_text_2)).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0, click()))
        onView(withId(R.id.tv_title_details)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis_details)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating_details)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_rating)).check(matches(isDisplayed()))
    }

    @Test
    fun addMoviesToFav() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0, click()))
        onView(withId(R.id.fab_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(withId(R.id.fab_favorite)).perform(click())
    }

    @Test
    fun addTvShowToFav() {
        onView(withText(R.string.tab_text_2)).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0, click()))
        onView(withId(R.id.fab_favorite)).check(matches(isDisplayed()))

        onView(withId(R.id.fab_favorite)).perform(click())
        onView(withId(R.id.fab_favorite)).perform(click())
    }

    @Test
    fun goToFavActivity() {
        onView(withId(R.id.activity_favorite)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withText(R.string.tab_text_3)).check(matches(isDisplayed()))
        onView(withText(R.string.tab_text_4)).check(matches(isDisplayed()))

        onView(withText(R.string.tab_text_4)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun cekMovieFavorite() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0, click()))
        onView(withId(R.id.fab_favorite)).perform(click())

        onView(withId(R.id.btn_back)).perform(click())
        onView(withId(R.id.activity_favorite)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_no_data_movie)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tv_no_data_movie)).check(matches(withEffectiveVisibility(Visibility.GONE)))

        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0, click()))
        onView(withId(R.id.tv_title_details)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis_details)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating_details)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).perform(click())

        onView(withId(R.id.btn_back)).perform(click())
        onView(withId(R.id.iv_no_data_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_no_data_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun cekTvShowFavorite() {
        onView(withText(R.string.tab_text_2)).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0, click()))
        onView(withId(R.id.fab_favorite)).perform(click())

        onView(withId(R.id.btn_back)).perform(click())
        onView(withId(R.id.activity_favorite)).perform(click())

        onView(withText(R.string.tab_text_4)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_no_data_tv_show)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tv_no_data_tv_show)).check(matches(withEffectiveVisibility(Visibility.GONE)))

        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0, click()))
        onView(withId(R.id.tv_title_details)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis_details)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating_details)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).perform(click())

        onView(withId(R.id.btn_back)).perform(click())
        onView(withId(R.id.iv_no_data_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_no_data_tv_show)).check(matches(isDisplayed()))
    }
}