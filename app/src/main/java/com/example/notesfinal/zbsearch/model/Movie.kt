package com.example.notesfinal.zbsearch.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val name: String = "default",
    val icon: String = "",
    val year: String = "2020",
    val rating: String = "10.0",
    val content: String = "default content",
    val dateOfStart: String = "2021-06-21"
) : Parcelable