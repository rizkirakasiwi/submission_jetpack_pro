package com.dicoding.pilem.ui.main

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.dicoding.pilem.R
import com.dicoding.pilem.data.DataDummy
import com.dicoding.pilem.ui.home.HomeFragment
import com.dicoding.pilem.ui.movie.MovieViewHolder
import com.dicoding.pilem.ui.movie.PopularMovieViewHolder
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    private lateinit var navController : NavController
    private val dummy = DataDummy.generateDummyMovie()
    private lateinit var homeScenario : FragmentScenario<HomeFragment>

    fun recyclerClick(): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return any(View::class.java)
            }

            override fun getDescription(): String {
                return "performing click() on recycler view item"
            }

            override fun perform(uiController: UiController?, view: View) {
                view.performClick()
            }
        }
    }

    @Before
    fun init(){
        homeScenario = launchFragmentInContainer(themeResId = R.style.Theme_Pilem)
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        homeScenario.onFragment{fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @Test
    fun popularMovieAndNavigation(){
        val popularDummy = dummy.filter { filmData -> filmData.isPopular }

        onView(withId(R.id.recyclerview_popular_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerview_popular_movie)).perform(RecyclerViewActions.scrollToPosition<PopularMovieViewHolder>(popularDummy.size))


        onView(withId(R.id.recyclerview_popular_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<PopularMovieViewHolder>(0, click()))
        assertThat(navController.currentDestination?.id, equalTo(R.id.detailFragment))

    }

    @Test
    fun movieAndNavigation(){
        val movieDummy = dummy.filter { filmData -> !filmData.isPopular }

        onView(withId(R.id.recyclerview_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerview_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movieDummy.size))

        onView(withId(R.id.recyclerview_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        assertThat(navController.currentDestination?.id, equalTo(R.id.detailFragment))


    }


    @Test
    fun popularTvShowAndNavigation(){
        val popularDummy = dummy.filter { filmData -> filmData.isPopular }

        onView(withText("TV Show")).perform(click())

        onView(withId(R.id.recyclerview_popular_tvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerview_popular_tvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(popularDummy.size))


        onView(withId(R.id.recyclerview_popular_tvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        assertThat(navController.currentDestination?.id, equalTo(R.id.detailFragment))

        onView(withText(popularDummy[0].title)).check(matches(isDisplayed()))
    }

    @Test
    fun tvShowAndNavigation(){
        val movieDummy = dummy.filter { filmData -> !filmData.isPopular }

        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.recyclerview_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerview_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movieDummy.size))

        onView(withId(R.id.recyclerview_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        assertThat(navController.currentDestination?.id, equalTo(R.id.detailFragment))

    }

    @Test
    fun navigateToDetail(){
        onView(withId(R.id.recyclerview_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerview_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<MovieViewHolder>(0, click()))
        onView(withId(R.id.mDetailFragment)).check(matches(isDisplayed()))
    }


}