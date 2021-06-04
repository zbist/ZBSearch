package com.example.notesfinal.zbsearch.model.responses

import com.example.notesfinal.zbsearch.model.responses.MovieTMDB
import com.google.gson.annotations.SerializedName

data class ResultMoviesTMDB(
    @SerializedName("results")
    val results: List<MovieTMDB>,
)