package com.dicoding.pilem.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.pilem.data.DataDummy
import com.dicoding.pilem.getOrAwaitValue
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {
    private lateinit var detailViewModel: DetailViewModel

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init(){
        detailViewModel = DetailViewModel()
    }

    @Test
    fun getDetailData(){
        val dummy = DataDummy.generateDummyMovie()[0]
        detailViewModel.addDetail(dummy)
        assertNotNull(detailViewModel.detailData.getOrAwaitValue())
        assertEquals(detailViewModel.detailData.getOrAwaitValue(), dummy)
    }
}