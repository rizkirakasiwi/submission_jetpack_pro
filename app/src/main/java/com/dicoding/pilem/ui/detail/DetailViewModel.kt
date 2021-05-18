package com.dicoding.pilem.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.pilem.data.FilmData

class DetailViewModel : ViewModel() {
    private val _detailData = MutableLiveData<FilmData>()
    val detailData : LiveData<FilmData> get() = _detailData

    fun addDetail(filmData: FilmData){
        _detailData.value = filmData
    }
}