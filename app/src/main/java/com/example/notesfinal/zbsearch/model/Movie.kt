package com.example.notesfinal.zbsearch.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val name: String = "дефолт",
    val englishName: String = "default",
    val genre: String = "fantastic",
    val poster: String = "",
    val longOfMovie: String = "123 min.",
    val budget: String = "10000000$",
    val revenue: String = "12345678$",
    val year: String = "2020",
    val rating: String = "10.0",
    val content: String = "default content",
    val dateOfStart: String = "2021-06-21",
    val description: String = "Some text"
) : Parcelable