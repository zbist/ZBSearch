package com.example.notesfinal.zbsearch.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Long = 0,
    val title: String = "дефолт",
    val originalTitle: String = "default",
    val genre: String = "fantastic",
    val posterPath: String = "",
    val voteAverage: String = "10.0",
    val releaseDate: String = "2021-06-21",
    val overview: String = "Some text",
    var isFavorite: Boolean = false,
) : Parcelable



