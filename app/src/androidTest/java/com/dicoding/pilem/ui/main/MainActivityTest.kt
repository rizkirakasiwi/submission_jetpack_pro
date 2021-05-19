package com.dicoding.pilem.ui.main

import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dicoding.pilem.R
import com.dicoding.pilem.data.DataDummy
import com.dicoding.pilem.ui.movie.MovieViewHolder
import com.dicoding.pilem.ui.movie.PopularMovieViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    private lateinit var navController : NavController
    private val dummy = DataDummy.generateDummy()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun popularMovie(){
        val popularDummy = dummy.filter { filmData -> filmData.isPopular }

        onView(withId(R.id.recyclerview_popular_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerview_popular_movie)).perform(RecyclerViewActions.scrollToPosition<PopularMovieViewHolder>(popularDummy.size))

    }

    @Test
    fun movie(){
        val movieDummy = dummy.filter { filmData -> !filmData.isPopular }

        onView(withId(R.id.recyclerview_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerview_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movieDummy.size))

    }


    @Test
    fun popularTvShow(){
        val popularDummy = dummy.filter { filmData -> filmData.isPopular }

        onView(withText("TV Show")).perform(click())

        onView(withId(R.id.recyclerview_popular_tvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerview_popular_tvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(popularDummy.size))
    }

    @Test
    fun tvShow(){
        val movieDummy = dummy.filter { filmData -> !filmData.isPopular }
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.recyclerview_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerview_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movieDummy.size))
    }

    @Test
    fun detail(){
        val dummy = dummy.filter { filmData -> !filmData.isPopular }

        onView(withId(R.id.recyclerview_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerview_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<MovieViewHolder>(0, click()))
        onView(withId(R.id.mDetailFragment)).check(matches(isDisplayed()))

        onView(withId(R.id.title_text)).check(matches(isDisplayed()))
        onView(withId(R.id.title_text)).check(matches(withText(dummy[0].title)))

        onView(withId(R.id.year_text)).check(matches(isDisplayed()))
        onView(withId(R.id.year_text)).check(matches(withText("Year: "+dummy[0].year)))

        onView(withId(R.id.desc_text)).check(matches(isDisplayed()))
        onView(withId(R.id.desc_text)).check(matches(withText(dummy[0].description)))

        onView(withId(R.id.rating_text)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_text)).check(matches(withText(dummy[0].rating.toString())))

        onView(withId(R.id.genre_text)).check(matches(isDisplayed()))
        onView(withId(R.id.genre_text)).check(matches(withText("Genre: "+dummy[0].genre.joinToString())))

        pressBack()

        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.recyclerview_tvshow)).check(matches(isDisplayed()))

        onView(withId(R.id.recyclerview_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<MovieViewHolder>(0, click()))
        onView(withId(R.id.mDetailFragment)).check(matches(isDisplayed()))

        onView(withId(R.id.title_text)).check(matches(isDisplayed()))
        onView(withId(R.id.title_text)).check(matches(withText(dummy[0].title)))

        onView(withId(R.id.year_text)).check(matches(isDisplayed()))
        onView(withId(R.id.year_text)).check(matches(withText("Year: "+dummy[0].year)))

        onView(withId(R.id.desc_text)).check(matches(isDisplayed()))
        onView(withId(R.id.desc_text)).check(matches(withText(dummy[0].description)))

        onView(withId(R.id.rating_text)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_text)).check(matches(withText(dummy[0].rating.toString())))

        onView(withId(R.id.genre_text)).check(matches(isDisplayed()))
        onView(withId(R.id.genre_text)).check(matches(withText("Genre: "+dummy[0].genre.joinToString())))
    }


}