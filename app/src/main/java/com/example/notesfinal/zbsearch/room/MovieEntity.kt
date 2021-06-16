package com.example.notesfinal.zbsearch.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "MovieTable")
data class MovieEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val originalTitle: String,
    val genre: String,
    val posterPath: String,
    val voteAverage: String,
    val releaseDate: String,
    val overview: String,
    val isFavorite: String,
)