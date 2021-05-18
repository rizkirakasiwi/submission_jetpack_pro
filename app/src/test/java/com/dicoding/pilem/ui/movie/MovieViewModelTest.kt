package com.dicoding.pilem.ui.movie

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {
    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun init(){
        movieViewModel = MovieViewModel()
    }

    @Test
    fun testGetPopularMovieData() {
        val popularMovieData = movieViewModel.popularMovieData
        assertNotNull(popularMovieData)
        assertEquals(true, popularMovieData[0].isPopular)
    }

    @Test
    fun testGetMovieData() {
        val movieData = movieViewModel.movieData
        assertNotNull(movieData)
        assertEquals(false, movieData[0].isPopular)
    }
}