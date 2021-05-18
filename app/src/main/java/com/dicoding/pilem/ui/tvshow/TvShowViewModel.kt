package com.dicoding.pilem.ui.tvshow

import androidx.lifecycle.ViewModel
import com.dicoding.pilem.data.DataDummy
import com.dicoding.pilem.data.FilmData

class TvShowViewModel : ViewModel() {
    val popularTvShowData = DataDummy.generateDummyTvShow().filter { filmData: FilmData -> filmData.isPopular }
    val tvShowData = DataDummy.generateDummyTvShow().filter { filmData: FilmData -> !filmData.isPopular }
}