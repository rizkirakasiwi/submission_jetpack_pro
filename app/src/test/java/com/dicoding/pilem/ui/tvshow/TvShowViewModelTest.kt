package com.dicoding.pilem.ui.tvshow

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {

    private lateinit var viewModel : TvShowViewModel

    @Before
    fun init(){
        viewModel = TvShowViewModel()
    }

    @Test
    fun testGetPopularTvShowData() {
        val popularTvShowData = viewModel.popularTvShowData
        assertNotNull(popularTvShowData)
        assertEquals(true, popularTvShowData[0].isPopular)
    }

    @Test
    fun testGetTvShowData() {
        val tvShowData = viewModel.tvShowData
        assertNotNull(tvShowData)
        assertEquals(false, tvShowData[0].isPopular)
    }
}