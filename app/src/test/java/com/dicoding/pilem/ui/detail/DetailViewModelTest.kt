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

    /**
     * data yang dikirimkan dari movie dan tv show di ambil oleh detail
     * data di add ke viewmodel
     * memastikan data detail tidak kosong
     * memastikan data detail sama dengan data yang dikirimkan
     * */
    @Test
    fun getDetailData(){
        val dummy = DataDummy.generateDummy()[0]

        detailViewModel.addDetail(dummy)
        assertNotNull(detailViewModel.detailData.getOrAwaitValue())
        assertEquals(detailViewModel.detailData.getOrAwaitValue(), dummy)
    }
}