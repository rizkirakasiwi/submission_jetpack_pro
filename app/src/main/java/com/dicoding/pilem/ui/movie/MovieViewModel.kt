package com.dicoding.pilem.ui.movie

import androidx.lifecycle.ViewModel
import com.dicoding.pilem.data.DataDummy
import com.dicoding.pilem.data.FilmData

class MovieViewModel : ViewModel() {
    val popularMovieData = DataDummy.generateDummyMovie().filter { filmData: FilmData -> filmData.isPopular }
    val movieData = DataDummy.generateDummyMovie().filter { filmData: FilmData -> !filmData.isPopular }
}