package com.example.notesfinal.zbsearch.model.responses

import com.google.gson.annotations.SerializedName

data class MovieTMDB(

    @SerializedName("id")
    val id: Long,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("title")
    val title: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("overview")
    val overview: String,
)