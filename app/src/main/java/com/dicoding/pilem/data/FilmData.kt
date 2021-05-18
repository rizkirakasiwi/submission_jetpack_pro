package com.dicoding.pilem.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmData (
    val id : Int,
    val title : String,
    val poster : String,
    val genre : List<String>,
    val year : String,
    val description : String,
    val rating : Float,
    val isPopular : Boolean = false
):Parcelable
