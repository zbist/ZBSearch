package com.example.notesfinal.zbsearch.domain.model.responses

import com.google.gson.annotations.SerializedName

data class ResultMoviesTMDB(
    @SerializedName("results")
    val results: List<MovieTMDB>,
)