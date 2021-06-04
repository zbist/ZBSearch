package com.example.notesfinal.zbsearch.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val adult: Boolean = false,
    val title: String = "дефолт",
    val originalTitle: String = "default",
    val genre: String = "fantastic",
    val posterPath: String = "",
    val voteAverage: String = "10.0",
    val releaseDate: String = "2021-06-21",
    val overview: String = "Some text",
) : Parcelable



